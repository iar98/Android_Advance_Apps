package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelActivityViewModel : ViewModel() {
    // for change image
    var indexNumber: Int = 1
    private val _currentIndex: MutableLiveData<Int> = MutableLiveData()
    val currentIndex: LiveData<Int> = _currentIndex

    fun getNumber(): LiveData<Int> = currentIndex
    fun setNumber(int: Int) {
        _currentIndex.value = int
    }

    // for change text
    var indexNumber2: Int = 0
    private val _currentIndex2: MutableLiveData<Int> = MutableLiveData()
    val currentIndex2: LiveData<Int> = _currentIndex2

    fun getNumber2(): LiveData<Int> = currentIndex2
    fun setNumber2(int: Int) {
        _currentIndex2.value = int
    }
}
