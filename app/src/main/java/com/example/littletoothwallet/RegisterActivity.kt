package com.example.littletoothwallet

import RegisterPagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

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