package com.home24.assignment.data.repository.article

import com.home24.assignment.data.datasource.local.IArticleLocalDataSource
import com.home24.assignment.data.datasource.remote.article.IArticleRemoteDataSource
import com.home24.assignment.data.repository.ArticleData
import com.home24.assignment.domain.core.ResultState
import com.home24.assignment.domain.article.IArticleRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ArticleRepositoryImplTest {

    private val remoteDataSource: IArticleRemoteDataSource = mockk()
    private val localDataSource: IArticleLocalDataSource = mockk()

    private val repository: IArticleRepository =
        ArticleRepositoryImpl(remoteDataSource, localDataSource)

    private val validNetworkReturn = ArticleData.DTO_RESPONSE

    @Before
    fun before() {

    }

    /**
     * Scenario:
     * [IArticleRemoteDataSource]  has data for given data -> return data to user
     */
    @Test
    fun testSuccessfulReturnFromRemoteDataSource() = runBlocking {

        //Valid Data
        coEvery {
            remoteDataSource.getListOfArticles()
        } returns ResultState.Success(validNetworkReturn)

        val result = repository.getListOfArticles()

        Assert.assertTrue(result is ResultState.Success)

        result.fold(
            success = { Assert.assertTrue(it) },
            failure = { Assert.assertNull(it.message) }
        )

    }

    /**
     * Scenario:
     * Bad data format given to [IArticleRemoteDataSource]
     * Throws an error with message as "Invalid Data"
     */
    @Test
    fun testFailureReturnInvalidDataFromRemoteDataSource() = runBlocking {

        //Invalid data
        coEvery {
            remoteDataSource.getListOfArticles()
        } returns ResultState.Failure(Exception("Invalid Data"))

        val result = repository.getListOfArticles()

        Assert.assertTrue(result is ResultState.Failure)

        result.fold(
            success = { Assert.assertTrue(it) },
            failure = { Assert.assertTrue(it.message == "Invalid Data") }
        )

    }
}