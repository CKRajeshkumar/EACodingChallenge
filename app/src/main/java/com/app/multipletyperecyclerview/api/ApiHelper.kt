package com.app.multipletyperecyclerview.api

import com.app.multipletyperecyclerview.models.Root
import retrofit2.Response

interface ApiHelper {

    suspend fun getEmployees(): Response<List<Root>>

}