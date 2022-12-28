package com.outcode.compose_news.ui.screen.articles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import com.outcode.compose_news.R
import com.outcode.compose_news.ui.component.Toolbar
import com.outcode.compose_news.ui.component.ErrorView
import com.outcode.compose_news.ui.component.LoadingView
import com.outcode.compose_news.ui.component.ToolbarIcon

import com.outcode.compose_news.ui.screen.articles.ArticlesListIntent.*
import com.outcode.compose_news.ui.screen.articles.ArticlesListState.*
import com.outcode.compose_news.ui.screen.articles.view.ArticlesList
import org.koin.android.viewmodel.ext.android.viewModel

class ArticlesListActivity : AppCompatActivity() {

    private val viewModel: ArticlesListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postIntent(Fetch)

        setContent {
            MaterialTheme {
                val state = viewModel.state.observeAsState()

                Column {
                    Toolbar()
                    RenderBody(state.value)
                }
            }
        }
    }

    @Composable
    private fun RenderBody(state: ArticlesListState?) {
        when (state) {
            is Loading -> LoadingView()
            is Success -> ArticlesList(state.articles) { postIntent(ShowDetails(it)) }
            is Error -> ErrorView(state.errorMessage) { postIntent(Retry) }
        }
    }

    @Composable
    private fun Toolbar() {
        Toolbar(
            title = getString(R.string.app_name),
            rightIcon = ToolbarIcon(
                icon = Icons.Default.Refresh,
                onClick = { postIntent(Refresh) }
            )
        )
    }

    private fun postIntent(intent: ArticlesListIntent) {
        viewModel.processIntent(intent)
    }
}
