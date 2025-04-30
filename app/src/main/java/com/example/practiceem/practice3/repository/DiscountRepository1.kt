package com.example.practiceem.practice3.repository

import com.example.practiceem.practice3.model.DiscountCard
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


object DiscountRepository1 {
    fun getCards(): Observable<List<DiscountCard>> = Observable.just(
        listOf(
            DiscountCard("1111", 15),
            DiscountCard("2222", 10),
            DiscountCard("1133", 15),
            DiscountCard("2244", 12)
        )
    )
        .delay(1, TimeUnit.SECONDS)
}