package com.application.kotlin.data.repository

import android.content.Context
import com.application.kotlin.R
import com.application.kotlin.data.remote.ApiCallback
import com.application.kotlin.utils.Utils
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

open class BaseRepository{

    @Inject
    lateinit var ctx : Context //auto init check AppModule
    open suspend fun <T> parseResponse(apiCall: suspend () -> Response<T>): ApiCallback<T> {

        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                ApiCallback.OnSuccess(response.body())
            } else {
                if (response.code() == 401) {
                    ApiCallback.OnError("Unauthorized")
                } else if (response.code() == 500) {
                    ApiCallback.OnError("Oops something went wrong")
                } else {
                    ApiCallback.OnError(response.message())
                }
            }
        } catch (exception: Exception) {
            ApiCallback.OnError(exception.message)
        }
    }

    fun getError(e : Exception) : String{
        return if (!Utils.isNetworkConnected(ctx)) {
            ctx.resources.getString(R.string.no_internet_connection)
        } else {
            e.toString()
        }
    }
}