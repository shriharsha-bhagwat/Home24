package com.home24.assignment.domain.article

import androidx.lifecycle.LiveData
import com.home24.assignment.data.datasource.local.entities.ArticleEntity
import com.home24.assignment.domain.core.SimpleResult

interface IArticleRepository {

    suspend fun observeArticles(): LiveData<SimpleResult<List<ArticleEntity>>>

    suspend fun updateArticle(articleEntity: ArticleEntity): SimpleResult<Unit>

    suspend fun getListOfArticles(): SimpleResult<Boolean>

}