package com.example.practiceem.practice3.network

import com.example.practiceem.practice3.model.CurrencyResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface CurrencyAPI {

    @GET("daily_json.js")
    fun getCurrency(): Observable<CurrencyResponse>
}