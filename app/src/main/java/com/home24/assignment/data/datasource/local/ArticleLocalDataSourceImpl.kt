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
        safeCall(TAG) { dao.getAllArticles() }

    override suspend fun getArticle(id: String): SimpleResult<ArticleEntity> =
        safeCall(TAG) { dao.getArticlesById(id) }

    override suspend fun insertArticle(articleEntity: ArticleEntity): SimpleResult<Unit> =
        safeCall(TAG) { dao.insertArticle(articleEntity) }

    override suspend fun deleteArticle(articleEntity: ArticleEntity): SimpleResult<Unit> =
        safeCall(TAG) { dao.deleteArticle(articleEntity) }

    override suspend fun clearAll(): SimpleResult<Unit> = safeCall(TAG) { dao.clearAll() }

    override suspend fun observeArticles(): LiveData<SimpleResult<List<ArticleEntity>>> {
        return dao.observeArticles().map {
            ResultState.Success(it)
        }
    }

    override suspend fun insertArticles(articles: List<ArticleEntity>): SimpleResult<Unit> =
        safeCall(TAG) { dao.insertArticles(articles) }

}