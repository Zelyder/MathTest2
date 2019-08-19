package com.zelyder.mathtest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zelyder.mathtest.R
import com.zelyder.mathtest.data.viewmodels.MainViewModel
import com.zelyder.mathtest.data.viewmodels.PageViewModel
import com.zelyder.mathtest.help.ARG_CATEGORY_ID
import com.zelyder.mathtest.help.ARG_SECTION_ID
import com.zelyder.mathtest.ui.adapters.SubcategoriesListAdapter

class SubcategoriesFragment : Fragment() {

    private val mainViewModel by lazy { ViewModelProviders.of(this)
        .get(MainViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_subcategories, container, false)
        val subcategoriesList: RecyclerView = root.findViewById(R.id.subcategoriesList)
        val subcategoriesListAdapter = SubcategoriesListAdapter(arguments?.getInt(ARG_SECTION_ID) ?: 1)
        subcategoriesList.layoutManager = LinearLayoutManager(context)
        subcategoriesList.adapter = subcategoriesListAdapter

        mainViewModel.getSubcategories(arguments?.getInt(ARG_CATEGORY_ID) ?: 1)
            .observe(this, Observer {
                it?.let {
                    subcategoriesListAdapter.refresh(it)
                }
            })

        return root
    }
}
