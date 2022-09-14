package com.example.gitdemoapp.network

import com.example.gitdemoapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * retrofit instance helper object
 **/
object RetrofitHelper {

    fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}