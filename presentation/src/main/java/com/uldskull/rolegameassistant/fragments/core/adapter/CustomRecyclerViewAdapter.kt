// File CustomRecyclerViewAdapter.kt
// @Author pierre.antoine - 27/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.core.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

/**
 *   Class "CustomRecyclerViewAdapter" :
 *   Custom recycler view adapter
 **/
abstract class CustomRecyclerViewAdapter internal constructor(
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
     * Selected row index.
     */
    var rowIndex: Int = -1
}