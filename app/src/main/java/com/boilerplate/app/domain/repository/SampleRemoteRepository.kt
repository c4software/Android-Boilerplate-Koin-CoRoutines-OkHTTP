package com.boilerplate.app.domain.repository

import com.boilerplate.app.data.remote.SampleRemoteDataSource
import kotlinx.coroutines.delay

interface SampleRemoteRepository {
    suspend fun ping(): String
}

class SampleRemoteRemoteRepositoryImpl(private val sampleRemoteDataSource: SampleRemoteDataSource) :
    SampleRemoteRepository {

    override suspend fun ping(): String {
        delay(1000L)
        sampleRemoteDataSource.ping()
        return "Succeed Remote"
    }
}