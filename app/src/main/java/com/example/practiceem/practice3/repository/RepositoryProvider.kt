package com.example.practiceem.practice3.repository

import com.example.practiceem.practice3.network.RetrofitClient

object RepositoryProvider {
    fun provideSearchRepository(): Repository {
        return Repository(RetrofitClient.create())
    }
}