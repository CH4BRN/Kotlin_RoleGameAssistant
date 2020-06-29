// File IdealsAdapter.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.ideals.ideal_toCheck

import android.content.Context
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
import com.uldskull.rolegameassistant.fragments.core.adapter.CustomRecyclerViewAdapter
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.core.utils.PictureUtil.Companion.resizePicture
import com.uldskull.rolegameassistant.models.DomainIdeal


/**
 *   Class "IdealsAdapter" :
 *   Adapter for ideals recycler view
 **/
class IdealsToCheckAdapter internal constructor(
    val context: Context
) :
    CustomRecyclerViewAdapter<DomainIdeal>(context) {
    companion object {
        private const val TAG = "IdealsToCheckAdapter"
    }

    /**  Inner class to display  **/
    inner class IdealsToCheckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var idealCheckedItemView: CheckBox = itemView.findViewById(R.id.chk_ideal)
        private var idealValueItemView: TextView = itemView.findViewById(R.id.tv_ideal)
        private var idealNameItemView: TextView =
            itemView.findViewById(R.id.activityEditIdeals_textView_idealTitle)
        var idealAlignmentItemView: ImageView = itemView.findViewById(R.id.img_alignment)
        private var idealEvilPoints: TextView = itemView.findViewById(R.id.tv_idealEvilPoints)
        private var idealGoodPoints: TextView = itemView.findViewById(R.id.tv_idealGoodPoints)
        private var idealOverlay: View = itemView.findViewById(R.id.ideal_overlay)

        init {
            idealOverlay.setOnClickListener {
                rowIndex = adapterPosition
                itemList[adapterPosition]?.isChecked = !itemList[adapterPosition]?.isChecked!!
                notifyDataSetChanged()
            }
        }

        /**
         * Bind the holder
         */
        fun bind(domainIdeal: DomainIdeal?) {
            idealValueItemView.maxWidth = 550
            idealNameItemView.text = domainIdeal?.idealName
            idealEvilPoints.text = domainIdeal?.idealEvilPoints.toString()
            idealGoodPoints.text = domainIdeal?.idealGoodPoints.toString()
            idealCheckedItemView.isChecked = domainIdeal?.isChecked!!
            val idealGoodPoints = domainIdeal?.idealGoodPoints
            val idealEvilPoints = domainIdeal?.idealEvilPoints

            setAlignmentIcon(idealEvilPoints, idealGoodPoints, this)
        }


    }

    /** Inflater  **/
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdealsToCheckViewHolder {
        Log.d("IdealsAdapter", "onCreateViewHolder")
        val itemView =
            layoutInflater.inflate(R.layout.fragment_idealtocheck_recyclerview_item, parent, false)

        return IdealsToCheckViewHolder(itemView)
    }


    /**
     * Set the alignment icon
     */
    private fun setAlignmentIcon(
        idealEvilPoints: Int?,
        idealGoodPoints: Int?,
        holder: IdealsToCheckViewHolder
    ) {
        if (idealEvilPoints != null && idealGoodPoints != null) {
            if (idealEvilPoints > idealGoodPoints) {
                holder.idealAlignmentItemView.setImageBitmap(
                    resizePicture(
                        BitmapFactory.decodeResource(context.resources, R.drawable.evil_icon),
                        250, 250
                    )
                )
            } else {
                holder.idealAlignmentItemView.setImageBitmap(
                    resizePicture(
                        BitmapFactory.decodeResource(context.resources, R.drawable.good_icon),
                        250, 250
                    )
                )
            }
        }
    }


    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as IdealsToCheckViewHolder).bind(itemList[position])
    }
}