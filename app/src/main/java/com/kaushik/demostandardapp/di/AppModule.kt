package com.kaushik.demostandardapp.di

import com.kaushik.demostandardapp.main.MainRepository
import com.kaushik.demostandardapp.network.Constants
import com.kaushik.demostandardapp.network.PostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
private val okHttp = OkHttpClient.Builder().callTimeout(5,TimeUnit.SECONDS).addInterceptor(logger)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiService(): PostService =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())
            .build()
            .create(PostService::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(apiService: PostService): MainRepository = MainRepository(apiService)

}