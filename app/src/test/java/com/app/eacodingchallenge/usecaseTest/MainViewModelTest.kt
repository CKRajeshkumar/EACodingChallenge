package com.app.eacodingchallenge.usecaseTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.eacodingchallenge.data.FestivalMockData.emptyListItem
import com.app.eacodingchallenge.data.FestivalMockData.positiveList
import com.app.eacodingchallenge.other.Resource
import com.app.eacodingchallenge.repository.MainRepository
import com.app.eacodingchallenge.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {


    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var mainViewModel: MainViewModel

    private lateinit var mainRepository: MainRepository

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = mock(MainRepository::class.java)
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun getAllFestivalsTest() {
        runBlocking {
            Mockito.`when`(mainRepository.getFestival())
                .thenReturn(Resource.success(positiveList))
            mainViewModel.getFestivals()
            val result = mainViewModel.res.getOrAwaitValue()
            assertEquals(Resource.success(positiveList), result)
        }
    }


    @Test
    fun `empty festival list test`() {
        runBlocking {
            Mockito.`when`(mainRepository.getFestival())
                .thenReturn(Resource.success(emptyListItem))
            mainViewModel.getFestivals()
            val result = mainViewModel.res.getOrAwaitValue()
            assertEquals(Resource.success(emptyListItem), result)
        }
    }

}