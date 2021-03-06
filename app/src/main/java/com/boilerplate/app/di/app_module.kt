package com.boilerplate.app.di

import com.boilerplate.app.domain.repository.SampleLocalRepository
import com.boilerplate.app.domain.repository.SampleLocalRepositoryImpl
import com.boilerplate.app.domain.repository.SampleRemoteRemoteRepositoryImpl
import com.boilerplate.app.domain.repository.SampleRemoteRepository
import com.boilerplate.app.view.info.InfoViewModel
import com.boilerplate.app.view.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Inject dependencies for the MainViewModel (the only UI in this boilerplate)
    viewModel { MainViewModel(get(), get()) }
    viewModel { InfoViewModel(get()) }

    // Sample Remote Data Repository
    single<SampleRemoteRepository>(createdAtStart = true) { SampleRemoteRemoteRepositoryImpl(get()) }

    // Sample Local Data Repository
    single<SampleLocalRepository>(createdAtStart = true) { SampleLocalRepositoryImpl() }

}

val moduleApp = listOf(appModule, remoteDataSourceModule)