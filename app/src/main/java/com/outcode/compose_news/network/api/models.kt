package com.outcode.compose_news.network.api

data class ArticlesResponseDto(
    val totalArticles: Int,
    val articles: List<ArticleDto>
)

data class ArticleDto(
    val author: String?,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val content: String?,
    val url: String
)