// File CharacterAdapter.kt
// @Author pierre.antoine - 28/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.characterSearch

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
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.core.adapter.CustomRecyclerViewAdapter
import com.uldskull.rolegameassistant.models.character.DomainCharacter

/**
 *   Class "CharacterAdapter" :
 *   Adapter that populates the character recycler view.
 **/
class CharactersAdapter internal constructor(
    val context: Context,
    var buttonListenerCustom: CustomAdapterButtonListener<DomainCharacter>?
) : CustomRecyclerViewAdapter<DomainCharacter>(context) {

   fun setButtonListener(buttonListenerCustom: CustomAdapterButtonListener<DomainCharacter>?){
       this.buttonListenerCustom = buttonListenerCustom
   }




    /**
     * View holder class
     */
    inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val characterItemLayout: LinearLayout =
            itemView.findViewById(R.id.character_item_linear_layout)
        private val characterNameItemView: TextView = itemView.findViewById(R.id.tv_characterName)

        /**
         * Bind the value
         */
        fun bind(domainCharacter: DomainCharacter?){
            if(itemList != null){

                characterNameItemView.text = domainCharacter?.characterName
                Log.d("test", "\nCurrent character : $domainCharacter")

                characterItemLayout.setOnClickListener {
                    rowIndex = adapterPosition
                    Log.d("DEBUG", "onBindViewHolder - OnClick - ${itemList[adapterPosition]} ")
                    if(buttonListenerCustom != null){
                        //  Send the character to the RecyclerView fragment
                        buttonListenerCustom?.itemPressed(itemList[adapterPosition])
                    }


                    notifyDataSetChanged()

                }

                if (rowIndex == adapterPosition) {
                    characterItemLayout.setBackgroundColor(context.resources.getColor(R.color.colorPrimaryDark))
                    characterNameItemView.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                } else {
                    characterItemLayout.setBackgroundColor(context.resources.getColor(R.color.textColorPrimary))
                    characterNameItemView.setTextColor(context.resources.getColor(R.color.colorPrimaryDark))
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val itemView = inflater.inflate(R.layout.fragment_character_recyclerview_item, parent, false)
        return CharactersViewHolder(itemView)
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
        val charactersViewHolder = holder as CharactersViewHolder
        charactersViewHolder.bind(itemList[position])
    }
}