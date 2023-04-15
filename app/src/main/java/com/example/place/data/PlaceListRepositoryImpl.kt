package com.example.place.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.place.R
import com.example.place.domain.PlaceItem
import com.example.place.domain.PlaceListRepository

object PlaceListRepositoryImpl: PlaceListRepository {

    private val placeListLD = MutableLiveData<List<PlaceItem>>()
    private val placeList = sortedSetOf<PlaceItem>({ o1, o2 -> o1.id.compareTo(o2.id) })

    private var autoIncrementId = 0

    init{
        val item = PlaceItem(R.drawable.ic_launcher_background,"Ресторан 0", 5, "Минск, Беларусь", "Уютное местечко для двоих", true)
        addPlaceItem(item)

        val item1 = PlaceItem(R.drawable.ic_launcher_background,"Парк 1", 5, "Минск, Беларусь", "Романтичная цветочная тропа из васильков", true)
        addPlaceItem(item1)

        val item2 = PlaceItem(R.drawable.ic_launcher_background,"Кино 2", 4, "Минск, Беларусь", "Задний ряд - лучшее завершение дня", true)
        addPlaceItem(item2)
    }

    override fun addPlaceItem(placeItem: PlaceItem) {
        if(placeItem.id == PlaceItem.UNDEFINED_ID){
            placeItem.id = autoIncrementId++
        }

        placeList.add(placeItem)
        updateList()
    }

    override fun deletePlaceItem(placeItem: PlaceItem) {
        placeList.remove(placeItem)
        updateList()
    }

    override fun editPlaceItem(placeItem: PlaceItem) {
        val oldElement = getPlaceItem(placeItem.id)
        placeList.remove(oldElement)
        addPlaceItem(placeItem)
    }

    override fun openPlaceItem(placeItem: PlaceItem) {
        TODO("Not yet implemented")
    }

    override fun getPlaceItem(placeItemId: Int): PlaceItem {
        return placeList.find { it.id == placeItemId
        } ?: throw java.lang.RuntimeException("Element with id $placeItemId not found")
    }

    override fun getPlaceList(): LiveData<List<PlaceItem>> {
        return placeListLD
    }

    private fun updateList(){
        placeListLD.value = placeList.toList()
    }

}