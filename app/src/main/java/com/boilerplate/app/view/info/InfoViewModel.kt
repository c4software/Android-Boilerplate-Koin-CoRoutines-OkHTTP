package com.boilerplate.app.view.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.boilerplate.app.domain.repository.SampleRemoteRepository
import com.boilerplate.app.utils.mvvm.BaseViewModel
import com.boilerplate.app.view.Failed
import com.boilerplate.app.view.Loading
import com.boilerplate.app.view.ViewModelState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InfoViewModel(
    private val sampleRemoteRepository: SampleRemoteRepository,
) : BaseViewModel() {
    val states = MutableLiveData<ViewModelState>()

    data class GetVersionResult(val version: String) : ViewModelState()

    fun getVersion() {
        states.postValue(Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val version = sampleRemoteRepository.getVersion()
                states.postValue(GetVersionResult(version))
            } catch (err: Exception) {
                states.postValue(Failed(err))
            }
        }
    }

}