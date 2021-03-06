// File CustomRecyclerViewAdapter.kt
// @Author pierre.antoine - 27/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

/**
 *   Class "CustomRecyclerViewAdapter" :
 *   TODO: Fill class use.
 **/
abstract class CustomRecyclerViewAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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