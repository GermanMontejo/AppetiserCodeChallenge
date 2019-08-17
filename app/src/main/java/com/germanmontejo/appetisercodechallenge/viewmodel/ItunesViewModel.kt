package com.germanmontejo.appetisercodechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.germanmontejo.appetisercodechallenge.model.repository.ItunesRepository
import ItunesResponse

class ItunesViewModel: ViewModel() {
    val itunesRepository = ItunesRepository()
    val itunesTrack: LiveData<ItunesResponse>
        get() = itunesRepository.getItunesLiveData()
}