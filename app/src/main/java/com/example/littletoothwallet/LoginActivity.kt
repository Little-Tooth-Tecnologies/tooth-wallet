package com.example.littletoothwallet

import android.content.Intent
import android.content.ContentValues.TAG
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        setContentView(R.layout.activity_login)

        val btnBack = findViewById<Button>(R.id.btnReturnLogin)
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    fun Login(view: View) {
        val emailEditText = findViewById<EditText>(R.id.InputEmail)
        val senhaEditText = findViewById<EditText>(R.id.InputPassword)

        val email = emailEditText.text.toString()
        val senha = senhaEditText.text.toString()

        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(
                        baseContext,
                        "Carregando a tela inicial...",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUIhome()
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Autenticação Falhou, Tente Novamente.",
                        Toast.LENGTH_LONG,
                    ).show()
                    // TODO: deixar edittexts em branco
                }
            }

    }

    private fun updateUIhome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}