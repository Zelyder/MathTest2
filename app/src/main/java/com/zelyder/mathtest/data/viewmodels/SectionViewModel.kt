package com.zelyder.mathtest.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zelyder.mathtest.ui.enums.SectionData
import com.zelyder.mathtest.ui.models.Section

class SectionViewModel: ViewModel() {
    var sectionList: MutableLiveData<List<Section>> = MutableLiveData()

    init {
        sectionList.value = SectionData.getSections()
    }

    fun getListSection() = sectionList

}