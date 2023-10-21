package com.example.littletoothwallet.controller

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.littletoothwallet.R
import com.example.littletoothwallet.adapter.BankAccountsAdapter
import com.example.littletoothwallet.model.connection.ConnectionBD
import com.example.littletoothwallet.model.dao.BankAccountDAO
import com.example.littletoothwallet.model.dto.BankAccount

class HomeActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bankAccounts: List<BankAccount>
    private lateinit var bankAccountsAdapter: BankAccountsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.RecyclerView)
        val connectionBD : ConnectionBD = ConnectionBD(this@HomeActivity)
        val database : SQLiteDatabase = connectionBD.writableDatabase

        val bankAccountsDAO = BankAccountDAO(this@HomeActivity)

        bankAccounts = bankAccountsDAO.getAllBankAccounts()
        bankAccountsAdapter = BankAccountsAdapter(this@HomeActivity, bankAccounts)
        recyclerView.adapter = bankAccountsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


    }

    fun startSettingsActivity(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun startAddPaymentMethod(view: View) {
        val intent = Intent(this, AddPaymentEntryActivity::class.java)
        startActivity(intent)
    }

    fun startAddExpense(view: View) {
        val intent = Intent(this, AddExpenseEntryActivity::class.java)
        startActivity(intent)
    }
}