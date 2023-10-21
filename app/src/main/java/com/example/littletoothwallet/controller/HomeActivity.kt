package com.example.littletoothwallet.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.littletoothwallet.R
import com.example.littletoothwallet.adapter.BankAccountsAdapter
import com.example.littletoothwallet.adapter.ExpensesAdapter
import com.example.littletoothwallet.model.dao.BankAccountDAO
import com.example.littletoothwallet.model.dao.OutgoingDAO
import com.example.littletoothwallet.model.dto.BankAccount
import com.example.littletoothwallet.model.dto.Outgoing

class HomeActivity : BaseActivity() {

    private lateinit var recyclerViewBA: RecyclerView
    private lateinit var recyclerViewEX: RecyclerView
    private lateinit var bankAccounts: MutableList<BankAccount>
    private lateinit var expenses: MutableList<Outgoing>
    private lateinit var bankAccountsAdapter: BankAccountsAdapter
    private lateinit var expensesAdapter: ExpensesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerViewBA = findViewById(R.id.RecyclerView)
        recyclerViewEX = findViewById(R.id.RecyclerViewExpense)

        val bankAccountsDAO = BankAccountDAO(this@HomeActivity)
        bankAccounts = bankAccountsDAO.getAllBankAccounts()
        val outgoingsDAO = OutgoingDAO(this@HomeActivity)
        expenses = outgoingsDAO.getAllOutgoings()

        expensesAdapter = ExpensesAdapter(this@HomeActivity, expenses)
        recyclerViewEX.adapter = expensesAdapter
        recyclerViewEX.layoutManager = LinearLayoutManager(this@HomeActivity)

        bankAccountsAdapter = BankAccountsAdapter(this@HomeActivity, bankAccounts, expensesAdapter)
        recyclerViewBA.adapter = bankAccountsAdapter
        recyclerViewBA.layoutManager = LinearLayoutManager(this@HomeActivity)

    }

    fun startSettingsActivity(view: View) {
        val intent = Intent(this@HomeActivity, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun startAddPaymentMethod(view: View) {
        val intent = Intent(this@HomeActivity, AddPaymentEntryActivity::class.java)
        startActivity(intent)
    }

    fun startAddExpense(view: View) {
        val intent = Intent(this@HomeActivity, AddExpenseEntryActivity::class.java)
        startActivity(intent)
    }
}