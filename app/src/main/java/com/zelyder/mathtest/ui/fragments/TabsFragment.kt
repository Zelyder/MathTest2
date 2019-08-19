package com.zelyder.mathtest.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.zelyder.mathtest.R
import com.zelyder.mathtest.ui.adapters.SectionsPagerAdapter


class TabsFragment : Fragment() {

    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sectionsPagerAdapter = SectionsPagerAdapter(view.context, childFragmentManager)
        viewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.currentItem
        val tabs: TabLayout = view.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

    }


}
