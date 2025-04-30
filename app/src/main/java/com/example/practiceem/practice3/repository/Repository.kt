package com.example.practiceem.practice3.repository

import com.example.practiceem.practice3.network.CurrencyAPI
import com.example.practiceem.practice3.model.CurrencyResponse
import io.reactivex.rxjava3.core.Observable

class Repository(val currencyAPI: CurrencyAPI) {
    fun getCurrency(): Observable<CurrencyResponse> {
        return currencyAPI.getCurrency()
    }
}