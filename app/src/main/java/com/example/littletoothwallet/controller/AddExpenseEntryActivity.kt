package com.example.littletoothwallet.controller

import android.os.Bundle
import android.view.View
import com.example.littletoothwallet.R

class AddExpenseEntryActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense_entry)
    }

    fun cancelActivity(view: View) {
        finish()
    }

    fun addNewExpense(view: View) {
        //TODO: Logica que requer o banco obvio
    }

}