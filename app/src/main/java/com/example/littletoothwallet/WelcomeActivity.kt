package com.example.littletoothwallet

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val text = getString(R.string.lazyRegister_begin_title)

        val words = text.split(" ")
        val endWords = words.last()

        val spannableString = SpannableString(text)

        val startIndex = text.indexOf(endWords)
        val endIndex = startIndex + endWords.length
        spannableString.setSpan(
            ForegroundColorSpan(Color.YELLOW),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        findViewById<TextView>(R.id.slogan).text = spannableString
    }

}