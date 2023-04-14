package com.example.place.domain

import androidx.lifecycle.LiveData

interface PlaceListRepository {

    fun addPlaceItem(placeItem: PlaceItem)

    fun deletePlaceItem(placeItem: PlaceItem)

    fun openPlaceItem(placeItem: PlaceItem)

    fun getPlaceItem(placeItemId: Int): PlaceItem

    fun getPlaceList(): LiveData<List<PlaceItem>>
}