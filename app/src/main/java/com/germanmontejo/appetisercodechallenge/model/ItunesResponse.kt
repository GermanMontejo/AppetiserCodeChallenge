package com.germanmontejo.appetisercodechallenge.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ItunesResponse(
    val resultCount: Int,
    @SerializedName("results") val itunesResults: List<Result>
)

// I've reduced the size fields of the Result class since these are the only
// fields I need.
data class Result(
    val trackId: Int,
    val trackName: String,
    val trackPrice: Double,
    val primaryGenreName: String,
    val artworkUrl100: String,
    val currency: String,
    val longDescription: String
    )
