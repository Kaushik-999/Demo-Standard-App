package com.kaushik.demostandardapp.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaushik.demostandardapp.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<ApiState>(ApiState.Empty)
    val uiState = _uiState

    fun getPost(){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = ApiState.Loading

            repository.getPost()
                .catch { e->
                    Log.d("view_model",e.localizedMessage)
                    _uiState.value = ApiState.Error(e)
                }.collect { data->
                    _uiState.value = ApiState.Success(data)
                }

        }
    }
}