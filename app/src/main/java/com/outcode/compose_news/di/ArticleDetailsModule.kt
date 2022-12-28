package com.outcode.compose_news.di

import com.outcode.compose_news.ui.screen.details.ArticleDetailsNavigator
import com.outcode.compose_news.ui.screen.details.ArticleDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articleDetailsModule = module {
    factory { ArticleDetailsNavigator(get()) }
    viewModel { ArticleDetailsViewModel(get()) }
}