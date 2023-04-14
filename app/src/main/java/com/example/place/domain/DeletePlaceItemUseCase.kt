package com.example.place.domain

class DeletePlaceItemUseCase(private val placeListRepository: PlaceListRepository) {
    fun deletePlaceItem(placeItem: PlaceItem){
        placeListRepository.deletePlaceItem(placeItem)
    }
}