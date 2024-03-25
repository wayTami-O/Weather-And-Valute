package com.example.viewpagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewpagerapp.databinding.ActivityMainBinding
import com.example.viewpagerapp.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.viewPager.adapter = PagerAdapter(this)
        //binding.viewPager.orientation = binding.viewPager.verticalScrollbarPosition
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.setIcon(R.drawable.baseline_wb_sunny_24)
                    tab.text = "Погода"
                    //tab.icon?.setTint(ContextCompat.getColor(this, R.color.blue))
                }
                1 -> {
                    tab.setIcon(R.drawable.baseline_attach_money_24)
                    tab.text = "Валюта"
                    //tab.icon?.setTint(ContextCompat.getColor(this, R.color.green))
                }
            }
        }.attach()
    }
}