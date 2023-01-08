package com.application.kotlin.di

import com.application.kotlin.data.remote.ApiInterface
import com.application.kotlin.utils.Const
import com.application.kotlin.utils.Const.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    private fun getClient(): OkHttpClient {
        val client = OkHttpClient().newBuilder()
        val log = HttpLoggingInterceptor();
        log.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(log)

        if (Const.TOKEN.isNotEmpty()) {
            val interceptor = Interceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${Const.TOKEN}")
                    .build()
                chain.proceed(newRequest)
            }
            client.addInterceptor(interceptor)
        }
        return client.build()
    }
}