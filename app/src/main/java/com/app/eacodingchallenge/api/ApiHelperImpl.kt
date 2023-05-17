package com.app.eacodingchallenge.api

import com.app.eacodingchallenge.models.Root
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper {

    override suspend fun getFestivals(): Response<List<Root>>  = apiService.getFestivals()

}