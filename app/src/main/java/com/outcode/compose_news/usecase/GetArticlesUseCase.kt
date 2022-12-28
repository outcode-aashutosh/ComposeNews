package com.outcode.compose_news.usecase

import com.outcode.compose_news.network.api.ArticleDto
import com.outcode.compose_news.network.repository.NewsRepository
import com.outcode.compose_news.model.Article
import com.outcode.compose_news.model.toModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class GetArticlesUseCase(private val newsRepository: NewsRepository) {

    fun get(): Single<List<Article>> =
        newsRepository
            .getTopHeadlines(countryCode = "us")
            .flatMap { Observable.fromIterable(it) }
            .filter(::filterInvalidArticles)
            .map { it.toModel() }
            .toList()


    private fun filterInvalidArticles(articleDto: ArticleDto): Boolean = with(articleDto) {
        return author != null && urlToImage != null && description != null && content != null
    }

}