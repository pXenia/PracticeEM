package com.example.practiceem.practice1.task4

/* Шейкерная сортировка*/
fun shakerSort(list: List<Int?>?): List<Int?>? {
    list ?: return null // проверка на null
    val (nonNulls, nulls) = list.partition { it != null } // разделение на Int и null

    if (nulls.size == list.size) return list  // если список состоит только из null
    val intList = nonNulls.filterNotNull().toMutableList()

    val range = 0 until intList.size - 1
    val reversedRange = range.reversed()

    while (true) {
        var swapped = false
        for (index in listOf(range, reversedRange)) {
            for (i in index) {
                if (intList[i] > intList[i + 1]) {
                    intList[i] = intList[i + 1].also { intList[i + 1] = intList[i] }
                    swapped = true
                }
            }
        }
        if (!swapped) break
    }

    return intList + nulls
}