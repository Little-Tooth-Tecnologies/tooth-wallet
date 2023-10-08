package com.example.littletoothwallet

import android.content.Intent
import android.os.Bundle
import android.view.View

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun startHomeActivity(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun logoutConfirmation(view: View) {
        val dialogFragment = ConfirmationDialogFragment()
        dialogFragment.show(supportFragmentManager, "ConfirmationDialogFragment")
    }
}