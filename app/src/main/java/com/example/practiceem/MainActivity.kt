package com.example.practiceem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practiceem.practice3.presentation.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }
}