package com.zelyder.mathtest.ui.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.zelyder.mathtest.R
import com.zelyder.mathtest.domain.models.SubcategoryModel
import com.zelyder.mathtest.help.ARG_INDEX_BACK
import com.zelyder.mathtest.help.ARG_SUBCATEGORY_ID
import com.zelyder.mathtest.ui.fragments.SubcategoriesFragmentDirections
import kotlinx.android.synthetic.main.subcategory_list_item.view.*

class SubcategoriesListAdapter(private val section: Int): RecyclerView.Adapter<SubcategoriesListHolder>(){

    private var subcategories: List<SubcategoryModel> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoriesListHolder {
        return SubcategoriesListHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.subcategory_list_item, parent, false))
    }

    override fun getItemCount(): Int = subcategories.size

    override fun onBindViewHolder(holder: SubcategoriesListHolder, position: Int) {
        holder.bind(subcategories[position], section)
    }

    fun refresh(subcategories: List<SubcategoryModel>){
        this.subcategories = subcategories
        notifyDataSetChanged()
    }

}

class SubcategoriesListHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    fun bind(subcategory: SubcategoryModel, section: Int) = with(itemView) {
        subcategoryName.text = subcategory.name
        setOnClickListener {
            when (section){
                1 -> findNavController().navigate(SubcategoriesFragmentDirections.toTestAction(subcategory.id))
                2 -> findNavController().navigate(SubcategoriesFragmentDirections.toListAction(subcategory.id))
            }

        }
    }



}