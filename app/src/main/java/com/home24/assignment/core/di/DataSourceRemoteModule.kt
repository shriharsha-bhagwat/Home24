package com.home24.assignment.core.di

import com.home24.assignment.data.datasource.remote.article.IArticleRemoteDataSource
import com.home24.assignment.data.datasource.remote.article.ArticleRemoteDataSourceImpl
import org.koin.dsl.module

/**
 * [DataSourceRemoteModule] provides RemoteDataSource instances
 */

val DataSourceRemoteModule = module {

    single<IArticleRemoteDataSource> { ArticleRemoteDataSourceImpl(articleDataApi = get()) }
}