package com.example.littletoothwallet.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.littletoothwallet.R

class LazyRegister1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lazyregister1, container, false)


        val btnContinue = view.findViewById<Button>(R.id.btnContinue)
        btnContinue.setOnClickListener {
            val activity = requireActivity()
            if (activity is RegisterActivity) {
                val viewPager = activity.findViewById<ViewPager>(R.id.viewPager)

                val currentPosition = viewPager.currentItem
                val nextPagePosition = currentPosition + 1

                if (nextPagePosition < (viewPager.adapter?.count ?: 0)) {
                    viewPager.setCurrentItem(nextPagePosition, true)
                }
            }
        }

        return view
    }

//    fun continueRegister() {
//        val activity = requireActivity()
//        if (activity is RegisterActivity) {
//            val viewPager = activity.findViewById<ViewPager>(R.id.viewPager)
//
//            val currentPosition = viewPager.currentItem
//            val nextPagePosition = currentPosition + 1
//
//            if (nextPagePosition < (viewPager.adapter?.count ?: 0)) {
//                viewPager.setCurrentItem(nextPagePosition, true)
//            }
//        }
//    }

}
