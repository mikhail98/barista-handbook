package com.eratart.baristashandbook.baseui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eratart.baristashandbook.domain.preferences.IAppPreferences

abstract class BaseViewModel(protected val appPreferences: IAppPreferences) : ViewModel() {

    open fun onCreate(){}

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setLoading(value: Boolean) {
        _isLoading.postValue(value)
    }
}