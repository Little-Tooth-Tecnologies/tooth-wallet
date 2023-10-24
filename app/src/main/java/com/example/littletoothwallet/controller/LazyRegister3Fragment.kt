package com.example.littletoothwallet.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.littletoothwallet.R

class LazyRegister3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lazyregister3, container, false)

        val btnReturn = view.findViewById<Button>(R.id.btnReturn2)
        btnReturn.setOnClickListener {
            val activity = requireActivity()
            if (activity is RegisterActivity) {
                val viewPager = activity.findViewById<ViewPager>(R.id.viewPager)

                val currentPosition = viewPager.currentItem
                val previousPagePosition = currentPosition - 1

                if (previousPagePosition < (viewPager.adapter?.count ?: 0)) {
                    viewPager.setCurrentItem(previousPagePosition, true)
                }
            }
        }

        return view
    }
}