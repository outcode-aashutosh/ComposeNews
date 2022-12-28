package com.outcode.compose_news.ui.screen.articles

import android.content.Context
import com.outcode.compose_news.model.Article
import com.outcode.compose_news.ui.screen.details.ArticleDetailsActivity

class ArticlesListNavigator(private val context: Context) {

    fun openArticleDetailsScreen(article: Article) {
        context.startActivity(ArticleDetailsActivity.newIntent(context, article))
    }
}