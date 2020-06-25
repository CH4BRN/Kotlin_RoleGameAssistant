// File RacesAdapter.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.breed

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.core.AdapterListTransmitter
import com.uldskull.rolegameassistant.fragments.core.adapter.CustomRecyclerViewAdapter
import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed

/**
 *   Class "RacesAdapter" :
 *   Class that populates the breed recycler view
 **/
class BreedsToChooseAdapter internal constructor(
    val context: Context,
    private val listTransmitter: AdapterListTransmitter<DomainDisplayedBreed>
) : CustomRecyclerViewAdapter<DomainDisplayedBreed>(context) {

    companion object {
        private const val TAG = "BreedsAdapter"
    }


    /**
     * View holder for races
     */
    inner class BreedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val breedItemLayout: LinearLayout =
            itemView.findViewById(R.id.breed_item_linear_layout)
        private val breedNameItemView: TextView = itemView.findViewById(R.id.tv_breedName)
        private val breedDescriptionItemView: TextView =
            itemView.findViewById(R.id.tv_breedDescription)

        /**
         * Bind the value
         */
        fun bind(domainDisplayedBreed: DomainDisplayedBreed?) {

            Log.d(TAG, "Current : ${domainDisplayedBreed?.breedName}")
            breedNameItemView.text = domainDisplayedBreed?.breedName
            breedDescriptionItemView.text = domainDisplayedBreed?.breedDescription
            breedItemLayout.setOnClickListener {
                rowIndex = position

                itemList[position]?.breedIsChecked = !itemList[position]?.breedIsChecked!!


                listTransmitter.transmitList(itemList.toList())
                notifyDataSetChanged()
            }

            if (domainDisplayedBreed?.breedIsChecked!!) {
                breedItemLayout.setBackgroundColor(context.resources.getColor(R.color.colorPrimaryDark))
                breedNameItemView.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                breedDescriptionItemView.setTextColor(context.resources.getColor(R.color.textColorPrimary))
            } else {
                breedItemLayout.setBackgroundColor(context.resources.getColor(R.color.textColorPrimary))
                breedNameItemView.setTextColor(context.resources.getColor(R.color.colorAccent))
                breedDescriptionItemView.setTextColor(context.resources.getColor(R.color.colorAccent))
            }
        }
    }

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        val itemView =
            inflater.inflate(R.layout.fragment_breeds_simple_recyclerview_item, parent, false)
        return BreedsViewHolder(itemView)
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
        Log.d(TAG, "onBindViewHolder")
        (holder as BreedsViewHolder).bind(itemList[position])

    }


    /**
     * Set the races list content.
     */
    internal fun setBreeds(domainDisplayedBreeds: MutableList<DomainDisplayedBreed?>?) {
        Log.d(TAG, "setBreeds")

        domainDisplayedBreeds?.sortBy { b -> b?.breedName }
        domainDisplayedBreeds?.forEach {
            if (it != null) {
                if (this.itemList.contains(it)) {
                    val index = itemList.lastIndexOf(it)
                    this.itemList[index] = it
                } else {
                    this.itemList.add(it)
                }
            }

        }
        notifyDataSetChanged()
    }
}