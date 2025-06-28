package com.brunadev.rapidApi.repository

import com.brunadev.rapidApi.model.Welcome
import com.brunadev.rapidApi.network.HttpClient
import okhttp3.ResponseBody

class RemoteDataSource {

    suspend fun eventList(region: String) : Welcome? {
        val result = HttpClient.eventApi.fetchEvents(region)
        return result
    }
}



