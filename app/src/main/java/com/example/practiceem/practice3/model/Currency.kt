package com.example.practiceem.practice3.model

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("Valute") val valute: Map<String, Currency>
)

data class Currency(
    @SerializedName("Name") val name: String,
    @SerializedName("Value") val value: Double,
)
