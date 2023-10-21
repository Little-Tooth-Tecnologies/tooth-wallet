package com.example.littletoothwallet.model.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.example.littletoothwallet.R
import com.example.littletoothwallet.model.connection.ConnectionBD
import com.example.littletoothwallet.model.dto.BankAccount


class BankAccountDAO(private val context: Context) {

    private val connectionBD : ConnectionBD = ConnectionBD(context)
    private val database : SQLiteDatabase = connectionBD.writableDatabase

    fun insertBankAccount(account: BankAccount) {
        val values = ContentValues().apply {
            put("ac_name", account.name)
            put("ac_balance", account.balance)
            put("ac_flag", account.flag)
        }

        val result = database.insert("bank_accounts", null, values)
        if (result != -1L) {
            Toast.makeText(context, context.getString(R.string.toast_Success), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, context.getString(R.string.toast_Failure), Toast.LENGTH_SHORT).show()
        }
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
        val result = database.delete("bank_accounts", selection, selectionArgs)
        if (result > 0) {
            Toast.makeText(context, context.getString(R.string.toast_SuccessDelete), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, context.getString(R.string.toast_FailureDelete), Toast.LENGTH_SHORT).show()
        }
    }

    fun getAllBankAccounts(): MutableList<BankAccount> {
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
