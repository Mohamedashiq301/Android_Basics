package com.example.myapp.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapp.R
import com.example.myapp.views.LoginActivity

class OnBroadingActivity : AppCompatActivity(),OnBroadingOneFragment.OnNextClick,OnBroadingTwoFragment.OnOptionClick {
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_broading)
        bindview()
    }

    private fun bindview() {
        viewPager=findViewById(R.id.viewPager)
        val adpater=FragmentAdapter(supportFragmentManager)
        viewPager.adapter=adpater
    }

    override fun onClick() {
        viewPager.currentItem=1
    }

    override fun onOptionBack() {
        viewPager.currentItem=0
    }

    override fun onOptionNext() {
        val intent=Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}