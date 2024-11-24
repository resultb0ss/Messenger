package com.example.messenger.ui.main.auth.viewpager

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (
    fragment: FragmentActivity,
    private val viewPagerList: MutableList<ViewPagersPicture>
) : FragmentStateAdapter(fragment)
{
    override fun createFragment(position: Int): Fragment {

        val fragment = ItemViewPagerFragment()
        fragment.arguments = bundleOf("vp" to viewPagerList[position])
        return fragment
    }

    override fun getItemCount(): Int {
        return viewPagerList.size
    }
}