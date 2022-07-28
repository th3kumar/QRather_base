package com.example.qrather.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import fragments.ScannedHistoryFragment

class MainPagerAdapter(var  fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getCount() : Int {
       return 2
    }

    override fun getItem(position : Int) : Fragment {
        return when (position) {
            0 -> ScannedHistoryFragment.newInstance(ScannedHistoryFragment.ResultListType.ALL_RESULT)
            1 -> ScannedHistoryFragment.newInstance(ScannedHistoryFragment.ResultListType.FAVOURITE_RESULT)
            else -> ScannedHistoryFragment.newInstance(ScannedHistoryFragment.ResultListType.ALL_RESULT)
        }
    }


}