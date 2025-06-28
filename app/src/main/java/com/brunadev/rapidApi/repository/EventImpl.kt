package com.brunadev.rapidApi.repository

import com.brunadev.rapidApi.constant.EventConstants.API_CODE
import com.brunadev.rapidApi.constant.EventConstants.API_KEY
import com.brunadev.rapidApi.model.Welcome

import io.reactivex.disposables.CompositeDisposable

class EventImpl : Repository {

    private val remoteDataSource = RemoteDataSource()
    private val compositeDisposable = CompositeDisposable()

    override suspend fun getDataAPI(): Welcome? =
        remoteDataSource.eventList(API_CODE)
}

