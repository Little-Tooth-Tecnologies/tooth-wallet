package com.example.littletoothwallet

import CadastroPagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)

        // Criando o adaptador do ViewPager e configure-o
        val adapter = CadastroPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        // Conectando o TabLayout ao ViewPager
        tabLayout.setupWithViewPager(viewPager)

    }
}