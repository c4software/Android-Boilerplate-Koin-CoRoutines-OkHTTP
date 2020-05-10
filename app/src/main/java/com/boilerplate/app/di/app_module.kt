package com.boilerplate.app.di

import com.boilerplate.app.utils.coroutines.ApplicationSchedulerProvider
import com.boilerplate.app.utils.coroutines.SchedulerProvider
import com.boilerplate.app.domain.repository.SampleLocalRepository
import com.boilerplate.app.domain.repository.SampleLocalRepositoryImpl
import com.boilerplate.app.domain.repository.SampleRemoteRepository
import com.boilerplate.app.domain.repository.SampleRemoteRemoteRepositoryImpl
import com.boilerplate.app.view.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get(), get(), get()) }

    // Sample Remote Data Repository
    single<SampleRemoteRepository>(createdAtStart = true) { SampleRemoteRemoteRepositoryImpl(get()) }

    // Sample Local Data Repository
    single<SampleLocalRepository>(createdAtStart = true) { SampleLocalRepositoryImpl() }

    // Rx Schedulers
    single<SchedulerProvider>(createdAtStart = true) { ApplicationSchedulerProvider() }
}

val moduleApp = listOf(appModule, remoteDataSourceModule)