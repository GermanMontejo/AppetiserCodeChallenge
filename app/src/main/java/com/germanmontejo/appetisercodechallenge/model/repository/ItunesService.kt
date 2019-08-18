package com.germanmontejo.appetisercodechallenge.model.repository

import com.germanmontejo.appetisercodechallenge.model.ItunesResponse
import retrofit2.Call
import retrofit2.http.GET
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

    // Here we ensure that our ItunesService is a singleton to avoid
    // recreating it multiple times.
    companion object {
        @Volatile
        private var INSTANCE: ItunesService? = null
        private const val BASE_URL: String = "https://itunes.apple.com/"

        fun create(): ItunesService {
            val tempService = INSTANCE
            if (tempService != null) return tempService
            synchronized(this) {
                val instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build().create(ItunesService::class.java)
                INSTANCE = instance
                return instance
            }

        }
    }
}