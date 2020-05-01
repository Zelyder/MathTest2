package com.zelyder.mathtest.ui.fragments

import android.os.Bundle
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
            .observe(viewLifecycleOwner, Observer {
                it?.let {
                    subcategoriesListAdapter.refresh(it)
                }
            })
/*
        Toast.makeText(context, "Count Back ${arguments?.getInt(ARG_INDEX_BACK) ?: 0} SECTION_ID ${arguments?.getInt(ARG_SECTION_ID) ?: 1}"
            , Toast.LENGTH_LONG).show()
*/
        return root
    }
    /*
    TODO: Разобраться и доделать полноэкранную рекламму
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController();
        // We use a String here, but any type that can be put in a Bundle is supported
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("key")?.observe(
            viewLifecycleOwner) { result ->
            // Do something with the  result.
        }
    }
*/
}

