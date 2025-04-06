package com.example.practiceem

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practiceem.practice1.task3.Apple
import com.example.practiceem.practice1.task3.MyApp
import com.example.practiceem.practice1.task3.User
import com.example.practiceem.practice1.task3.findInt
import com.example.practiceem.practice1.task4.shakerSort

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // обработчик нажатия на кнопку "Найти Int в списке типа Any"
        findViewById<Button>(R.id.bt_find_int).setOnClickListener {
                val intList: List<Int> = listOf(
                    Apple(),
                    MyApp,
                    User("Ann", "123"),
                    3,
                    123L,
                    270420,
                    listOf(1, 2),
                    "str",
                    true,
                    -89
                ).findInt()
                Log.d("FindInt", "Объекты типа Int: ${intList.joinToString(" ")}")
            }

        // обработчик нажатия на кнопку "Сортировать"
        findViewById<Button>(R.id.bt_sort).setOnClickListener {
            val list = shakerSort(listOf(5, null, 3, 1, 4, 2, null))
            Log.d("ShakerSort", "Результат сортировки: ${list?.joinToString(" ")}")
        }
    }
}