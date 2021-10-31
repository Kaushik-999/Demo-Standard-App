package com.kaushik.demostandardapp.main

import com.kaushik.demostandardapp.network.PostService
import com.kaushik.demostandardapp.network.models.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: PostService
) {

    fun getPost(): Flow<Post> = flow{
        emit(apiService.getPost())
    }.flowOn(Dispatchers.IO)
}