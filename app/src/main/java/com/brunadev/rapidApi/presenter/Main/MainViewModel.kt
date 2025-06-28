package com.brunadev.rapidApi.presenter.Main

import androidx.lifecycle.*
import com.brunadev.rapidApi.model.Welcome
import com.brunadev.rapidApi.repository.Repository
import kotlinx.coroutines.launch

class  MainViewModel(private val repository: Repository) : ViewModel(){

    private val _listState = MutableLiveData<Welcome?>()
    val listState: LiveData<Welcome?> get() = _listState

    fun init() {
        viewModelScope.launch {
            val responseAPI = repository.getDataAPI()
            _listState.value = responseAPI
        }
    }
}







