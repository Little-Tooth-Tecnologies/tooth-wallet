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


class AddPaymentEntryActivity : BaseActivity() {

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
        setContentView(R.layout.activity_add_payment_entry)

        colorPickerButton = findViewById(R.id.colorPickerButton)
        colorPickerButton.setBackgroundColor(-16776961)
        entryFlag = (-16776961).toString()
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
            layoutManager = GridLayoutManager(this@AddPaymentEntryActivity, numColumns)
            setPadding(padding, dpToPx(20), padding, padding)
            adapter = ColorAdapter(this@AddPaymentEntryActivity, colors) { selectedColor ->
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

    fun addNewPaymentMethod(view: View) {
        val entryName = findViewById<EditText>(R.id.inputPaymentEntryName).text.toString()
        val entryValueText = findViewById<EditText>(R.id.inputValueEntry).text.toString()
        val entryValue = entryValueText.toDoubleOrNull() ?: 0.0

        val bankAccount = BankAccount(-1, entryName, entryValue, entryFlag)
        val bankAccountDAO = BankAccountDAO(this@AddPaymentEntryActivity)
        bankAccountDAO.insertBankAccount(bankAccount)

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    fun cancelActivity(view: View) {
        finish()
    }

}