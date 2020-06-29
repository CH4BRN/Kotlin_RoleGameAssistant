// File CharacterAdapter.kt
// @Author pierre.antoine - 28/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.characterSearch

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.core.adapter.CustomRecyclerViewAdapter
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.core.listeners.DeleteCharacterButtonListener
import com.uldskull.rolegameassistant.fragments.core.listeners.EditLifePointsButtonListener
import com.uldskull.rolegameassistant.fragments.core.listeners.EditSanityButtonListener
import com.uldskull.rolegameassistant.models.character.DomainCharacter

/**
 *   Class "CharacterAdapter" :
 *   Adapter that populates the character recycler view.
 **/
class CharactersAdapter internal constructor(
    val context: Context,
    var openCharacterButtonListener: CustomAdapterButtonListener<DomainCharacter>?,
    var deleteCharacterButtonListener: DeleteCharacterButtonListener,
    var editLifePointsButtonListener: EditLifePointsButtonListener,
    var editSanityPointsButtonListener: EditSanityButtonListener
) : CustomRecyclerViewAdapter<DomainCharacter>(context) {

    companion object {
        private const val TAG = "CharactersAdapter"
    }

    /**
     * View holder class
     */
    inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val characterItemLayout: ConstraintLayout =
            itemView.findViewById(R.id.character_item_linear_layout)
        private val characterNameItemView: TextView = itemView.findViewById(R.id.tv_characterName)
        private val imageButtonDeleteCharacter: ImageButton =
            itemView.findViewById(R.id.fragmentCharacterRecyclerViewItem_imageButton_deleteCharacter)
        private val textViewCharacterHealth: TextView =
            itemView.findViewById(R.id.fragmentCharacterRecyclerViewItem_textView_healthPoints)
        private val textViewHealthAttribute: TextView =
            itemView.findViewById(R.id.fragmentCharacterRecyclerViewItem_textView_healthPointAttribute)
        private val textViewCharacterSanity:TextView =
            itemView.findViewById(R.id.fragmentCharacterRecyclerViewItem_textView_sanity)
        private val textViewSanityAttribute :TextView =
            itemView.findViewById(R.id.fragmentCharacterRecyclerViewItem_textView_sanityAttribute)
        private val imageButtonSetLifePoints: ImageButton =
            itemView.findViewById(R.id.fragmentCharacterRecyclerViewItem_imageButton_setLifePoints)
        private val imageButtonSetSanity: ImageButton =
            itemView.findViewById(R.id.fragmentCharacterRecyclerViewItem_imageButton_editSanity)

        init {
            setDeleteButtonOnClickListener()

            characterItemLayout.setOnClickListener {
                rowIndex = adapterPosition
                Log.d("DEBUG", "onBindViewHolder - OnClick - ${itemList[adapterPosition]} ")
                if (openCharacterButtonListener != null) {
                    //  Send the character to the RecyclerView fragment
                    openCharacterButtonListener?.itemPressed(itemList[adapterPosition])
                }
                notifyDataSetChanged()
            }

            imageButtonSetLifePoints?.setOnClickListener {
                editLifePointsButtonListener?.editLifePoints(itemList[adapterPosition])
            }

            imageButtonSetSanity?.setOnClickListener {
                editSanityPointsButtonListener?.editSanityScore(itemList[adapterPosition])
            }
        }

        /**
         * Set delete button on click listener
         */
        private fun setDeleteButtonOnClickListener() {
            imageButtonDeleteCharacter?.setOnClickListener {
                if (adapterPosition == -1) {
                    deleteCharacterButtonListener.deleteCharacter(itemList[0])
                } else {
                    deleteCharacterButtonListener.deleteCharacter(itemList[adapterPosition])
                }
                notifyDataSetChanged()
            }
        }

        /**
         * Bind the value
         */
        fun bind(domainCharacter: DomainCharacter?) {
            if (itemList != null) {
                characterNameItemView.text = domainCharacter?.characterName
                if (domainCharacter?.characterHealthPoints == null) {
                    textViewCharacterHealth.text = 0.toString()
                } else {
                    textViewCharacterHealth.text = domainCharacter.characterHealthPoints.toString()
                }


                if(domainCharacter?.characterSanity == null){
                    textViewCharacterSanity.text = 99.toString()
                }else{
                    textViewCharacterSanity.text = domainCharacter.characterSanity.toString()
                }

                Log.d("test", "\nCurrent character : $domainCharacter")

                if (rowIndex == adapterPosition) {
                    characterItemLayout.setBackgroundColor(context.resources.getColor(R.color.colorPrimaryDark))
                    characterNameItemView.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                    textViewCharacterHealth.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                    textViewHealthAttribute.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                    textViewCharacterSanity.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                    textViewSanityAttribute.setTextColor(context.resources.getColor(R.color.textColorPrimary))
                } else {
                    characterItemLayout.setBackgroundColor(context.resources.getColor(R.color.textColorPrimary))
                    characterNameItemView.setTextColor(context.resources.getColor(R.color.colorPrimaryDark))
                    textViewCharacterHealth.setTextColor(context.resources.getColor(R.color.colorPrimaryDark))
                    textViewHealthAttribute.setTextColor(context.resources.getColor(R.color.colorPrimaryDark))
                    textViewCharacterSanity.setTextColor(context.resources.getColor(R.color.colorPrimaryDark))
                    textViewSanityAttribute.setTextColor(context.resources.getColor(R.color.colorPrimaryDark))
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
        val itemView =
            inflater.inflate(R.layout.fragment_character_recyclerview_item, parent, false)

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