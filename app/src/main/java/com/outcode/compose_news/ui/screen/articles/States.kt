package com.outcode.compose_news.ui.screen.articles

import com.outcode.compose_news.model.Article

sealed class ArticlesListState {
    object InitState : ArticlesListState()
    object Loading : ArticlesListState()
    class Error(val errorMessage: String) : ArticlesListState()
    class Success(val articles: List<Article>) : ArticlesListState()
}