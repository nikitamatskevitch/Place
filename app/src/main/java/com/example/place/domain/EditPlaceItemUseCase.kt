package com.example.place.domain

class EditPlaceItemUseCase(private val placeListRepository: PlaceListRepository) {
    fun editPlaceItem(placeItem: PlaceItem){
        placeListRepository.editPlaceItem(placeItem)
    }
}