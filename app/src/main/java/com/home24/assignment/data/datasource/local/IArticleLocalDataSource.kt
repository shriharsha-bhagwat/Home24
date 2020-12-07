package com.home24.assignment.data.datasource.local

import androidx.lifecycle.LiveData
import com.home24.assignment.data.datasource.local.entities.ArticleEntity
import com.home24.assignment.domain.core.SimpleResult

/**
 * Wrapper for [local.dao.ArticleDao]
 */

interface IArticleLocalDataSource {

    suspend fun observeArticles(): LiveData<SimpleResult<List<ArticleEntity>>>

    /**
     * Get all articles which was added to database
     */
    suspend fun getAllArticles(): SimpleResult<List<ArticleEntity>>

    /**
     * Get Venue which corresponds to given [id]
     */
    suspend fun getArticle(id: String): SimpleResult<ArticleEntity>

    /**
     * Delete only one Article
     */
    suspend fun deleteArticle(articleEntity: ArticleEntity): SimpleResult<Unit>

    /**
     * Clear whole table that contains [ArticleEntity]
     */
    suspend fun clearAll(): SimpleResult<Unit>

    suspend fun insertArticle(articleEntity: ArticleEntity): SimpleResult<Unit>

    suspend fun insertArticles(articles: List<ArticleEntity>): SimpleResult<Unit>
}