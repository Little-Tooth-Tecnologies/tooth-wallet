package com.example.littletoothwallet.model.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.littletoothwallet.model.dto.Outgoing

class OutgoingDAO(private val database: SQLiteDatabase) {

    fun insertOutgoing(outgoing: Outgoing) {
        val values = ContentValues().apply {
            put("out_name", outgoing.name)
            put("out_price", outgoing.price)
            put("out_date", outgoing.date)
            put("bank_accounts_id", outgoing.bankAccountsId)
        }

        database.insert("outgoing", null, values)
    }

    fun updateOutgoing(outgoing: Outgoing) {
        val values = ContentValues().apply {
            put("out_name", outgoing.name)
            put("out_price", outgoing.price)
            put("out_date", outgoing.date)
            put("bank_accounts_id", outgoing.bankAccountsId)
        }

        val selection = "id = ?"
        val selectionArgs = arrayOf(outgoing.id.toString())

        database.update("outgoing", values, selection, selectionArgs)
    }

    fun deleteOutgoing(outgoing: Outgoing) {
        val selection = "id = ?"
        val selectionArgs = arrayOf(outgoing.id.toString())
        database.delete("outgoing", selection, selectionArgs)
    }

    fun getAllOutgoings(): List<Outgoing> {
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
