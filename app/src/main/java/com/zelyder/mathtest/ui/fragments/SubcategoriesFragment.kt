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
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdRequestError
import com.yandex.mobile.ads.InterstitialAd
import com.yandex.mobile.ads.InterstitialEventListener
import com.yandex.mobile.ads.InterstitialEventListener.SimpleInterstitialEventListener
import com.zelyder.mathtest.R
import com.zelyder.mathtest.data.viewmodels.MainViewModel
import com.zelyder.mathtest.ui.adapters.SubcategoriesListAdapter

class SubcategoriesFragment : Fragment() {

    val TAG = "SubcategoriesFragment"
    var isBack = false
    lateinit var mAdRequest: AdRequest
    lateinit var mInterstitialAd: InterstitialAd

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

        initInterstitialAd()

        if(args?.indexBack == 1 && !isBack){
            mInterstitialAd.loadAd(mAdRequest)
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

    override fun onDestroyView() {
        super.onDestroyView()
        mInterstitialAd.destroy()
    }

    private fun initInterstitialAd() {
        mInterstitialAd = InterstitialAd(requireContext())
        mInterstitialAd.blockId = resources.getString(R.string.interstitial_ad_yandex_meditation_id)
        mAdRequest = AdRequest.builder().build()
        mInterstitialAd.interstitialEventListener = mInterstitialAdEventListener
    }

    private val mInterstitialAdEventListener: InterstitialEventListener =
        object : SimpleInterstitialEventListener() {
            override fun onInterstitialLoaded() {
                mInterstitialAd.show()
            }

            override fun onInterstitialFailedToLoad(error: AdRequestError) {}
        }
}

