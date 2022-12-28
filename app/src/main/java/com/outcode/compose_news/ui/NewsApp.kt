package com.outcode.compose_news.ui

import android.app.Application
import com.outcode.compose_news.di.articleDetailsModule
import com.outcode.compose_news.di.articlesListModule
import com.outcode.compose_news.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NewsApp)
            modules(networkModule, articlesListModule, articleDetailsModule)
        }
    }
}