package com.example.place.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.place.R
import com.example.place.domain.PlaceItem

class PlaceListAdapter: ListAdapter<PlaceItem, PlaceListAdapter.PlaceItemViewHolder>(PlaceItemDiffCallback()){

    var onPlaceItemLongClickListener: ((PlaceItem) -> Unit)? = null
    var onPlaceItemClickListener: ((PlaceItem) -> Unit)? = null

    //как создавать View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceItemViewHolder {
        val layout = when(viewType){
            VIEW_TYPE_ENABLED -> R.layout.item_place_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_place_disabled
            else -> throw java.lang.RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return PlaceItemViewHolder(view)
    }

    //как вставить значения внутри View
    override fun onBindViewHolder(viewHolder: PlaceItemViewHolder, position: Int) {
        val placeItem = getItem(position)

        viewHolder.itemView.setOnLongClickListener{
            onPlaceItemLongClickListener?.invoke(placeItem)
            true
        }

        viewHolder.itemView.setOnClickListener{
            onPlaceItemClickListener?.invoke(placeItem)
            true
        }

        viewHolder.tvImage.setImageResource(placeItem.image)
        viewHolder.tvName.text = placeItem.name
        viewHolder.tvRating.text = placeItem.rating.toString() + " ★"
        viewHolder.tvAdress.text = placeItem.adress
        viewHolder.tvDescription.text = placeItem.description
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)

        return if (item.enabled){
            VIEW_TYPE_ENABLED
        }else{
            VIEW_TYPE_DISABLED
        }
    }

    class PlaceItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvImage = view.findViewById<ImageView>(R.id.iv_image)
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvRating = view.findViewById<TextView>(R.id.tv_rating)
        val tvAdress = view.findViewById<TextView>(R.id.tv_address)
        val tvDescription = view.findViewById<TextView>(R.id.tv_description)
    }

    companion object{
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0

        //в зависимости от устройства нужно искать компромисс, какой запас пула создавать
        const val MAX_POOL_SIZE = 30
    }
}