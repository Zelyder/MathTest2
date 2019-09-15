package com.zelyder.mathtest.ui.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IValueFormatter
import com.zelyder.mathtest.R
import com.zelyder.mathtest.data.viewmodels.TestAViewModel
import com.zelyder.mathtest.databinding.FragmentTestBinding
import com.zelyder.mathtest.help.ARG_SUBCATEGORY_ID
import com.zelyder.mathtest.help.FormulaUtilities
import com.zelyder.mathtest.help.setupMathView
import com.zelyder.mathtest.interfaces.DialogControl
import kotlinx.android.synthetic.main.dialog_correct_formula.view.*
import kotlinx.android.synthetic.main.dialog_final.view.*
import kotlinx.android.synthetic.main.fragment_test.*
import kotlinx.android.synthetic.main.fragment_test.view.*


class TestFragment : Fragment(), DialogControl {

    private val testViewModel by lazy {
        ViewModelProviders.of(this).get(TestAViewModel::class.java)
    }

    private var scale = 0f
    private  var isImgBig = false
    private val state1 = ConstraintSet()
    private val state2 = ConstraintSet()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTestBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_test, container, false
        )

        binding.lifecycleOwner = this
        binding.viewModel = testViewModel

        scale = resources.configuration.fontScale

        state1.clone(context, R.layout.fragment_test)
        state2.clone(context, R.layout.fragment_test_big_img)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<Button>(R.id.button)?.visibility = View.GONE



        activity?.findViewById<ImageView>(R.id.imgTest)?.setOnClickListener {
            TransitionManager.beginDelayedTransition(consLayoutTest)
            val constraint = if (isImgBig) state1 else state2
            constraint.applyTo(consLayoutTest)
            isImgBig = !isImgBig
        }

        testViewModel.setKeys(
            FormulaUtilities().getKeys(
                arguments?.getInt(ARG_SUBCATEGORY_ID) ?: 1
            )
        )
        testViewModel.dialogControl = this

        math_view_item.setupMathView()

        testViewModel.setFormulas(arguments?.getInt(ARG_SUBCATEGORY_ID) ?: 1, this)


    }

    override fun createCorrectDialog(text: String) {
        val builder = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.dialog_correct_formula, null)

        view.mvDialog.text = text

        builder.setView(view)
            .setTitle(resources.getString(R.string.correct_formula_dialog_title))
            .setPositiveButton(
                resources.getString(R.string.correct_formula_dialog_ok_btn)
            ) { _, _ -> testViewModel.nextFormula() }
            .create()
            .show()
    }

    override fun createFinalDialog(correctAnswers: Int, wrongAnswers: Int) {
        val builder = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.dialog_final, null)

        val entries = ArrayList<PieEntry>()

        if (correctAnswers != 0) {
            entries.add(
                PieEntry(
                    correctAnswers.toFloat(),
                    resources.getString(R.string.final_dialog_correct)
                )
            )
        }
        if (wrongAnswers != 0) {
            entries.add(
                PieEntry(
                    wrongAnswers.toFloat(),
                    resources.getString(R.string.final_dialog_wrong)
                )
            )
        }

        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.sliceSpace = 2f * scale
        pieDataSet.valueTextSize = 10 * scale
        pieDataSet.valueFormatter =
            IValueFormatter { value, _, _, _ ->
                value.toInt().toString()
            }
        val pieData = PieData(pieDataSet)
        when {
            correctAnswers == 0 -> pieDataSet.setColors(resources.getColor(R.color.colorWrong))
            wrongAnswers == 0 -> pieDataSet.setColors(resources.getColor(R.color.colorCorrect))
            else -> pieDataSet.setColors(
                resources.getColor(R.color.colorCorrect),
                resources.getColor(R.color.colorWrong)
            )
        }

        with(view.pieChart) {
            isDrawHoleEnabled = false
            val dis = Description()
            dis.text = ""
            description = dis
            setEntryLabelColor(resources.getColor(R.color.colorPieText))
            animateY(1000, Easing.EasingOption.EaseInOutCubic)
            data = pieData
            invalidate()
        }
        builder.setTitle(resources.getString(R.string.final_dialog_success))
            .setView(view)
            .setPositiveButton(resources.getString(R.string.final_dialog_exit)) { _, _ ->
                findNavController().popBackStack()
            }
            .create()
            .show()
    }
}
