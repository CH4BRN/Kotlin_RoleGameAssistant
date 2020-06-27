// CustomViewHolder.kt created by UldSkull - 25/06/2020

package com.uldskull.rolegameassistant.fragments.core.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
Class "CustomViewHolder"

Custom abstract view holder.
 */
abstract class CustomViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    /**
     * Bind the view holder
     */
    abstract fun bind(domainModel: T?)
}