package com.outcode.compose_news.ui.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun Toolbar(title: String, rightIcon: ToolbarIcon? = null, navigationIcon: ToolbarIcon? = null) {
    TopAppBar(
        title = { Text(text = title) },
        actions = { CreateActionsIfNeeded(rightIcon) },
        navigationIcon = { CreateNavigationButtonIfNeeded(navigationIcon) },
        backgroundColor = Color.Black,
        contentColor = Color.White
    )
}

@Composable
private fun CreateNavigationButtonIfNeeded(navigationIcon: ToolbarIcon?) {
    navigationIcon?.let {
        IconButton(
            onClick = { it.onClick.invoke() },
            content = { Icon(it.icon) }
        )
    }
}

@Composable
private fun CreateActionsIfNeeded(rightIcon: ToolbarIcon?) {
    rightIcon?.let {
        IconButton(
            onClick = { it.onClick.invoke() },
            content = { Icon(it.icon) }
        )
    }
}

data class ToolbarIcon(
    val icon: ImageVector,
    val onClick: () -> Unit
)