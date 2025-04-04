package com.example.practiceem.practice1.task3

// расширение для поиска Int типов в списке
fun List<Any>.findInt(): List<Int> {
    return this.mapNotNull { it as? Int }
}