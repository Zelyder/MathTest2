package com.zelyder.mathtest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zelyder.mathtest.domain.models.FormulaModel
import com.zelyder.mathtest.help.FormulaUtilities
import com.zelyder.mathtest.help.setupMathView
import kotlinx.android.synthetic.main.formula_list_item.view.*



class FormulasListAdapter: RecyclerView.Adapter<FormulasHolder>() {

    private var formulas: List<FormulaModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormulasHolder {
        return FormulasHolder(LayoutInflater.from(parent.context)
            .inflate(com.zelyder.mathtest.R.layout.formula_list_item, parent, false))
    }

    override fun getItemCount(): Int = formulas.size

    override fun onBindViewHolder(holder: FormulasHolder, position: Int) {
        holder.bind(formulas[position])
    }

    fun refresh(formulas: List<FormulaModel>){
        this.formulas = formulas
        notifyDataSetChanged()
    }
}

class FormulasHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(formula: FormulaModel) = with(itemView) {
        tvFormulaItem.text = formula.name
        mvFormulaItem.text = formula.formula
        mvFormulaItem.setupMathView()
        if(formula.img != 0){
            imgFormulaItem.setImageResource(formula.img)
            imgFormulaItem.visibility = View.VISIBLE
        }
    }
}