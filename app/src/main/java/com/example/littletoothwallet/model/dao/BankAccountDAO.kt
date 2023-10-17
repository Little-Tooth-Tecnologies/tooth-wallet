package com.example.littletoothwallet.model.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.littletoothwallet.model.dto.BankAccount

class BankAccountDAO(private val    database: SQLiteDatabase) {

    fun insertBankAccount(account: BankAccount) {
        val values = ContentValues().apply {
            put("ac_name", account.name)
            put("ac_balance", account.balance)
            put("ac_flag", account.flag)
        }

        database.insert("bank_accounts", null, values)
    }

    fun updateBankAccount(account: BankAccount) {
        val values = ContentValues().apply {
            put("ac_name", account.name)
            put("ac_balance", account.balance)
            put("ac_flag", account.flag)
        }

        val selection = "id = ?"
        val selectionArgs = arrayOf(account.id.toString())

        database.update("bank_accounts", values, selection, selectionArgs)
    }

    fun deleteBankAccount(account: BankAccount) {
        val selection = "id = ?"
        val selectionArgs = arrayOf(account.id.toString())
        database.delete("bank_accounts", selection, selectionArgs)
    }

    fun getAllBankAccounts(): List<BankAccount> {
        val accounts = mutableListOf<BankAccount>()
        val cursor = database.rawQuery("SELECT * FROM bank_accounts", null)

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow("id"))
                val name = getString(getColumnIndexOrThrow("ac_name"))
                val balance = getDouble(getColumnIndexOrThrow("ac_balance"))
                val flag = getString(getColumnIndexOrThrow("ac_flag"))
                accounts.add(BankAccount(id, name, balance, flag))
            }
        }
        cursor.close()
        return accounts
    }
}
