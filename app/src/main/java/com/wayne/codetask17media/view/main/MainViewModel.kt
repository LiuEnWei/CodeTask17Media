package com.wayne.codetask17media.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wayne.codetask17media.view.base.BaseViewModel
import timber.log.Timber

class MainViewModel: BaseViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun startLoading() {
        Timber.e("startLoading()")
        _isLoading.postValue(true)
    }

    fun stopLoading() {
        Timber.e("stopLoading()")
        _isLoading.postValue(false)
    }
}