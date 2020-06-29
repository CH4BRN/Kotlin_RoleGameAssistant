// BreedsNameAdapter.kt created by UldSkull - 25/06/2020

package com.uldskull.rolegameassistant.fragments.fragment.breed

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.core.adapter.CustomRecyclerViewAdapter
import com.uldskull.rolegameassistant.fragments.core.adapter.CustomViewHolder
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed
import kotlinx.android.synthetic.main.fragment_breeds_name_recyclerview_item.view.*

/**
Class "BreedsNameAdapter"

Adapter to display breeds that will be edited
 */
class BreedsNamesAdapter internal constructor(
    val context: Context,
    private val editBreedsActivity_customButtonListener:CustomAdapterButtonListener<DomainDisplayedBreed>
) : CustomRecyclerViewAdapter<DomainDisplayedBreed>(context) {

    companion object {
        private const val TAG = "BreedsNameAdapter"
    }

    /**
     * View holder for breeds
     */
    inner class BreedsNameViewHolder(itemView: View) :
        CustomViewHolder<DomainDisplayedBreed>(itemView) {
        private val textViewBreedName: TextView =
            itemView?.findViewById(R.id.fragmentBreedNameRecyclerViewItem_textView_breedName)
        val viewBreedNameOverlay:View =
            itemView?.findViewById(R.id.fragmentBreedNameRecyclerViewItem_view_overlay)

        init {
            viewBreedNameOverlay.setOnClickListener {
                var current = itemList[adapterPosition]
                Log.d("DEBUG$TAG", "Overlay ${current.breedName}")
                textViewBreedName.setBackgroundColor(context.resources.getColor(R.color.colorPrimaryDark))
                textViewBreedName.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                itemList[adapterPosition]?.breedIsChecked = !itemList[adapterPosition]?.breedIsChecked

                if(checkedPosition != adapterPosition){
                    notifyItemChanged(checkedPosition)
                    checkedPosition = adapterPosition
                }
                editBreedsActivity_customButtonListener.itemPressed(itemList[adapterPosition])
            }
        }


        /**
         * Bind the view holder
         */
        override fun bind(domainModel: DomainDisplayedBreed?) {
            textViewBreedName.text = domainModel?.breedName!!
            if(checkedPosition == -1){
                //  Initial
                textViewBreedName.setBackgroundColor(context.resources.getColor(R.color.textColorPrimary))
                textViewBreedName.setTextColor(context.resources.getColor(R.color.colorAccent))
            }else{
                //  Selected
                if(checkedPosition == adapterPosition){
                    textViewBreedName.setBackgroundColor(context.resources.getColor(R.color.colorPrimaryDark))
                    textViewBreedName.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                }
                //  Not selected
                else{
                    textViewBreedName.setBackgroundColor(context.resources.getColor(R.color.textColorPrimary))
                    textViewBreedName.setTextColor(context.resources.getColor(R.color.colorAccent))
                }
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            inflater.inflate(R.layout.fragment_breeds_name_recyclerview_item, parent, false)

        return BreedsNameViewHolder(itemView)
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
        (holder as BreedsNameViewHolder).bind(itemList[position])
    }

}