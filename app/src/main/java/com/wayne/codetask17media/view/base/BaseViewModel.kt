package com.wayne.codetask17media.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import org.koin.core.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}