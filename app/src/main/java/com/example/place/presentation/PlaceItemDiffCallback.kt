package com.example.place.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.place.domain.PlaceItem

class PlaceItemDiffCallback: DiffUtil.ItemCallback<PlaceItem>() {
    override fun areItemsTheSame(oldItem: PlaceItem, newItem: PlaceItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PlaceItem, newItem: PlaceItem): Boolean {
        //должны сравнить два объекта по полям, но поскольку это data-классы здесь в отличии от Java можно неявно вызывать метод equals
        return oldItem == newItem
    }

}