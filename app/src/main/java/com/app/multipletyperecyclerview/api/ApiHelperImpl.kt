package com.app.multipletyperecyclerview.api

import com.app.multipletyperecyclerview.models.Root
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper {

    override suspend fun getFestivals(): Response<List<Root>>  = apiService.getFestivals()

}