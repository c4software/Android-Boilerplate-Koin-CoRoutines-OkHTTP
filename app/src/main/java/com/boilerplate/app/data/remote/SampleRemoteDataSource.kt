package com.boilerplate.app.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface SampleRemoteDataSource {

    @GET("/info/ping?content-type=application/json")
    @Headers("Content-type: application/json")
    fun ping(): Single<HashMap<String, Any>>

}