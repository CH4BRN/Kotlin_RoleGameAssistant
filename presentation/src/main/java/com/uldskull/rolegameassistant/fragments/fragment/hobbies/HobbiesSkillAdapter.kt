// HobbiesSkillAdapter.kt created by UldSkull - 15/06/2020

package com.uldskull.rolegameassistant.fragments.fragment.hobbies

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.models.skill.DomainSkillToCheck

/**
Class "HobbiesSkillAdapter"

Adapter for hobbies skills
 */
class HobbiesSkillAdapter internal constructor(
    val context: Context,
    private val buttonListenerCustom: CustomAdapterButtonListener<DomainSkillToCheck>
) : RecyclerView.Adapter<HobbiesSkillAdapter.HobbiesSkillsViewHolder>() {
    companion object {
        private const val TAG = "HobbiesSkillAdapter"
    }


    /**Skills list**/
    private var hobbiesSkills = mutableListOf<DomainSkillToCheck?>()

    /**
     * Set the hobbies skills list.
     */
    internal fun setHobbiesSkills(skills: List<DomainSkillToCheck?>?) {
        Log.d(TAG, "setHobbiesSkills")
        if (this.hobbiesSkills == null) {
            this.hobbiesSkills = mutableListOf()
        }
        if (skills != null) {
            this.hobbiesSkills = skills.toMutableList()
            notifyDataSetChanged()
        }
    }

    /**
     * Inner class to display
     */
    inner class HobbiesSkillsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvHobbiesSkillName: TextView? = itemView.findViewById(R.id.tv_skillToCheck_name)
        private var cbHobbiesSkillIsChecked: CheckBox? =
            itemView.findViewById(R.id.cb_skillIToCheck_IsChecked)
        private var tvHobbiesSkillDescription: TextView? =
            itemView.findViewById(R.id.tv_skillToCheck_Description)
        private var vHobbiesSkillOverlay: View? = itemView.findViewById(R.id.skillToCheck_overlay)

        /**
         * Bind the view holder.
         */
        fun bind(skill: DomainSkillToCheck?) {
            Log.d("DEBUG$TAG", "Skill : $skill")
            tvHobbiesSkillName?.text = skill?.skillName
            tvHobbiesSkillDescription?.text = skill?.skillDescription

            vHobbiesSkillOverlay?.setOnClickListener {
                Log.d("DEBUG$TAG", "Overlay")
                val isChecked = skill?.skillIsChecked
                Log.d("DEBUG$TAG", "Overlay : isChecked : $isChecked")
                if (isChecked != null) {
                    skill.skillIsChecked = !isChecked
                    Log.d("DEBUG$TAG", "Overlay : skill?.skillIsChecked : ${skill.skillIsChecked}")
                }
                cbHobbiesSkillIsChecked?.isChecked = skill?.skillIsChecked!!
                buttonListenerCustom.itemPressed(skill)

            }
            cbHobbiesSkillIsChecked?.isChecked = skill?.skillIsChecked!!
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbiesSkillsViewHolder {
        val view: View = LayoutInflater.from(context).inflate(
            R.layout.fragment_skilltocheckdescription_recyclerview_item,
            parent,
            false
        )
        return HobbiesSkillsViewHolder(view)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return hobbiesSkills.size
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
    override fun onBindViewHolder(holder: HobbiesSkillsViewHolder, position: Int) {
        Log.d("DEBUG$TAG", "onBindViewHolder \n position : $position")
        holder.bind(hobbiesSkills[position])
    }
}