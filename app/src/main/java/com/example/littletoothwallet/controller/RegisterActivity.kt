package com.example.littletoothwallet.controller

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.littletoothwallet.R

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val viewPager: ViewPager = findViewById(R.id.viewPager)

        val adapter = RegisterPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        viewPager.setOnTouchListener { _, _ -> true }
    }
}