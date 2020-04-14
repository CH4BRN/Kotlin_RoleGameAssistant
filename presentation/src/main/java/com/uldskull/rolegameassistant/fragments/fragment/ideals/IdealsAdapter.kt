// File IdealsAdapter.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.ideals

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.models.character.DomainIdeal


/**
 *   Class "IdealsAdapter" :
 *   Adapter for ideals recycler view
 **/
class IdealsAdapter internal constructor(
    val context: Context,
    private val buttonListener: AdapterButtonListener<DomainIdeal>
) :
    RecyclerView.Adapter<IdealsAdapter.IdealsViewHolder>() {
    /** Inflater  **/
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    /**  Ideals list  **/
    private var ideals = emptyList<DomainIdeal>()


    /**  Inner class to display  **/
    inner class IdealsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var idealCheckedItemView: CheckBox = itemView.findViewById(R.id.chk_ideal)
        var idealValueItemView: TextView = itemView.findViewById(R.id.tv_ideal)
        var idealNameItemView: TextView = itemView.findViewById(R.id.tv_idealTitle)
        var idealAlignmentItemView: ImageView = itemView.findViewById(R.id.img_alignment)
        var idealEvilPoints: TextView = itemView.findViewById(R.id.tv_idealEvilPoints)
        var idealGoodPoints: TextView = itemView.findViewById(R.id.tv_idealGoodPoints)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdealsViewHolder {
        Log.d("IdealsAdapter", "onCreateViewHolder")
        val itemView = layoutInflater.inflate(R.layout.recyclerview_item_ideal, parent, false)


        return IdealsViewHolder(itemView)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        Log.d("IdealsAdapter", "getItemCount")
        return ideals.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [IdealsViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [IdealsViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: IdealsViewHolder, position: Int) {
        val current = ideals[position]

        holder.idealValueItemView.maxWidth = 550
        holder.idealNameItemView.text = current.idealName
        holder.idealEvilPoints.text = current.idealEvilPoints.toString()
        holder.idealGoodPoints.text = current.idealGoodPoints.toString()

        val idealGoodPoints = current.idealGoodPoints
        val idealEvilPoints = current.idealEvilPoints
        if (idealEvilPoints != null && idealGoodPoints != null) {
            if (idealEvilPoints > idealGoodPoints) {
                holder.idealAlignmentItemView.setImageBitmap(
                    resizeImage(
                        BitmapFactory.decodeResource(context.resources, R.drawable.evil_icon),
                        250, 250
                    )
                )
            } else {
                holder.idealAlignmentItemView.setImageBitmap(
                    resizeImage(
                        BitmapFactory.decodeResource(context.resources, R.drawable.good_icon),
                        250, 250
                    )
                )
            }
        }



        holder.idealCheckedItemView.setOnClickListener {
            Log.d(
                "ideal", current.idealName +
                        when (holder.idealCheckedItemView.isChecked) {
                            true -> " is checked"
                            else -> " is unchecked"
                        }
            )
            when (holder.idealCheckedItemView.isChecked) {
                true -> {
                    current.isChecked = true
                }
                else -> {
                    current.isChecked = false
                }
            }

            this.buttonListener.itemPressed(current)

        }


    }

    private fun resizeImage(bitmap: Bitmap, width: Int, height: Int): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, width, height, false)
    }

    /**
     * Set the displayed bonds.
     */
    fun setIdeals(ideals: List<DomainIdeal>) {
        this.ideals = ideals
        notifyDataSetChanged()
    }
}