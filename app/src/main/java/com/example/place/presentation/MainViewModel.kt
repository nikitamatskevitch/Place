package com.example.place.presentation

import androidx.lifecycle.ViewModel
import com.example.place.data.PlaceListRepositoryImpl
import com.example.place.domain.*

class MainViewModel: ViewModel() {
    private val repository = PlaceListRepositoryImpl

    private val getPlaceListUseCase = GetPlaceListUseCase(repository)
    private val deletePlaceItemUseCase = DeletePlaceItemUseCase(repository)
    private val openPlaceItemUseCase = OpenPlaceItemUseCase(repository)
    private val editPlaceItemUseCase = EditPlaceItemUseCase(repository)

    val placeList = getPlaceListUseCase.getPlaceList()

    fun deletePlaceItem(placeItem: PlaceItem){
        deletePlaceItemUseCase.deletePlaceItem(placeItem)
    }

    fun changeEnableState(placeItem: PlaceItem){
        val newItem = placeItem.copy(enabled = !placeItem.enabled) // изменение состояния enabled у копии объекта
        editPlaceItemUseCase.editPlaceItem(newItem)
    }
}