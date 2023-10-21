package com.example.littletoothwallet.model.dto

data class Outgoing(
    val id: Long = -1,
    var name: String,
    var price: Double,
    var date: String,
    val bankAccountId: Long
)
