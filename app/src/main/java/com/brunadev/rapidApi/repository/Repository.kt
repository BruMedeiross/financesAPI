package com.brunadev.rapidApi.repository

import com.brunadev.rapidApi.model.Welcome

interface Repository {

    suspend fun getDataAPI () : Welcome?
}
