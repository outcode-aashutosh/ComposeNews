package com.outcode.compose_news.di

import com.outcode.compose_news.ui.screen.articles.ArticlesListNavigator
import com.outcode.compose_news.ui.screen.articles.ArticlesListViewModel
import com.outcode.compose_news.usecase.GetArticlesUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articlesListModule = module {
    factory { GetArticlesUseCase(get()) }
    factory { ArticlesListNavigator(get())}
    viewModel { ArticlesListViewModel(getArticlesUseCase = get(), get()) }
}