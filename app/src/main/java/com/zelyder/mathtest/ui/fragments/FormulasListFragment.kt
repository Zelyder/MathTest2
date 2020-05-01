package com.zelyder.mathtest.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zelyder.mathtest.R
import com.zelyder.mathtest.data.viewmodels.FormulasListViewModel
import com.zelyder.mathtest.help.ARG_SUBCATEGORY_ID
import com.zelyder.mathtest.ui.adapters.FormulasListAdapter

class FormulasListFragment : Fragment() {

    private val formulasViewModel by lazy { ViewModelProviders.of(this)
        .get(FormulasListViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_formulas_list, container, false)

        val formulasList: RecyclerView = root.findViewById(R.id.formulasList)
        val listAdapter = FormulasListAdapter()
        formulasList.layoutManager = LinearLayoutManager(context)
        formulasList.adapter = listAdapter

        formulasViewModel.getFormulas(arguments?.getInt(ARG_SUBCATEGORY_ID) ?: 1)
            .observe(viewLifecycleOwner, Observer {
            it?.let {
                listAdapter.refresh(it)
            }
        })

        return root
    }


}
