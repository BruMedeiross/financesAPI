package com.brunadev.rapidApi.network


import com.brunadev.rapidApi.commom.ApiEndPoints
import com.brunadev.rapidApi.network.Api.BASE_URL
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpClient {

    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader("X-RapidAPI-Key", "fadb611317mshd35d621fa69c0e4p107c68jsn3b2cb07cd478")
                .addHeader("X-RapidAPI-Host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                .method(original.method(), original.body())
                .build()

            chain.proceed(requestBuilder)
        }.build()

    val eventApi: ApiEndPoints = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(ApiEndPoints::class.java) // inicializa a api

}