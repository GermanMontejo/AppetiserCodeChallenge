package com.germanmontejo.appetisercodechallenge.model.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.germanmontejo.appetisercodechallenge.model.ItunesResponse
import com.germanmontejo.appetisercodechallenge.model.Result
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItunesRepository(application: Application) {
    private val itunesDao = ItunesDatabase.getDatabase(application).itunesDao()
    private val term = "star"
    private val country = "au"
    private val media = "movie"

    private val itunesService = ItunesService.create()
    private var mutableItunesLiveData = MutableLiveData<ItunesResponse>()
    val allTracks: LiveData<List<ItunesTrack>> = itunesDao.getAllTracks()

    fun getItunesTrackById(trackId: Int): LiveData<ItunesTrack> {
        return itunesDao.getTrackById(trackId)
    }

    fun insert(itunesTracks: List<ItunesTrack>) {
        itunesDao.insert(itunesTracks)
    }

    fun getItunesLiveData(): MutableLiveData<ItunesResponse> {
        val request = itunesService.searchTracks(term, country, media)
        request.enqueue(object : Callback<ItunesResponse> {
            /*
            * When the retrieval fails, e.g. no internet connection, we're going
            * to create a dummy itunesResponse data and supply it with a 0 resultCount
            * and an empty itunesRes. We're doing this so the mutableItunesLiveData
            * will still be "observed" and we'll be able to fetch the data from the local db.
            */
            override fun onFailure(call: Call<ItunesResponse>, t: Throwable) {
                val itunesRes = mutableListOf<Result>()
                val itunesResponse = ItunesResponse(0, itunesRes)
                mutableItunesLiveData.value = itunesResponse
            }

            override fun onResponse(call: Call<ItunesResponse>, response: Response<ItunesResponse>) {
                mutableItunesLiveData.value = response.body()
            }
        })
        return mutableItunesLiveData
    }
}