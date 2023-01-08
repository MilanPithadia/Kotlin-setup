package com.application.kotlin.data.repository

import com.application.kotlin.data.model.responsemodel.CountryResponse
import com.application.kotlin.data.model.responsemodel.LoginResponse
import com.application.kotlin.data.remote.ApiCallback
import com.application.kotlin.data.remote.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.RequestBody
import javax.inject.Inject

class LoginRepository @Inject constructor(private val remote: ApiInterface) : BaseRepository() {

    fun login(requestBody: RequestBody): Flow<ApiCallback<LoginResponse>> =
        flow {
            try {
                val call = remote.login(requestBody)
                val response = parseResponse { call }
                emit(response)
            } catch (e: Exception) {
                emit(ApiCallback.OnError(getError(e)))
            }
        }.flowOn(Dispatchers.IO)
}