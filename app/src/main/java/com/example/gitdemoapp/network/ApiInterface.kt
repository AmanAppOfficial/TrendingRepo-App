package com.example.gitdemoapp.network


import com.example.model.RepoModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("repositories")
    suspend fun getHomeData() : Response<List<RepoModel>>
}