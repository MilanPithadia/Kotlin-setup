package com.application.kotlin.data.remote

import com.application.kotlin.data.model.responsemodel.CountryResponse
import com.application.kotlin.data.model.responsemodel.LoginResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url


interface ApiInterface {

    //Retrofit
    @GET("samayo/country-json/master/src/country-by-capital-city.json")
    @Headers("Content-Type:application/json;charset=UTF-8")
    suspend fun getCountry() : Response<CountryResponse>

    @POST("UserLogin")
    @Headers("Content-Type:application/json;charset=UTF-8")
    suspend fun login(@Body requestBody: RequestBody) : Response<LoginResponse>
}