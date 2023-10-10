package com.example.littletoothwallet

import android.content.Intent
import android.os.Bundle
import android.view.View

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun cancelActivity(view: View) {
        finish()
    }

    fun logoutConfirmation(view: View) {
        val dialogFragment = ConfirmationDialogFragment()
        dialogFragment.show(supportFragmentManager, "ConfirmationDialogFragment")
    }
}