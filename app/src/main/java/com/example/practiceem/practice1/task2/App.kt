package com.example.practiceem.practice1.task2

import android.app.Application

class App : Application() {
    // делегирование кеширования времени начала работы приложения
    private val startAppTime: Long by CacheStartTimeDelegate()
    private var timeLogger = Logger(startAppTime)

    override fun onCreate() {
        super.onCreate()
        timeLogger.startLogging()
    }

    override fun onTerminate() {
        super.onTerminate()
        timeLogger.stopLogging()
    }
}