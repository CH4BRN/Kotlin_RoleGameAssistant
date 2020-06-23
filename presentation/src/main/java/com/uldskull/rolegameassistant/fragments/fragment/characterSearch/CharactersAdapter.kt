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
import com.uldskull.rolegameassistant.fragments.fragment.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewAdapter
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter

/**
 *   Class "CharacterAdapter" :
 *   Adapter that populates the character recycler view.
 **/
class CharactersAdapter internal constructor(
    val context: Context,
    private val buttonListenerCustom: CustomAdapterButtonListener<DomainCharacter>
) : CustomRecyclerViewAdapter(context) {

    /**
     * Character list
     */
    private var characters: List<DomainCharacter?> = emptyList()

    /**
     * Layout inflater
     */
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /**
     * View holder class
     */
    inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterItemLayout: LinearLayout =
            itemView.findViewById(R.id.character_item_linear_layout)
        val characterNameItemView: TextView = itemView.findViewById(R.id.tv_characterName)

        /**
         * Bind the value
         */
        fun bind(domainCharacter: DomainCharacter?){
            if(characters != null){

                characterNameItemView.text = domainCharacter?.characterName
                Log.d("test", "\nCurrent character : $domainCharacter")

                characterItemLayout.setOnClickListener {
                    rowIndex = adapterPosition
                    Log.d("DEBUG", "onBindViewHolder - OnClick - ${characters!![adapterPosition]} ")
                    //  Send the character to the RecyclerView fragment
                    buttonListenerCustom.itemPressed(characters!![adapterPosition])

                    notifyDataSetChanged()

                }

                if (rowIndex == adapterPosition) {
                    characterItemLayout.setBackgroundColor(Color.parseColor("#D98B43"))
                    characterNameItemView.setTextColor(Color.parseColor("#ffffff"))
                } else {
                    characterItemLayout.setBackgroundColor(Color.parseColor("#ffffff"))
                    characterNameItemView.setTextColor(Color.parseColor("#C02942"))
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
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return if(characters == null){
            0
        } else{
            characters!!.size
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
        val charactersViewHolder = holder as CharactersViewHolder
        charactersViewHolder?.bind(characters[position])
    }


    /**
     * Set the character list content
     */
    internal fun setCharacters(domainCharacters: List<DomainCharacter?>) {
        this.characters = domainCharacters
        Log.d(this.javaClass.simpleName, "Characters size = " + this.characters?.size.toString())
        notifyDataSetChanged()
    }
}