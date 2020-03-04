// File BondsAdapter.kt
// @Author pierre.antoine - 02/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bonds

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R

/**
 *   Class "BondsAdapter" :
 *   Adapter for bonds recycler view.
 **/
class BondsAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<BondsAdapter.BondsViewHolder>() {

    /** Inflater    **/
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /** Bonds list **/
    private var bonds = emptyList<String>()

    /** Inner class to display **/
    inner class BondsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bondValueItemView: TextView = itemView.findViewById(R.id.tv_bond)
        var bondDeleteItemView: Button = itemView.findViewById(R.id.btn_deleteBond)
    }

    /**
     * Called when the view holder is created.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BondsViewHolder {

        val itemView = inflater.inflate(R.layout.recyclerview_item_bond, parent, false)
        return BondsViewHolder(itemView)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return bonds.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [BondsViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [BondsViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: BondsViewHolder, position: Int) {
        val current = bonds[position]
        holder.bondValueItemView.text = current

        holder.bondDeleteItemView.setOnClickListener {

            Log.i("ADAPTER", bonds[position])

            setBonds(bonds.minus(bonds[position]))
            Log.i("ADAPTER", bonds.size.toString())
        }
    }

    /**
     * Set the displayed bonds.
     */
    fun setBonds(bonds: List<String>) {
        this.bonds = bonds
        notifyDataSetChanged()
    }
}