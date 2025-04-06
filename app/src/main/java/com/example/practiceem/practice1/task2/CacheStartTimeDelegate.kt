package com.example.practiceem.practice1.task2

import android.app.Application
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/* Делегат для кеширования времени запуска приложения */
class CacheStartTimeDelegate : ReadOnlyProperty<Application, Long> {
    private val startTime = System.currentTimeMillis() // время запуска
    override fun getValue(thisRef: Application, property: KProperty<*>): Long = startTime
}