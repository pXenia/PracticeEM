package com.example.practiceem.practice3.repository

import com.example.practiceem.practice3.model.DiscountCard
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


object DiscountRepository2 {
    fun getCards(): Observable<List<DiscountCard>> = Observable
//        .just(
//        listOf(
//            DiscountCard("3311", 15),
//            DiscountCard("4222", 10),
//            DiscountCard("1533", 15),
//            DiscountCard("6244", 12)
//        )
//    )
        .error(RuntimeException("DiscountRepository2 error"))
}