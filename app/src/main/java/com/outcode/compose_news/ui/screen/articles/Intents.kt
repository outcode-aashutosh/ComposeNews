package com.outcode.compose_news.ui.screen.articles

import com.outcode.compose_news.model.Article


sealed class ArticlesListIntent {
    object Fetch : ArticlesListIntent()
    object Refresh : ArticlesListIntent()
    object Retry : ArticlesListIntent()
    class ShowDetails(val article: Article) : ArticlesListIntent()
}