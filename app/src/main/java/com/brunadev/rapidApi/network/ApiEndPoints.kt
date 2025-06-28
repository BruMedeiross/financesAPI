package com.brunadev.rapidApi.commom

import com.brunadev.rapidApi.model.Welcome
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoints {

    @GET("market/v2/get-summary")
    suspend fun fetchEvents(
       @Query("region") region: String
    ): Welcome?
}