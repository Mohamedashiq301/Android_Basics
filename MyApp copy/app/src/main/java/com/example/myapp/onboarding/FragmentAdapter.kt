package com.example.myapp.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
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