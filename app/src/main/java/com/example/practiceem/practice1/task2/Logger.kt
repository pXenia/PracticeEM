package com.example.practiceem.practice1.task2

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/* Выводит в лог время запуска приложения */
class Logger(val time: Long, val delayTime: Long = 3000L) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val formattedTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    fun startLogging() {
        val timeStamp = formattedTime.format(Date(time))
        scope.launch {
            while (isActive) {
                Log.d("AppInfo", "Время запуска приложения: $timeStamp")
                delay(delayTime)
            }
        }
    }

    fun stopLogging() {
        scope.cancel()
    }
}