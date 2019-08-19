package com.zelyder.mathtest.ui.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.zelyder.mathtest.R
import com.zelyder.mathtest.domain.models.CategoryModel
import com.zelyder.mathtest.help.ARG_CATEGORY_ID
import com.zelyder.mathtest.help.ARG_SECTION_ID
import kotlinx.android.synthetic.main.main_list_item.view.*

class MainListAdapter: RecyclerView.Adapter<MainListHolder>() {

    private var categories: List<CategoryModel> = ArrayList()
    var section: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListHolder {
        return MainListHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: MainListHolder, position: Int) {
        holder.bind(categories[position], section)
    }

    fun refreshSections(categories: List<CategoryModel>){
        this.categories = categories
        notifyDataSetChanged()
    }
}

class MainListHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(category: CategoryModel, section: Int) = with(itemView){
        sectionName.text = category.name
        sectionName.setCompoundDrawablesRelativeWithIntrinsicBounds(category.img ,0,0,0)
        setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(ARG_CATEGORY_ID, category.id)
            bundle.putInt(ARG_SECTION_ID, section)
            findNavController().navigate(R.id.to_test_action, bundle)
        }
    }
}