package com.example.littletoothwallet.controller

import android.os.Bundle
import android.view.View
import com.example.littletoothwallet.R

class AddPaymentEntryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_payment_entry)
    }

    fun cancelActivity(view: View) {
        finish()
    }

    fun addNewPaymentMethod(view: View) {
        //TODO: Logica que requer o banco obvio
    }
}