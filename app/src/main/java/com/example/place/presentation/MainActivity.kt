package com.example.place.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.place.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var placeListAdapter: PlaceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.placeList.observe(this){
            placeListAdapter.submitList(it)
        }
    }

    private fun setUpRecyclerView(){
        val rvPlaceList = findViewById<RecyclerView>(R.id.rv_place_list)

        with(rvPlaceList){
            placeListAdapter = PlaceListAdapter()
            adapter = placeListAdapter

            //Ручная установка размера пула ViewType
            recycledViewPool.setMaxRecycledViews(PlaceListAdapter.VIEW_TYPE_ENABLED, PlaceListAdapter.MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(PlaceListAdapter.VIEW_TYPE_DISABLED, PlaceListAdapter.MAX_POOL_SIZE)
        }

        setUpLongClickListener()

        setUpClickListener()

        setUpSwipeListener(rvPlaceList)
    }

    private fun setUpSwipeListener(rvPlaceList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = placeListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deletePlaceItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvPlaceList)
    }

    private fun setUpClickListener() {
        placeListAdapter.onPlaceItemClickListener = {
            Toast.makeText(this, "Item pos: ${it.id}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpLongClickListener() {
        placeListAdapter.onPlaceItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
}