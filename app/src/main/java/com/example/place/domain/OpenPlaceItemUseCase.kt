package com.example.place.domain

class OpenPlaceItemUseCase(private val placeListRepository: PlaceListRepository) {
    fun openPlaceItem(placeItem: PlaceItem){
        placeListRepository.openPlaceItem(placeItem)
    }
}