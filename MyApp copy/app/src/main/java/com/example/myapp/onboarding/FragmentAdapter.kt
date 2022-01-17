package com.example.myapp.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fragmentManager: FragmentActivity) : FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return OnBroadingOneFragment()
            }
            1 -> {
                return OnBroadingTwoFragment()
            }
            else -> {
                return OnBroadingOneFragment()
            }
        }
    }

}