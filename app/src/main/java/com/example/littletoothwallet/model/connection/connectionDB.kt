package com.example.littletoothwallet.model.connection

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ConnectionBD(contexto : Context) : SQLiteOpenHelper(contexto, NAME,null, VERSION) {

    companion object{
        private val NAME : String = "dbTooth.db"
        private val VERSION : Int = 1
    }

    override fun onCreate(db : SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS bank_accounts (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ac_name TEXT NOT NULL," +
                "ac_balance REAL NOT NULL," +
                "ac_flag TEXT NOT NULL" +
                ")")

        db.execSQL("CREATE TABLE IF NOT EXISTS outgoing (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "out_name TEXT NOT NULL," +
                "out_price REAL NOT NULL," +
                "out_date DATE NOT NULL," +
                "bank_accounts_id INTEGER NOT NULL," +
                "FOREIGN KEY (bank_accounts_id) REFERENCES bank_accounts (id)" +
                ")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}