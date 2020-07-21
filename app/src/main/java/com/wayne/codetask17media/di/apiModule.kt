package com.wayne.codetask17media.di

import com.wayne.codetask17media.Constant
import com.wayne.codetask17media.model.api.ApiRepository
import com.wayne.codetask17media.model.api.GithubApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { createOkHttpClient() }
    single { createEpaDataService(get()) }
    single { createApiRepository(get()) }
}

fun createOkHttpClient() : OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
}

fun createEpaDataService(okHttpClient: OkHttpClient): GithubApiService {
    val retrofit =  Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constant.GITHUB_API_URL)
        .build()
    return retrofit.create(GithubApiService::class.java)
}

fun createApiRepository(githubApiService: GithubApiService): ApiRepository {
    return ApiRepository(githubApiService)
}