package com.eratart.baristashandbook.baseui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.core.ext.applyBeforeAfter
import com.eratart.baristashandbook.core.ext.printError
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import com.eratart.baristashandbook.tools.resources.IResourceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

abstract class BaseViewModel(
    protected val resourceManager: IResourceManager,
    protected val appPreferences: IAppPreferences
) : ViewModel() {

    open fun onCreate() {}

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun setLoading(value: Boolean) {
        _isLoading.postValue(value)
    }

    protected fun <T> Flow<T>.applyLoader(): Flow<T> {
        return this.applyBeforeAfter({ setLoading(true) }, { setLoading(false) })
    }

    fun <T> Flow<T>.onError(
        showMessage: Boolean = true, listener: Throwable.() -> Unit
    ): Flow<T> {
        return this.catch { error ->
            listener.invoke(error)
            error.printError()
            if (showMessage) {
                _message.postValue(resourceManager.getString(R.string.app_smth_went_wrong))
            }
        }
    }
}