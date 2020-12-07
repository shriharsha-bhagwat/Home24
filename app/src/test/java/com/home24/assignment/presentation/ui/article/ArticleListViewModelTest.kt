package com.home24.assignment.presentation.ui.article

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.home24.assignment.data.datasource.local.entities.ArticleEntity
import com.home24.assignment.domain.article.IArticleRepository
import com.home24.assignment.domain.core.ResultState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ArticleListViewModelTest {

    private val repository: IArticleRepository = mockk()

    private lateinit var viewModel: ArticleListViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val articleObserver = mockk<Observer<List<ArticleEntity>>>(relaxed = true)
    private val successState = getTestData()
    private val failureState = emptyList<ArticleEntity>()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        viewModel = ArticleListViewModel(repository)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun test_getListOfArticles_twoArticlesFound() = runBlocking {
        coEvery {
            repository.getListOfArticles()
        } returns ResultState.Success(true)

        viewModel.articles.observeForever(articleObserver)
        viewModel.getArticleData()
        coVerify { repository.getListOfArticles() }

        coVerifyOrder {
            articleObserver.onChanged(successState)
        }

        assertEquals(viewModel.articles.value?.size, 2)
    }

    @Test
    fun test_getListOfArticles_noArticlesFound() = runBlocking {
        coEvery {

            repository.getListOfArticles()
        } returns ResultState.Success(true)

        viewModel.articles.observeForever(articleObserver)
        viewModel.getArticleData()

        coVerify { repository.getListOfArticles() }

        delay(10)

        coVerifyOrder {
            articleObserver.onChanged(failureState)
        }

        assertEquals(viewModel.articles.value?.size, 0)
    }

    fun getTestData(): List<ArticleEntity> {
        val articleOne =
            ArticleEntity("1234", "Article One", "22.65", "articleOne.jpg", false, false)
        val articleTwo =
            ArticleEntity("5678", "Article Two", "52.65", "articleTwo.jpg", false, false)

        return listOf(articleOne, articleTwo)
    }
}