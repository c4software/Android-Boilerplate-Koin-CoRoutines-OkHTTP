package com.boilerplate.app.data.remote

import com.boilerplate.app.data.models.PingResult
import retrofit2.http.GET
import retrofit2.http.Headers

interface SampleRemoteDataSource {

    @GET("info/ping?content-type=application/json")
    @Headers("Content-type: application/json")
    suspend fun ping(): PingResult

}