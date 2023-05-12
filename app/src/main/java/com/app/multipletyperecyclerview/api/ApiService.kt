package com.app.multipletyperecyclerview.api

import com.app.multipletyperecyclerview.models.Root
import retrofit2.Response
import retrofit2.http.GET

interface ApiService{

    @GET("festivals")
    suspend fun getFestivals():Response<List<Root>>


}