package com.example.littletoothwallet.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.littletoothwallet.R
import com.example.littletoothwallet.model.dao.OutgoingDAO
import com.example.littletoothwallet.model.dto.Outgoing
import com.google.gson.Gson

class EditExpenseActivity : BaseActivity(){

    private lateinit var selectedExpense: Outgoing


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_expense)

        val json: String? = intent.getStringExtra("SelectedExpense")
        selectedExpense = Gson().fromJson(json, Outgoing::class.java)

        val inputName = findViewById<EditText>(R.id.inputExpenseEditName)
        val inputPrice = findViewById<EditText>(R.id.inputExpenseValueEdit)

        inputName.setText(selectedExpense.name)
        inputPrice.setText(selectedExpense.price.toString())
    }

    fun UpdateExpense(view: View) {
        val name = findViewById<EditText>(R.id.inputExpenseEditName).text.toString()
        val inputPriceText = findViewById<EditText>(R.id.inputExpenseValueEdit).text.toString()
        val price = inputPriceText.toDoubleOrNull() ?: 0.0

        val expense = Outgoing(selectedExpense.id, name, price, selectedExpense.date, selectedExpense.bankAccountId)
        val outgoingDAO = OutgoingDAO(this@EditExpenseActivity)
        outgoingDAO.updateOutgoing(expense, selectedExpense.price)

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    fun cancelActivity(view: View) {
        finish()
    }
}