package com.example.place.domain

class AddPlaceItemUseCase(private val placeListRepository: PlaceListRepository) {
    fun addPlaceItem(placeItem: PlaceItem){
        placeListRepository.addPlaceItem(placeItem)
    }
}