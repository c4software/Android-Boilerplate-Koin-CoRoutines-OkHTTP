package com.boilerplate.app.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.boilerplate.app.utils.mvvm.SingleLiveEvent
import com.boilerplate.app.domain.repository.SampleLocalRepository
import com.boilerplate.app.domain.repository.SampleRemoteRepository
import com.boilerplate.app.utils.mvvm.BaseViewModel
import com.boilerplate.app.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel(
    private val sampleRemoteRepository: SampleRemoteRepository,
    private val sampleLocalRepository: SampleLocalRepository
) : BaseViewModel() {
    private val _states = MutableLiveData<ViewModelState>()
    val states: LiveData<ViewModelState> get() = _states

    fun doRemoteTestAction() {
        _states.value = Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = sampleRemoteRepository.ping()
                _states.postValue(CallResult(result))
            } catch (err: Exception) {
                _states.postValue(Failed(err))
            }
        }
    }

    fun doLocalTestAction() {
        _states.value = Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = sampleLocalRepository.ping()
                _states.postValue(CallResult(result))
            } catch (err: Exception) {
                _states.postValue(Failed(err))
            }
        }
    }

    data class CallResult(val data: String) : ViewModelState()
}