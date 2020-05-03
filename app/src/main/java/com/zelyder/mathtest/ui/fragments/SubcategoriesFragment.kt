package com.zelyder.mathtest.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zelyder.mathtest.R
import com.zelyder.mathtest.data.viewmodels.MainViewModel
import com.zelyder.mathtest.data.viewmodels.PageViewModel
import com.zelyder.mathtest.help.ARG_CATEGORY_ID
import com.zelyder.mathtest.help.ARG_INDEX_BACK
import com.zelyder.mathtest.help.ARG_SECTION_ID
import com.zelyder.mathtest.help.ARG_SUBCATEGORY_ID
import com.zelyder.mathtest.ui.adapters.SubcategoriesListAdapter

class SubcategoriesFragment : Fragment() {

    val TAG = "SubcategoriesFragment"
    var isBack = false

    private val mainViewModel by lazy { ViewModelProviders.of(this)
        .get(MainViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isBack = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = arguments?.let { SubcategoriesFragmentArgs.fromBundle(it) }

        if(args?.indexBack == 1 && !isBack){
            Log.d(TAG, "Count Back ${args.indexBack}  categoryId ${args.categoryId}")
            isBack = true
        }

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_subcategories, container, false)
        val subcategoriesList: RecyclerView = root.findViewById(R.id.subcategoriesList)

        val subcategoriesListAdapter = SubcategoriesListAdapter(args!!.sectionId)
        subcategoriesList.layoutManager = LinearLayoutManager(context)
        subcategoriesList.adapter = subcategoriesListAdapter

        mainViewModel.getSubcategories(args.categoryId)
            .observe(viewLifecycleOwner, Observer {
                it?.let {
                    subcategoriesListAdapter.refresh(it)
                }
            })

        return root
    }
}

