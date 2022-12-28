package com.outcode.compose_news.ui.screen.details

import com.outcode.compose_news.model.Article

sealed class ArticleDetailsIntent {
    class Show(val article: Article) : ArticleDetailsIntent()
    class OpenInBrowser(val articleUrl: String) : ArticleDetailsIntent()
}