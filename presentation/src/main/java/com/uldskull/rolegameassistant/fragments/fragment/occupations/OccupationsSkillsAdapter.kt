// File JobSkillsAdapter.kt
// @Author pierre.antoine - 05/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.occupations

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToCheck


/**
 *   Class "JobSkillsAdapter" :
 *   TODO: Fill class use.
 **/
class OccupationsSkillsAdapter internal constructor(
    context: Context,
    private val buttonListener: AdapterButtonListener<DomainSkillToCheck>
) : RecyclerView.Adapter<OccupationsSkillsAdapter.OccupationsSkillsViewHolder>() {
    companion object {
        private const val TAG = "OccupationsSkillsAdapter"
    }

    /** Inflater  **/
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    /**  Inner class to display  **/
    inner class OccupationsSkillsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvOccupationsSkillName: TextView? = itemView.findViewById(R.id.tv_skillToCheck_name)
        var tvOccupationsSkillDescription: TextView? =
            itemView.findViewById(R.id.tv_skillToCheck_Description)
        var cbOccupationsSkillIsChecked: CheckBox? =
            itemView.findViewById(R.id.cb_skillIToCheck_IsChecked)
        var vOccupationsSkillOverlay: View? = itemView.findViewById(R.id.skillToCheck_overlay)

        fun bind(skill: DomainSkillToCheck?) {
            Log.d("DEBUG$TAG", "Skill : $skill")
            tvOccupationsSkillName?.text = skill?.skillName
            tvOccupationsSkillDescription?.text = skill?.skillDescription
            cbOccupationsSkillIsChecked?.isChecked = skill?.skillIsChecked!!
            vOccupationsSkillOverlay?.setOnClickListener {
                Log.d("DEBUG$TAG", "Overlay")
                buttonListener.itemPressed(skill)
            }

        }
    }

    /**  Skills list  **/
    private var occupationSkills = emptyList<DomainSkillToCheck?>()

    /**
     * Called when RecyclerView needs a new [OccupationsSkillsViewHolder] of the given type to represent
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
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OccupationsSkillsViewHolder {
        val itemView =
            layoutInflater.inflate(R.layout.recyclerview_item_skill_to_check, parent, false)
        return OccupationsSkillsViewHolder(itemView)

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return occupationSkills.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [OccupationsSkillsViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [OccupationsSkillsViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: OccupationsSkillsViewHolder, position: Int) {
        holder.bind(occupationSkills[position])
    }


    fun setOccupationsSkills(skills: List<DomainSkillToCheck?>?) {
        if (skills != null) {
            this.occupationSkills = skills
            notifyDataSetChanged()
        }

    }

}