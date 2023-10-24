package com.example.littletoothwallet.model.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.littletoothwallet.model.connection.ConnectionBD
import com.example.littletoothwallet.model.dto.Outgoing

class OutgoingDAO(private val context: Context) {

    private val connectionBD : ConnectionBD = ConnectionBD(context)
    private val database : SQLiteDatabase = connectionBD.writableDatabase

    fun insertOutgoing(outgoing: Outgoing) {
        val values = ContentValues().apply {
            put("out_name", outgoing.name)
            put("out_price", outgoing.price)
            put("out_date", outgoing.date)
            put("bank_accounts_id", outgoing.bankAccountId)
        }

        database.insert("outgoing", null, values)

        val bankAccountDAO = BankAccountDAO(context)
        val bankAccount = bankAccountDAO.getBankAccount(outgoing.bankAccountId)
        bankAccount!!.balance -= outgoing.price
        bankAccountDAO.updateBankAccount(bankAccount!!)
    }

    fun updateOutgoing(outgoing: Outgoing, oldPrice: Double) {
        val values = ContentValues().apply {
            put("out_name", outgoing.name)
            put("out_price", outgoing.price)
            put("out_date", outgoing.date)
            put("bank_accounts_id", outgoing.bankAccountId)
        }

        val selection = "id = ?"
        val selectionArgs = arrayOf(outgoing.id.toString())
        database.update("outgoing", values, selection, selectionArgs)

        val bankAccountDAO = BankAccountDAO(context)
        val bankAccount = bankAccountDAO.getBankAccount(outgoing.bankAccountId)
        bankAccount!!.balance = (bankAccount.balance + oldPrice) - outgoing.price
        bankAccountDAO.updateBankAccount(bankAccount)
    }

    fun deleteOutgoing(outgoing: Outgoing) {
        val selection = "id = ?"
        val selectionArgs = arrayOf(outgoing.id.toString())
        database.delete("outgoing", selection, selectionArgs)

        val bankAccountDAO = BankAccountDAO(context)
        val bankAccount = bankAccountDAO.getBankAccount(outgoing.bankAccountId)
        bankAccount!!.balance += outgoing.price
        bankAccountDAO.updateBankAccount(bankAccount!!)
    }

    fun getAllOutgoings(): MutableList<Outgoing> {
        val outgoings = mutableListOf<Outgoing>()
        val cursor = database.rawQuery("SELECT * FROM outgoing", null)

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow("id"))
                val name = getString(getColumnIndexOrThrow("out_name"))
                val price = getDouble(getColumnIndexOrThrow("out_price"))
                val date = getString(getColumnIndexOrThrow("out_date"))
                val accountId = getLong(getColumnIndexOrThrow("bank_accounts_id"))
                outgoings.add(Outgoing(id, name, price, date, accountId))
            }
        }
        cursor.close()
        return outgoings
    }
}
