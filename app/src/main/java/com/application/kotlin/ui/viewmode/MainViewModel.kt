package com.application.kotlin.ui.viewmode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.kotlin.data.model.responsemodel.CountryResponse
import com.application.kotlin.data.remote.ApiCallback
import com.application.kotlin.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: MainRepository) : ViewModel() {

    fun getCountry(): LiveData<ApiCallback<CountryResponse>> {
        val list = MutableLiveData<ApiCallback<CountryResponse>>()
        viewModelScope.launch {
            repository.getCountry().collect {
                list.postValue(it)
            }
        }
        return list
    }
}