package com.example.place.domain

class GetPlaceItemUseCase(private val placeListRepository: PlaceListRepository) {
    fun getPlaceItem(placeItemId: Int): PlaceItem{
        return placeListRepository.getPlaceItem(placeItemId)
    }
}