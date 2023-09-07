package com.example.littletoothwallet

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val textView = findViewById<TextView>(R.id.textView)
        val fullText = getString(R.string.walkthrough_begin_title)

        // SpannableString a partir do texto completo
        val spannableString = SpannableString(fullText)

        //posição do texto que vai ficar amarelow
        val startIndex = fullText.indexOf("SORRISO")
        val endIndex = startIndex + "SORRISO".length

        // deixando negro
        val boldSpan = StyleSpan(Typeface.BOLD)
        spannableString.setSpan(boldSpan, startIndex, endIndex, 0)

        // deixando ele yellow
        val yellowColorSpan = ForegroundColorSpan(Color.YELLOW)
        spannableString.setSpan(yellowColorSpan, startIndex, endIndex, 0)

        // Definindo :)
        textView.text = spannableString

        // TODO: Lógica do botão pra levar ao CadastroActivity/LoginActivity(esse n existe)
    }

}