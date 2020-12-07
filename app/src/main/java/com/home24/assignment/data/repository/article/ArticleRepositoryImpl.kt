package com.home24.assignment.data.repository.article

import androidx.lifecycle.LiveData
import com.home24.assignment.data.datasource.local.IArticleLocalDataSource
import com.home24.assignment.data.datasource.local.entities.ArticleEntity
import com.home24.assignment.data.datasource.remote.article.IArticleRemoteDataSource
import com.home24.assignment.data.repository.mappers.EntityMappers
import com.home24.assignment.domain.core.ResultState
import com.home24.assignment.domain.core.SimpleResult
import com.home24.assignment.domain.article.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * [IArticleRepository] implementation
 */

class ArticleRepositoryImpl(
    private val dataSourceRemote: IArticleRemoteDataSource,
    private val localDataSource: IArticleLocalDataSource,
) : IArticleRepository {

    override suspend fun getListOfArticles(): SimpleResult<Boolean> =
        dataSourceRemote.getListOfArticles().fold(
            success = { response ->
                storeArticlesInLocal(response)
                ResultState.Success(true)
            },
            failure = { throwable -> ResultState.Failure(throwable) }
        )

    suspend fun storeArticlesInLocal(response: ArticleListResponse) {
        localDataSource.clearAll()

        val venues = mutableListOf<ArticleEntity>()
        response.embedded.articles.forEach {
            venues.add(EntityMappers.toDto(it))
        }

        localDataSource.insertArticles(venues)
    }

    override suspend fun observeArticles(): LiveData<SimpleResult<List<ArticleEntity>>> =
        withContext(Dispatchers.IO) {
            localDataSource.observeArticles()
        }

    override suspend fun updateArticle(articleEntity: ArticleEntity): SimpleResult<Unit> =
        withContext(Dispatchers.IO) {
            localDataSource.insertArticle(articleEntity)
        }

}