package com.boilerplate.app.domain.repository

import kotlinx.coroutines.delay

interface SampleLocalRepository {
    suspend fun ping(): String
}

class SampleLocalRepositoryImpl(): SampleLocalRepository{
    override suspend fun ping(): String {
        // Fake delay for demo purpose
        delay(1000L)
        return "Succeed Local"
    }
}

