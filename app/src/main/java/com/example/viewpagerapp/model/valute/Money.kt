package com.example.viewpagerapp.model.valute

data class Money(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute: Map<String, Valute>
)