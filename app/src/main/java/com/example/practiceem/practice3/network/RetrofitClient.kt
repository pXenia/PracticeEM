package com.example.practiceem.practice3.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object RetrofitClient {
    fun create(): CurrencyAPI {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .build()

        return retrofit.create(CurrencyAPI::class.java)
    }
}