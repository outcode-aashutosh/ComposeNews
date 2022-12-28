package com.outcode.compose_news.di

import com.outcode.compose_news.network.api.NewsApi
import com.outcode.compose_news.network.repository.NewsRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val API_URL = "http://newsapi.org/v2/"
private const val API_KEY = "26fd580458214c518707a6c3d1816b32"

val networkModule = module {
    factory<Retrofit> {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(API_URL)
            .build()
    }

    factory<NewsApi> { get<Retrofit>().create(NewsApi::class.java) }
    factory { NewsRepository(get(), API_KEY) }
}