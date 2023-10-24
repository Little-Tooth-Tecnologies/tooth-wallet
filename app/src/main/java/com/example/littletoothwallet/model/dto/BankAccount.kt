package com.example.littletoothwallet.model.dto

data class BankAccount(
    val id: Long = -1,
    val name: String,
    var balance: Double,
    val flag: String
)
