// File HobbySkillAdapter.kt
// @Author pierre.antoine - 11/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.hobby

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill

/**
 *   Class "HobbySkillAdapter" :
 *   TODO: Fill class use.
 **/
class HobbySkillAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<HobbySkillAdapter.HobbySkillsViewHolder>() {


    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    /**  Skills list  **/
    private var hobbySkills = emptyList<DomainOccupationSkill?>()

    inner class HobbySkillsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvHobbySkillName: TextView? = itemView.findViewById(R.id.tv_hobbySkillName)
        var tvHobbySkillBase: TextView? = itemView.findViewById(R.id.tv_hobbySkillBase)
        var tvHobbySkillAddTens: TextView? = itemView.findViewById(R.id.tv_hobbySkillAddTens)
        var tvHobbySkillAddUnits: TextView? = itemView.findViewById(R.id.tv_hobbySkillAddUnits)
        var tvHobbySkillTotal: TextView = itemView.findViewById(R.id.tv_hobbySkillTotal)

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbySkillsViewHolder {
        val itemView = layoutInflater.inflate(R.layout.recyclerview_item_hobbyskill, parent, false)
        return HobbySkillsViewHolder((itemView))
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return hobbySkills.size
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
    override fun onBindViewHolder(holder: HobbySkillsViewHolder, position: Int) {
        val current = hobbySkills[position]
        //   holder.tvHobbySkillAddTens?.text = current.filledSkillTensValue.toString()
        // holder.tvHobbySkillAddUnits?.text = current.filledSkillUnitsValue.toString()
        //holder.tvHobbySkillBase?.text = current.filledSkillBase.toString()
        //holder.tvHobbySkillName?.text = current.skillName
    }

    fun setHobbySkills(skills: List<DomainOccupationSkill?>) {
        this.hobbySkills = skills
        notifyDataSetChanged()
    }
}