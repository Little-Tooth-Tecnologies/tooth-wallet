package com.example.littletoothwallet.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.littletoothwallet.R

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
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