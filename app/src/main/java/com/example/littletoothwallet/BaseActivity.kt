package com.example.littletoothwallet

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat

open class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // For Android 30 (API 30) or higher
            window.setDecorFitsSystemWindows(true)
            // window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            // For versions prior to Android 30 (API 30)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }


    }


}
