package com.home24.assignment.core.di

import android.util.Log
import com.home24.assignment.core.Home24App
import com.home24.assignment.core.utils.AppConstants
import com.home24.assignment.core.utils.AppConstants.BASE_URL
import com.home24.assignment.core.utils.serialization.asConverterFactory
import com.home24.assignment.data.datasource.remote.article.api.ArticleDataApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit


private val contentType = "application/json".toMediaType()

val NetworkModule = module {
    single { provideRetrofit() }
    single { provideArticlesDataApi(retrofit = get()) }
}

fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
        .apply {
            addConverterFactory(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }.asConverterFactory(contentType))

            baseUrl(BASE_URL)
            if (Home24App.debug.enabled) {
                val okHttpClient = OkHttpClient.Builder()
                okHttpClient.addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                okHttpClient.addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val newUrl = chain
                            .request()
                            .url
                            .newBuilder()
                            .build()

                        val request = chain
                            .request()
                            .newBuilder()
                            .url(newUrl)
                            .build()

                        val response = chain.proceed(request)
                        if (response.code == AppConstants.APP_COMMON_RESPONSE_CODE_UNAUTH) {
                            Log.e(AppConstants.APP_TAG, "Unauth response")
                        }
                        return response
                    }
                })
                client(okHttpClient.build())
            }
        }
        .build()


fun provideArticlesDataApi(retrofit: Retrofit): ArticleDataApi =
    retrofit.create(ArticleDataApi::class.java)