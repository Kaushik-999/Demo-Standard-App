package com.kaushik.demostandardapp.network

import com.kaushik.demostandardapp.network.models.Post
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET(Constants.BASIC_PATH)
    suspend fun getPost(): Post
}