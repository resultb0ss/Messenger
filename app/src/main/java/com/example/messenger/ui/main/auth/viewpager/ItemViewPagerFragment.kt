package com.example.messenger.ui.main.auth.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.messenger.R
import com.example.messenger.databinding.FragmentItemViewPagerBinding


class ItemViewPagerFragment : Fragment() {


    private var _binding: FragmentItemViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPageItem = arguments?.getSerializable("vp") as ViewPagersPicture
        binding.viewPagerText.text = viewPageItem.title
        binding.viewPagerImage.setImageResource(viewPageItem.imageView)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}