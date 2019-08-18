package com.germanmontejo.appetisercodechallenge.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.germanmontejo.appetisercodechallenge.model.ItunesResponse
import com.germanmontejo.appetisercodechallenge.model.Result
import com.germanmontejo.appetisercodechallenge.model.repository.ItunesRepository
import com.germanmontejo.appetisercodechallenge.model.repository.ItunesTrack

class ItunesViewModel : ViewModel() {

    val itunesTrack: LiveData<ItunesResponse>
        get() = itunesRepository.getItunesLiveData()

    val allTracks: LiveData<List<ItunesTrack>>
        get() = itunesRepository.allTracks

    fun getItunesTrackById(trackId: Int): LiveData<ItunesTrack> {
        return itunesRepository.getItunesTrackById(trackId)
    }

    lateinit var itunesRepository: ItunesRepository
    fun initItunesRepo(app: Application) {
        itunesRepository = ItunesRepository(app)
    }

    fun insert(itunesResponse: ItunesResponse) {
        val itunesTracks = mutableListOf<ItunesTrack>()
        for (itunesResult in itunesResponse.itunesResults) {
            var itunesTrack = ItunesTrack(
                itunesResult.trackId,
                itunesResult.trackName,
                itunesResult.trackPrice.toString(),
                itunesResult.primaryGenreName,
                itunesResult.artworkUrl100,
                itunesResult.currency,
                itunesResult.longDescription

            )
            itunesTracks.add(itunesTrack)

        }
        itunesRepository.insert(itunesTracks)
    }

    fun composeItunesResponse(allTracks: List<ItunesTrack>): ItunesResponse {
        var itunesResults = mutableListOf<Result>()
        for (itunesTrack in allTracks) {
            var result = Result(
                trackId=itunesTrack.id,
                trackName=itunesTrack.trackName,
                trackPrice = itunesTrack.trackPrice.toDouble(),
                primaryGenreName = itunesTrack.genre,
                artworkUrl100 = itunesTrack.artworkUrl,
                currency = itunesTrack.currency,
                longDescription = itunesTrack.description
            )
            itunesResults.add(result)
        }
        return ItunesResponse(allTracks.size, itunesResults)
    }
}