package com.germanmontejo.appetisercodechallenge.model.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItunesDao {
    @Query("SELECT * FROM itunes_track")
    fun getAllTracks(): LiveData<List<ItunesTrack>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itunesTrack: List<ItunesTrack>)

    @Query("SELECT * FROM itunes_track WHERE id = :trackId")
    fun getTrackById(trackId: Int): LiveData<ItunesTrack>
}