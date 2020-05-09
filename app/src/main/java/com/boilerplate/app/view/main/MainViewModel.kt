package com.boilerplate.app.view.main

import androidx.lifecycle.LiveData
import com.boilerplate.app.utils.coroutines.SchedulerProvider
import com.boilerplate.app.utils.coroutines.with
import com.boilerplate.app.utils.mvvm.RxViewModel
import com.boilerplate.app.utils.mvvm.SingleLiveEvent
import com.boilerplate.app.view.Error
import com.boilerplate.app.view.Pending
import com.boilerplate.app.view.Success
import com.boilerplate.app.view.ViewModelEvent
import com.boilerplate.app.view.domain.repository.SampleLocalRepository
import com.boilerplate.app.view.domain.repository.SampleRemoteRepository

class MainViewModel(
    private val sampleRemoteRepository: SampleRemoteRepository,
    private val sampleLocalRepository: SampleLocalRepository,
    private val schedulerProvider: SchedulerProvider
) : RxViewModel() {
    private val _events = SingleLiveEvent<ViewModelEvent>()
    val events: LiveData<ViewModelEvent> get() = _events

    fun doRemoteTestAction() {
        _events.value = Pending
        launch {
            sampleRemoteRepository
                .ping()
                .with(schedulerProvider)
                .subscribe(
                    { _events.value = Success },
                    { error -> _events.value = Error(error) })
        }
    }

    fun doLocalTestAction() {
        _events.value = Pending
        launch {
            sampleLocalRepository
                .ping()
                .with(schedulerProvider)
                .subscribe(
                    { _events.value = Success },
                    { error -> _events.value = Error(error) })
        }
    }
}