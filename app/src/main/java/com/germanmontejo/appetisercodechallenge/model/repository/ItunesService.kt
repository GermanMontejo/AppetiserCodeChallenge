package com.germanmontejo.appetisercodechallenge.model.repository

import retrofit2.Call
import retrofit2.http.GET
import ItunesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

interface ItunesService {
    @GET("search")
    fun searchTracks(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String
    ): Call<ItunesResponse>

    companion object {
        private const val BASE_URL: String = "https://itunes.apple.com/"

        fun create(): ItunesService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build().create(ItunesService::class.java)
        }
    }
}