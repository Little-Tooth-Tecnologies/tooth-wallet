package com.example.littletoothwallet

import android.content.Intent
import android.os.Bundle
import android.view.View

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