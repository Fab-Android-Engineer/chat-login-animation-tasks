package com.example.fabrapptrassessment.di

import com.example.fabrapptrassessment.BASE_URL
import com.example.fabrapptrassessment.chat.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providesApiService() : ApiService { // expose retrofit service
        // retrofit object with gson converter
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

}