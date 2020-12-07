package com.home24.assignment.data.datasource.remote.article.api

import com.home24.assignment.core.utils.AppConstants.APP_COMMON_ARTICLE_LIMIT
import com.home24.assignment.core.utils.AppConstants.APP_COMMON_LOCALE
import com.home24.assignment.domain.article.ArticleListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleDataApi {


    /*You can take the Base URL from build config field for larger projects*/
    private companion object {
        private const val BASE_URL = "https://api-mobile.home24.com/api/v2.0/"
    }

    @GET(BASE_URL + "categories/100/articles")
    suspend fun getListOfArticles(
        @Query("appDomain") appDomain: Int = 1,
        @Query("locale") locale: String = APP_COMMON_LOCALE,
        @Query("limit") limit: Int = APP_COMMON_ARTICLE_LIMIT
    ): ArticleListResponse
}