package com.example.littletoothwallet.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.example.littletoothwallet.R
import com.example.littletoothwallet.model.dao.BankAccountDAO
import com.example.littletoothwallet.model.dao.OutgoingDAO
import com.example.littletoothwallet.model.dto.BankAccount
import com.example.littletoothwallet.model.dto.Outgoing
import java.util.Calendar

class AddExpenseEntryActivity : BaseActivity() {

    private lateinit var selectedBankAccount: BankAccount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense_entry)
        val bankAccounts = BankAccountDAO(this@AddExpenseEntryActivity).getAllBankAccounts()

        val spinner = findViewById<Spinner>(R.id._dynamic)
        val bankAccountNames: List<String> = bankAccounts.map { it.name }

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, bankAccountNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedAccountName: String =
                    spinner.selectedItem as String
                selectedBankAccount = bankAccounts.find { it.name == selectedAccountName }!!
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }



        fun addNewExpense(view: View) {

        }

    }

    fun getCurrentDate(): String {
        val cal = Calendar.getInstance()
        val dia = cal.get(Calendar.DAY_OF_MONTH)
        val mes = cal.get(Calendar.MONTH) + 1
        val ano = cal.get(Calendar.YEAR)

        return "$dia/$mes/$ano"
    }

    fun addNewExpense(view: View) {
        val entryName = findViewById<EditText>(R.id.inputExpenseEntryName).text.toString()
        val entryValueText = findViewById<EditText>(R.id.inputExpenseValueEntry).text.toString()
        val entryValue = entryValueText.toDoubleOrNull() ?: 0.0
        val dateValue = getCurrentDate()

        val expense = Outgoing(-1, entryName, entryValue, dateValue, selectedBankAccount.id)
        val outgoingDAO = OutgoingDAO(this@AddExpenseEntryActivity)
        outgoingDAO.insertOutgoing(expense)

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    fun cancelActivity(view: View) {
        finish()
    }
}