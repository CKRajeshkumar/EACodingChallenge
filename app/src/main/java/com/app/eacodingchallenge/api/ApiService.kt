package com.app.eacodingchallenge.api

import com.app.eacodingchallenge.models.Root
import retrofit2.Response
import retrofit2.http.GET

interface ApiService{

    @GET("festivals")
    suspend fun getFestivals():Response<List<Root>>


}