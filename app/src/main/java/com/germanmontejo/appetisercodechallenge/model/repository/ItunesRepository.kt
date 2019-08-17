package com.germanmontejo.appetisercodechallenge.model.repository

import ItunesResponse
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItunesRepository {
    private val term = "star"
    private val country = "au"
    private val media = "movie"

    private val itunesService by lazy {
        ItunesService.create()
    }

    private var mutableItunesLiveData = MutableLiveData<ItunesResponse>()

    fun getItunesLiveData(): MutableLiveData<ItunesResponse> {
        val request = itunesService.searchTracks(term, country, media)
        request.enqueue(object : Callback<ItunesResponse> {
            override fun onFailure(call: Call<ItunesResponse>, t: Throwable) {
                Log.d("itemlist", "onFailure(): ${t.message}")
            }

            override fun onResponse(call: Call<ItunesResponse>, response: Response<ItunesResponse>) {
                mutableItunesLiveData.value = response.body()
                Log.d("itemlist:", "${response.headers()}")
            }
        })
        return mutableItunesLiveData
    }
}