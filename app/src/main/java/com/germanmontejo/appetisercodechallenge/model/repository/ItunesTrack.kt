package com.germanmontejo.appetisercodechallenge.model.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itunes_track")
data class ItunesTrack (
    @PrimaryKey val id: Int,
    @ColumnInfo(name="track_name") val trackName: String,
    @ColumnInfo(name="track_price") val trackPrice: String,
    @ColumnInfo(name="track_genre") val genre: String,
    @ColumnInfo(name="artwork_url") val artworkUrl: String,
    @ColumnInfo(name="currency") val currency: String,
    @ColumnInfo(name="description") val description: String
)