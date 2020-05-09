package com.boilerplate.app.view.domain.repository

import io.reactivex.Single
import java.util.concurrent.TimeUnit

interface SampleLocalRepository {
    fun ping(): Single<Boolean>
}

class SampleLocalRepositoryImpl(): SampleLocalRepository{
    override fun ping(): Single<Boolean> {
        // TODO Fake
        return Single
            .timer(1000L, TimeUnit.MILLISECONDS)
            .map { true }
    }
}

