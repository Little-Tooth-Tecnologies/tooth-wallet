package com.example.littletoothwallet.controller

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.littletoothwallet.R
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun cancelActivity(view: View) {
        finish()
    }

    fun logoutConfirmation(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.dialog_confirmation)
            .setPositiveButton(
                R.string.dialog_confirmation_yes
            ) { _, _ ->
                FirebaseAuth.getInstance().signOut()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
            .setNegativeButton(
                R.string.dialog_confirmation_cancel
            ) { _, _ ->
                // nada
            }
        builder.create().show()
    }
}