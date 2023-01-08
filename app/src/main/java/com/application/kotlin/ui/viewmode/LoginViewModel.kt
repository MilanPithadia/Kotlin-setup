package com.application.kotlin.ui.viewmode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.kotlin.data.model.responsemodel.LoginResponse
import com.application.kotlin.data.remote.ApiCallback
import com.application.kotlin.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repository: LoginRepository) : ViewModel() {

    fun login(requestBody: RequestBody) : LiveData<ApiCallback<LoginResponse>>{
        val data = MutableLiveData<ApiCallback<LoginResponse>>()
        viewModelScope.launch {
            repository.login(requestBody).collect {
                data.postValue(it)
            }
        }
        return data;
    }
}