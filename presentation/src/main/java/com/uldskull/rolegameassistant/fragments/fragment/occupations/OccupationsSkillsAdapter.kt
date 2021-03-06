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
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill


/**
 *   Class "JobSkillsAdapter" :
 *   TODO: Fill class use.
 **/
class OccupationsSkillsAdapter internal constructor(
    context: Context,
    private val buttonListener: AdapterButtonListener<DomainOccupationSkill>
) : RecyclerView.Adapter<OccupationsSkillsAdapter.OccupationsSkillsViewHolder>() {
    companion object {
        private const val TAG = "OccupationsSkillsAdapter"
    }

    /** Inflater  **/
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    /**  Inner class to display  **/
    inner class OccupationsSkillsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvOccupationsSkillName: TextView? = itemView.findViewById(R.id.tv_occupationsSkillName)
        var tvOccupationsSkillDescription: TextView? =
            itemView.findViewById(R.id.tv_occupationsSkillDescription)
        var cbOccupationsSkillIsChecked: CheckBox? =
            itemView.findViewById(R.id.cb_occupationsSkillIsChecked)
    }

    /**  Skills list  **/
    private var occupationSkills = emptyList<DomainOccupationSkill?>()

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
            layoutInflater.inflate(R.layout.recyclerview_item_occupationsskill, parent, false)
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
        val current = occupationSkills[position]
        holder.tvOccupationsSkillName?.text = current?.skillName
        Log.d(TAG, "Skills is checked : ${current?.skillIsChecked}")
        holder.cbOccupationsSkillIsChecked?.isChecked = current?.skillIsChecked!!
        holder.cbOccupationsSkillIsChecked?.setOnCheckedChangeListener() { _, isChecked ->
            kotlin.run {
                Log.d(TAG, "isChecked : $isChecked")
                current.skillIsChecked = isChecked
                Log.d(TAG, "${occupationSkills[position]}")
                this.buttonListener.itemPressed(current)
            }

        }
        holder.tvOccupationsSkillDescription?.text = current?.skillDescription
    }


    fun setOccupationsSkills(skills: List<DomainOccupationSkill?>?) {
        if (skills != null) {
            this.occupationSkills = skills
            notifyDataSetChanged()
        }

    }

}