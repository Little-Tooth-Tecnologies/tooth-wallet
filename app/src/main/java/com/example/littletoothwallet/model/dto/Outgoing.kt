package com.example.littletoothwallet.model.dto

data class Outgoing(
    val id: Long,
    val name: String,
    val price: Double,
    val date: String,
    val bankAccountsId: Long
)
