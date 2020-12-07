package com.home24.assignment.data.datasource.remote.article

import com.home24.assignment.data.datasource.local.entities.ArticleEntity
import com.home24.assignment.data.repository.ArticleData
import com.home24.assignment.domain.core.ResultState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Tests for [IArticleRemoteDataSource]
 */

@RunWith(JUnit4::class)
class ArticleRemoteDataSourceImplTest {

    private val remoteDataSource: IArticleRemoteDataSource = mockk()

    private lateinit var articleList: List<ArticleEntity>

    private val validNetworkReturn = ArticleData.DTO_RESPONSE

    /**
     * Override default Koin configuration to use Room in-memory database
     */
    @Before
    fun before() {
        articleList = getTestData()
    }

    fun getTestData(): List<ArticleEntity> {
        val articleOne = ArticleEntity( "1234", "Article One", "22.65", "articleOne.jpg", false, false)
        val articleTwo = ArticleEntity("5678", "Article Two","52.65", "articleTwo.jpg", false, false)

        return listOf(articleOne, articleTwo)
    }

    @Test
    fun test_getPoiList() = runBlocking {

        //Valid Data
        coEvery {
            remoteDataSource.getListOfArticles()
        } returns ResultState.Success(validNetworkReturn)

        val result = remoteDataSource.getListOfArticles()
        Assert.assertTrue(result is ResultState.Success)

        result.fold(
            success = { Assert.assertEquals(it.embedded.articles.size, 2) },
            failure = {}
        )

    }
}