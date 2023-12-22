package com.dicoding.mybatikfinder.view.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.dicoding.mybatikfinder.R
import com.dicoding.mybatikfinder.view.main.ViewPagerAdapter

class HomeFragment : Fragment() {

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var imageList: List<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewPager = view.findViewById(R.id.vpBatik)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageList = mutableListOf(R.drawable.batik1,R.drawable.batik2,R.drawable.batik3)

        viewPagerAdapter = ViewPagerAdapter(requireActivity(), imageList)
        viewPager.adapter = viewPagerAdapter
    }

}