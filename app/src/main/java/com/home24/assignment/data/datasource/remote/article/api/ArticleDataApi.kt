package com.home24.assignment.data.datasource.remote.article.api

import com.home24.assignment.core.utils.AppConstants.APP_COMMON_ARTICLE_LIMIT
import com.home24.assignment.core.utils.AppConstants.APP_COMMON_LOCALE
import com.home24.assignment.core.utils.AppConstants.BASE_URL
import com.home24.assignment.domain.article.ArticleListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleDataApi {

    @GET(BASE_URL + "categories/100/articles")
    suspend fun getListOfArticles(
        @Query("appDomain") appDomain: Int = 1,
        @Query("locale") locale: String = APP_COMMON_LOCALE,
        @Query("limit") limit: Int = APP_COMMON_ARTICLE_LIMIT
    ): ArticleListResponse
}