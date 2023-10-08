package com.example.littletoothwallet

import androidx.fragment.app.DialogFragment
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class ConfirmationDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.dialog_confirmation)
                .setPositiveButton(R.string.dialog_confirmation_yes,
                    DialogInterface.OnClickListener { dialog, id ->
                        FirebaseAuth.getInstance().signOut()

                        val intent = Intent(it, MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finishAffinity()
                    })
                .setNegativeButton(R.string.dialog_confirmation_cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // nada
                    })
            builder.create()
        } ?: throw IllegalStateException("Não pode chamar fora de activity (nem sei se tem como)")
    }
}