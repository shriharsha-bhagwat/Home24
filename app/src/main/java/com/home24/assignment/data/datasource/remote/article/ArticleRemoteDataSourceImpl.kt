package com.home24.assignment.data.datasource.remote.article

import com.home24.assignment.data.core.base.BaseDataSource
import com.home24.assignment.data.datasource.remote.article.api.ArticleDataApi
import com.home24.assignment.domain.core.SimpleResult
import com.home24.assignment.domain.article.ArticleListResponse

class ArticleRemoteDataSourceImpl(private val articleDataApi: ArticleDataApi) :
    BaseDataSource(), IArticleRemoteDataSource {

    override suspend fun getListOfArticles(): SimpleResult<ArticleListResponse> =
        safeCall(call = {
            articleDataApi.getListOfArticles()
        })
}