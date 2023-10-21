package com.example.littletoothwallet.model.dto

import java.io.Serializable

data class BankAccount(
    val id: Long = -1,
    val name: String,
    val balance: Double,
    val flag: String
) : Serializable
