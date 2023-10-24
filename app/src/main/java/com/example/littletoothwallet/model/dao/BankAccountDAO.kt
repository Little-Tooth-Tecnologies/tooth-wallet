package com.example.littletoothwallet.model.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.example.littletoothwallet.R
import com.example.littletoothwallet.adapter.ExpensesAdapter
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

    fun deleteBankAccount(account: BankAccount, adapter: ExpensesAdapter) {
        val selection = "id = ?"
        val selectionArgs = arrayOf(account.id.toString())
        val result = database.delete("bank_accounts", selection, selectionArgs)
        if (result > 0) {
            val outgoingResult = database.delete("outgoing", "bank_accounts_id = ?", selectionArgs)
            if (outgoingResult > 0) {
                adapter.expenses.clear()
                adapter.notifyDataSetChanged()
                Toast.makeText(context, context.getString(R.string.toast_SuccessDelete), Toast.LENGTH_SHORT).show()
            }
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

    @SuppressLint("Range")
    fun getBankAccount(bankAccountId: Long): BankAccount? {
        val selectQuery = "SELECT  * FROM bank_accounts WHERE id = $bankAccountId"
        val cursor = database.rawQuery(selectQuery, null)

        var bankAccount: BankAccount? = null

        if (cursor.moveToFirst()) {
            val id = cursor.getLong(cursor.getColumnIndex("id"))
            val name = cursor.getString(cursor.getColumnIndex("ac_name"))
            val balance = cursor.getDouble(cursor.getColumnIndex("ac_balance"))
            val flag = cursor.getString(cursor.getColumnIndex("ac_flag"))

            bankAccount = BankAccount(id, name, balance, flag)
        }
        cursor.close()


        return bankAccount
    }
}
