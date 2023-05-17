package com.app.eacodingchallenge.usecaseTest

import com.app.eacodingchallenge.api.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService
    private lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService::class.java)
    }


    @Test
    fun `get all festivals`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("[]"))
            val response = apiService.getFestivals()
            val request = mockWebServer.takeRequest()
            assertEquals("/festivals", request.path)
            assertEquals(true, response.body()?.isEmpty() == true)
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}