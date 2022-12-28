package com.outcode.compose_news.ui.screen.details

import com.outcode.compose_news.model.Article

sealed class ArticleDetailsState {
    object Loading : ArticleDetailsState()
    class Success(val article: Article) : ArticleDetailsState()
}