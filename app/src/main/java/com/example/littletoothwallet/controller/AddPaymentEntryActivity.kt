package com.example.littletoothwallet.controller

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.littletoothwallet.R


class AddPaymentEntryActivity : BaseActivity() {

    private lateinit var colorPickerDialog: AlertDialog
    private lateinit var content: ConstraintLayout
    private lateinit var colorPickerButton: View

    private val colors = listOf(
        Color.CYAN, Color.rgb(179, 157, 219), Color.MAGENTA, Color.rgb(245, 245, 220), Color.YELLOW,
        Color.rgb(169, 169, 169), Color.GREEN, Color.rgb(244, 164, 96), Color.BLUE, Color.RED,
        Color.rgb(255, 228, 181), Color.rgb(72, 61, 139), Color.rgb(205, 92, 92), Color.rgb(255, 165, 0), Color.rgb(102, 205, 170)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_payment_entry)

        colorPickerButton = findViewById(R.id.colorPickerButton)
        colorPickerButton.setOnClickListener { showColorPickerDialog() }
    }

    private fun showColorPickerDialog() {
        val recyclerView = createColorPickerRecyclerView()
        colorPickerDialog = createColorPickerDialog(recyclerView)
        colorPickerDialog.show()
    }

    private fun createColorPickerRecyclerView(): RecyclerView {
        val numColumns = 5 // Desired number of columns
        val padding = dpToPx(15) // Convert 15 dp to pixels
        val spacing = dpToPx(15) // Set the spacing between items in dp

        return RecyclerView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            layoutManager = GridLayoutManager(this@AddPaymentEntryActivity, numColumns)
            setPadding(padding, dpToPx(20), padding, padding) // Convert padding to pixels
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
        colorPickerButton.setBackgroundColor(ColorUtils.blendARGB(selectedColor, Color.BLACK, 0.3f)) // Make it darker
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    

}