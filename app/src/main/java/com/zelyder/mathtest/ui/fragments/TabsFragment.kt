package com.zelyder.mathtest.ui.fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
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

        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.to_settings_privacy_policy){
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://math-test-formulas.flycricket.io/privacy.html")
                )
            )
        }

        return item.onNavDestinationSelected(findNavController())
                || super.onOptionsItemSelected(item)
    }

}
