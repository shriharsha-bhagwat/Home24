package com.home24.assignment.data.datasource.remote.article

import com.home24.assignment.domain.core.SimpleResult
import com.home24.assignment.domain.article.*

interface IArticleRemoteDataSource {

    suspend fun getListOfArticles(): SimpleResult<ArticleListResponse>

}