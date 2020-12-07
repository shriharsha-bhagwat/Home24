package com.home24.assignment.core.di

import com.home24.assignment.presentation.ui.detail.VenueDetailViewModel
import com.home24.assignment.presentation.ui.article.ArticleListViewModel
import com.home24.assignment.presentation.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * [ViewModelsModule] provides ViewModel instances
 */

val ViewModelsModule = module {
    viewModel { ArticleListViewModel(repository = get()) }
    viewModel { VenueDetailViewModel(repository = get()) }
    viewModel { SplashViewModel() }
}