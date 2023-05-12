package com.app.multipletyperecyclerview.repository

import com.app.multipletyperecyclerview.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
){

    suspend fun getFestival() = apiHelper.getFestivals()

}