package com.boilerplate.app.view.domain.repository

import com.boilerplate.app.view.data.remote.SampleRemoteDataSource
import io.reactivex.Single
import java.util.concurrent.TimeUnit

interface SampleRemoteRepository {
    fun ping(): Single<String>
}

class SampleRemoteRemoteRepositoryImpl(private val sampleRemoteDataSource: SampleRemoteDataSource) :
    SampleRemoteRepository {
    override fun ping(): Single<String> {
        // TODO FAKE Delay
        return Single
            .timer(1000L, TimeUnit.MILLISECONDS)
            .flatMap { sampleRemoteDataSource.ping() }
            .map { "Ping Succeed" }
    }
}