package com.example.messenger.ui.main.auth.viewpager

import com.example.messenger.R
import java.io.Serializable

class ViewPagersPicture (

    val title: String,
    val imageView: Int
    ): Serializable {

        companion object {
            val viewPagerList = mutableListOf(
                ViewPagersPicture("text", R.drawable.start_fragment_viewpager_icon_one),
                ViewPagersPicture("text", R.drawable.start_fragment_viewpager_icon_two),
                ViewPagersPicture("text", R.drawable.start_fragment_viewpager_icon_three),
                ViewPagersPicture("text", R.drawable.start_fragment_viewpager_icon_four),
                ViewPagersPicture("text", R.drawable.start_fragment_viewpager_icon_five),
                )
        }

}