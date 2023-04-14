package com.example.place.domain

import androidx.lifecycle.LiveData

class GetPlaceListUseCase(private val placeListRepository: PlaceListRepository) {
    fun getPlaceList(): LiveData<List<PlaceItem>> {
        return placeListRepository.getPlaceList()
    }
}