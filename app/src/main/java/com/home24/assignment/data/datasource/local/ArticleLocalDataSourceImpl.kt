package com.home24.assignment.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.home24.assignment.data.core.base.BaseDataSource
import com.home24.assignment.data.datasource.local.dao.ArticleDao
import com.home24.assignment.data.datasource.local.entities.ArticleEntity
import com.home24.assignment.domain.core.ResultState
import com.home24.assignment.domain.core.SimpleResult


/**
 * [IArticleLocalDataSource] implementation
 */

class ArticleLocalDataSourceImpl(
    private val dao: ArticleDao
) : IArticleLocalDataSource, BaseDataSource() {

    override suspend fun getAllArticles(): SimpleResult<List<ArticleEntity>> =
        safeCall(TAG) { dao.getAllVenues() }

    override suspend fun getArticle(id: String): SimpleResult<ArticleEntity> =
        safeCall(TAG) { dao.getVenueById(id) }

    override suspend fun insertArticle(articleEntity: ArticleEntity): SimpleResult<Unit> =
        safeCall(TAG) { dao.insertVenue(articleEntity) }

    override suspend fun deleteArticle(articleEntity: ArticleEntity): SimpleResult<Unit> =
        safeCall(TAG) { dao.deleteVenue(articleEntity) }

    override suspend fun clearAll(): SimpleResult<Unit> = safeCall(TAG) { dao.clearAll() }

    override suspend fun observeArticles(): LiveData<SimpleResult<List<ArticleEntity>>> {
        return dao.observeVenues().map {
            ResultState.Success(it)
        }
    }

    override suspend fun insertArticles(articles: List<ArticleEntity>): SimpleResult<Unit> =
        safeCall(TAG) { dao.insertVenues(articles) }

}