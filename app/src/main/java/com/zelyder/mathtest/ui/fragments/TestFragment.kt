package com.zelyder.mathtest.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.zelyder.mathtest.R
import com.zelyder.mathtest.data.viewmodels.MainViewModel
import com.zelyder.mathtest.data.viewmodels.TestAViewModel
import com.zelyder.mathtest.databinding.FragmentTestBinding
import com.zelyder.mathtest.help.ARG_SUBCATEGORY_ID
import com.zelyder.mathtest.help.FormulaUtilities
import com.zelyder.mathtest.help.setupMathView
import kotlinx.android.synthetic.main.fragment_test.*


class TestFragment : Fragment() {

    private val testViewModel by lazy { ViewModelProviders.of(this).get(TestAViewModel::class.java) }
    private val mainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTestBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_test, container, false)



        binding.lifecycleOwner = this
        binding.viewModel = testViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<Button>(R.id.button)?.visibility = View.GONE

        testViewModel.setKeys(FormulaUtilities().getKeys(arguments?.getInt(ARG_SUBCATEGORY_ID) ?: 1))

        math_view_item.setupMathView()
    }

}
