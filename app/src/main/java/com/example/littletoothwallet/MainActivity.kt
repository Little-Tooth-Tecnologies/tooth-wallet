package com.example.littletoothwallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import kotlin.random.Random

class MainActivity : BaseActivity() {

    // array de strings com as mensagens de loading
    private val loadingSteps: Array<String> by lazy {
        resources.getStringArray(R.array.loading_steps)
    }

    // remove as mensagens do handler quando a activity é destruida
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // busca a variavel de texto e gera um random baseado o java util
        val loadingTextView = findViewById<TextView>(R.id.loading_strings)
        val random = java.util.Random()

        // pega um valor randomico do array de strings e seta no textview
        handler.postDelayed({
            val randomIndex = random.nextInt(loadingSteps.size)
            loadingTextView.text = loadingSteps[randomIndex]
            updateRandomText(loadingTextView, random)
        }, 2000)


        // chama a tela de welcome
        Handler().postDelayed({
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)

            finish()
        }, 5000)
    }

    // atualiza o texto do textview com um valor randomico do array de strings
    private fun updateRandomText(loadingTextView: TextView?, random: java.util.Random) {
        handler.postDelayed({
            val randomIndex = random.nextInt(loadingSteps.size)
            loadingTextView?.text = loadingSteps[randomIndex]
            updateRandomText(loadingTextView, random)
        }, 1500)
    }
}