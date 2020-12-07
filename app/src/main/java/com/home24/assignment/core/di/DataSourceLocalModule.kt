package com.home24.assignment.core.di

import com.home24.assignment.data.datasource.local.IArticleLocalDataSource
import com.home24.assignment.data.datasource.local.ArticleLocalDataSourceImpl
import org.koin.dsl.module


/**
 * [DataSourceLocalModule] provides LocalDataSource instances
 */

val DataSourceLocalModule = module {

    single<IArticleLocalDataSource> { ArticleLocalDataSourceImpl(dao = get()) }
}