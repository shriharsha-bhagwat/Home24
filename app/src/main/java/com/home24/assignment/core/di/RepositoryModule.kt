package com.home24.assignment.core.di

import com.home24.assignment.data.repository.article.ArticleRepositoryImpl
import com.home24.assignment.domain.article.IArticleRepository
import org.koin.dsl.module

/**
 * [RepositoryModule] provides repositories instances
 * Repository often depends on local and remote DataSources
 * For example, @see [<Any>Impl]
 */


val RepositoryModule = module {

    single<IArticleRepository> {
        ArticleRepositoryImpl(
            dataSourceRemote = get(),
            localDataSource = get()
        )
    }
}

