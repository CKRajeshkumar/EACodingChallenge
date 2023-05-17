package com.app.eacodingchallenge.usecaseTest

import com.app.eacodingchallenge.api.ApiHelper
import com.app.eacodingchallenge.api.ApiHelperImpl
import com.app.eacodingchallenge.api.ApiService
import com.app.eacodingchallenge.models.Root
import com.app.eacodingchallenge.other.Resource
import com.app.eacodingchallenge.repository.MainRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class MainRepositoryTest {

    private lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var apiHelper: ApiHelper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        apiHelper = ApiHelperImpl(apiService)
        mainRepository = MainRepository(apiHelper)
    }

    @Test
    fun `get festivals`() {
        runBlocking {
            Mockito.`when`(apiService.getFestivals()).thenReturn(Response.success(listOf()))
            val response = mainRepository.getFestival()
            assertEquals(Resource.success(listOf<Root>()), Resource.success(response).data)
        }

    }

}