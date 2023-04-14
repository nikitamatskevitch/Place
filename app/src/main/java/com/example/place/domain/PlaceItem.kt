package com.example.place.domain

data class PlaceItem(
    val name: String,
    val rating: Int,
    val adress: String,
    val description: String,
    var id: Int = UNDEFINED_ID
)
{
    companion object{
        const val  UNDEFINED_ID = -1
    }
}