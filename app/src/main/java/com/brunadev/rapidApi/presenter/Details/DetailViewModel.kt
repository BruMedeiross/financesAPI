package com.brunadev.rapidApi.presenter.Details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunadev.rapidApi.model.ResultApi

class DetailViewModel : ViewModel() {

    val indexDetail: LiveData<ResultApi> get() = _indexClicked
    private val _indexClicked = MutableLiveData<ResultApi>()

    fun setBookDetail(eventExtras: ResultApi) {
        _indexClicked.postValue(eventExtras)
    }

}