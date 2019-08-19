package com.zelyder.mathtest.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int> = _index

    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }
    init {

    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}