// File RacesAdapter.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.breed

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewAdapter
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed

/**
 *   Class "RacesAdapter" :
 *   Class that populates the breed recycler view
 **/
class BreedsAdapter internal constructor(
    context: Context,
    private val buttonListener: AdapterButtonListener<DomainBreed>
) : CustomRecyclerViewAdapter(context) {

    companion object {
        private const val TAG = "BreedsAdapter"
    }

    /**
     * Races list
     */
    private var breeds: MutableList<DomainBreed> = mutableListOf()
    /** Inflater    **/
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /**
     * View holder for races
     */
    inner class BreedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breedItemLayout: LinearLayout =
            itemView.findViewById<LinearLayout>(R.id.breed_item_linear_layout)
        val breedNameItemView: TextView = itemView.findViewById(R.id.tv_breedName)
        val breedDescriptionItemView: TextView = itemView.findViewById(R.id.tv_breedDescription)
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
        val itemView = inflater.inflate(R.layout.recyclerview_item_breed, parent, false)
        return BreedsViewHolder(itemView)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        // Log.d(TAG, "getItemCount")
        return breeds.size
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
        val breedsViewHolder = holder as BreedsViewHolder
        val current = breeds[position]
        breedsViewHolder.breedNameItemView.text = current.breedName
        breedsViewHolder.breedDescriptionItemView.text = current.breedDescription



        breedsViewHolder.breedItemLayout.setOnClickListener {
            rowIndex = position
            Log.d(TAG, "${breeds[position]}")
            breeds[position].breedChecked = !breeds[position].breedChecked
            Log.d(TAG, "${breeds[position]}")
            buttonListener.itemPressed(breeds[position])
            notifyDataSetChanged()
        }

        if (current.breedChecked) {
            breedsViewHolder.breedItemLayout.setBackgroundColor(Color.parseColor("#D98B43"))
            breedsViewHolder.breedNameItemView.setTextColor(Color.parseColor("#ffffff"))
        } else {
            breedsViewHolder.breedItemLayout.setBackgroundColor(Color.parseColor("#ffffff"))
            breedsViewHolder.breedNameItemView.setTextColor(Color.parseColor("#C02942"))
        }
    }

    /**
     * Set the races list content.
     */
    internal fun setBreeds(domainBreeds: MutableList<DomainBreed>) {
        Log.d(TAG, "setBreeds")
        domainBreeds.sortBy { b -> b.breedName }
        this.breeds = domainBreeds
        Log.d(TAG, "Breeds size = " + this.breeds.size.toString())
        notifyDataSetChanged()
    }
}