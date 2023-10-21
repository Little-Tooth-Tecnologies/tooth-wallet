package com.example.littletoothwallet.controller

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.littletoothwallet.R
import com.example.littletoothwallet.model.dao.BankAccountDAO
import com.example.littletoothwallet.model.dto.BankAccount
import com.google.gson.Gson

class EditPaymentEntryActivity : BaseActivity() {

    private lateinit var selectedAccount: BankAccount
    private lateinit var colorPickerDialog: AlertDialog
    private lateinit var colorPickerButton: View
    private lateinit var entryFlag: String
    private val colors = listOf(
        Color.CYAN, Color.rgb(179, 157, 219), Color.MAGENTA, Color.rgb(245, 245, 220), Color.YELLOW,
        Color.rgb(169, 169, 169), Color.GREEN, Color.rgb(244, 164, 96), Color.BLUE, Color.RED,
        Color.rgb(255, 228, 181), Color.rgb(72, 61, 139), Color.rgb(205, 92, 92), Color.rgb(255, 165, 0), Color.rgb(102, 205, 170)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_payment_entry)

        val json: String? = intent.getStringExtra("SelectedAccount")
        selectedAccount = Gson().fromJson(json, BankAccount::class.java)

        val inputName = findViewById<EditText>(R.id.inputPaymentEditName)
        val inputBalance = findViewById<EditText>(R.id.inputValueEdit)
        colorPickerButton = findViewById(R.id.colorPickerButtonEdit)

        inputName.setText(selectedAccount.name)
        inputBalance.setText(selectedAccount.balance.toString())
        colorPickerButton.setBackgroundColor(selectedAccount.flag.toInt())
        entryFlag = selectedAccount.flag
        colorPickerButton.setOnClickListener { showColorPickerDialog() }
    }

    private fun showColorPickerDialog() {
        val recyclerView = createColorPickerRecyclerView()
        colorPickerDialog = createColorPickerDialog(recyclerView)
        colorPickerDialog.show()
    }

    private fun createColorPickerRecyclerView(): RecyclerView {
        val numColumns = 5 // Desired number of columns
        val padding = dpToPx(15)
        val spacing = dpToPx(15) // Set the spacing between items in dp

        return RecyclerView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            layoutManager = GridLayoutManager(this@EditPaymentEntryActivity, numColumns)
            setPadding(padding, dpToPx(20), padding, padding)
            adapter = ColorAdapter(this@EditPaymentEntryActivity, colors) { selectedColor ->
                applySelectedColor(selectedColor)
                colorPickerDialog.dismiss()
            }
            addItemDecoration(GridSpacingItemDecoration(numColumns, spacing, true))
        }
    }

    private fun createColorPickerDialog(recyclerView: RecyclerView): AlertDialog {
        return AlertDialog.Builder(this, R.style.CustomAlertDialogTheme)
            .setTitle("Escolha uma cor")
            .setView(recyclerView)
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    private fun applySelectedColor(selectedColor: Int) {
        // Change Button Background Color
        colorPickerButton.setBackgroundColor(selectedColor)
        entryFlag = selectedColor.toString()
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    fun editPaymentMethod(view: View) {
        val name = findViewById<EditText>(R.id.inputPaymentEditName).text.toString()
        val inputBalanceText= findViewById<EditText>(R.id.inputValueEdit).text.toString()
        val balance = inputBalanceText.toDoubleOrNull() ?: 0.0

        val bankAccount = BankAccount(selectedAccount.id, name, balance, entryFlag)
        val bankAccountDAO = BankAccountDAO(this@EditPaymentEntryActivity)
        bankAccountDAO.updateBankAccount(bankAccount)
        println("omaigaa")

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    fun cancelActivity(view: View) {
        finish()
    }

}