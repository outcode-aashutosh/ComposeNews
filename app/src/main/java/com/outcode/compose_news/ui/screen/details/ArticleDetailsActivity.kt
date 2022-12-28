package com.outcode.compose_news.ui.screen.details

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import com.outcode.compose_news.R
import com.outcode.compose_news.model.Article
import com.outcode.compose_news.ui.component.LoadingView
import com.outcode.compose_news.ui.component.Toolbar
import com.outcode.compose_news.ui.component.ToolbarIcon
import com.outcode.compose_news.ui.screen.details.ArticleDetailsIntent.*
import com.outcode.compose_news.ui.screen.details.ArticleDetailsState.*
import com.outcode.compose_news.ui.screen.details.view.ArticleDetailsView
import org.koin.android.viewmodel.ext.android.viewModel

class ArticleDetailsActivity : AppCompatActivity() {

    private val viewModel: ArticleDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postIntent(Show(extractArticle()))

        setContent {
            val state = viewModel.state.observeAsState()

            Column {
                Toolbar()
                RenderBody(state.value)
            }
        }
    }

    @Composable
    private fun RenderBody(state: ArticleDetailsState?) {
        when (state) {
            is Loading -> LoadingView()
            is Success -> ArticleDetailsView(state.article) { postIntent(OpenInBrowser(it)) }
        }
    }

    @Composable
    private fun Toolbar() {
        Toolbar(
            title = getString(R.string.article_details),
            navigationIcon = ToolbarIcon(Icons.Default.ArrowBack) { onBackPressed() }
        )
    }

    private fun postIntent(intent: ArticleDetailsIntent) {
        viewModel.processIntent(intent)
    }

    private fun extractArticle(): Article {
        return intent.extras?.getParcelable(EXTRA_ARTICLE)
            ?: throw IllegalStateException("Extra article can't be null")
    }

    companion object {
        private const val EXTRA_ARTICLE = "EXTRA_ARTICLE"

        fun newIntent(context: Context, article: Article) =
            Intent(context, ArticleDetailsActivity::class.java).apply {
                flags = FLAG_ACTIVITY_NEW_TASK
                putExtra(EXTRA_ARTICLE, article)
            }
    }
}