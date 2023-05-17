package com.app.eacodingchallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.eacodingchallenge.models.Root
import com.app.eacodingchallenge.other.Resource
import com.app.eacodingchallenge.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _res = MutableLiveData<Resource<List<Root>>>()

    val res: LiveData<Resource<List<Root>>>
        get() = _res

    init {
        getFestivals()
    }

    fun getFestivals() = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getFestival().let {
            _res.postValue(it)
        }
    }

}