package com.boilerplate.app.domain.repository

import com.boilerplate.app.data.remote.SampleRemoteDataSource
import kotlinx.coroutines.delay

interface SampleRemoteRepository {
    suspend fun ping(): String
    suspend fun getVersion(): String
}

class SampleRemoteRemoteRepositoryImpl(private val sampleRemoteDataSource: SampleRemoteDataSource) :
    SampleRemoteRepository {

    override suspend fun ping(): String {
        delay(1000L)
        val result = sampleRemoteDataSource.ping()
        return if (result.isSuccess()) {
            "Succeed Remote"
        } else {
            "Failed Remote"
        }
    }

    override suspend fun getVersion(): String {
        return sampleRemoteDataSource.restInfo().release
    }
}