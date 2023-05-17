package com.app.eacodingchallenge.api

import com.app.eacodingchallenge.models.Root
import retrofit2.Response

interface ApiHelper {

    suspend fun getFestivals(): Response<List<Root>>

}