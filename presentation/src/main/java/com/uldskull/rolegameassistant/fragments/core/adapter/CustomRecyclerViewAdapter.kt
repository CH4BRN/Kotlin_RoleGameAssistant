// File CustomRecyclerViewAdapter.kt
// @Author pierre.antoine - 27/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.core.adapter

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

/**
 *   Class "CustomRecyclerViewAdapter" :
 *   Custom recycler view adapter
 **/
abstract class CustomRecyclerViewAdapter<U> internal constructor(
    context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    /**
     * Ensure that viewHolder has a onClickListener method
     */
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }

        return this
    }

    /**
     * Set the items taht will be displayed
     */
    fun setItems(itemsList:List<U>){
        this.itemList = itemsList.toMutableList()
        notifyDataSetChanged()
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return itemList.size
    }

    /** Inflater    **/
    val inflater: LayoutInflater = LayoutInflater.from(context)
    /**
     * Selected row index.
     */
    var rowIndex: Int = -1

    /**
     * List of displayed items
     */
    var itemList:MutableList<U> = mutableListOf()
}