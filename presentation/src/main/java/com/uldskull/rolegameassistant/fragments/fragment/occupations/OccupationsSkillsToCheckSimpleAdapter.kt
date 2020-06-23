// OccupationsSkillsToCheckAdapter.kt created by UldSkull - 19/06/2020

package com.uldskull.rolegameassistant.fragments.fragment.occupations

import android.content.Context
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
Class "OccupationsSkillsToCheckAdapter"

Adapter for skills "to check" recycler view
 */
class OccupationsSkillsToCheckSimpleAdapter internal constructor(
    context: Context,
    private val buttonListenerCustom: CustomAdapterButtonListener<DomainSkillToCheck>
) : RecyclerView.Adapter<OccupationsSkillsToCheckSimpleAdapter.OccupationsSkillsToCheckViewHolder>() {
    companion object {
        private const val TAG = "OccupationsSkillsToCheckAdapter"
    }



    /** Inflater  **/
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    /**  Skills list  **/
    private var occupationSkills = emptyList<DomainSkillToCheck?>()

    /**
     * View holder
     */
    inner class OccupationsSkillsToCheckViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var tvSkillName: TextView? =
            itemView.findViewById(R.id.fragmentSkillToCheck_textView_skillName)
        private var cbSkillChecked: CheckBox? =
            itemView.findViewById(R.id.fragmentSkillToCheck_checkBox_skillChecked)
        private var viOverlay: View? = itemView.findViewById(R.id.fragmentSkillToCheck_view_overlay)

        init {
            viOverlay?.setOnClickListener {
               if(occupationSkills[adapterPosition]?.skillIsChecked!!){
                   cbSkillChecked?.isChecked = false
                   occupationSkills[adapterPosition]?.skillIsChecked = false
               }else{
                   cbSkillChecked?.isChecked = true
                   occupationSkills[adapterPosition]?.skillIsChecked = true
               }

            }
        }

        /**
         * Bind the view holder
         */
        fun bind(domainSkillToCheck: DomainSkillToCheck?, position: Int) {
            if (domainSkillToCheck != null) {
                tvSkillName?.text = domainSkillToCheck.skillName
            }
            cbSkillChecked?.isChecked = occupationSkills[position]?.skillIsChecked!!
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
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OccupationsSkillsToCheckViewHolder {
        val itemView = layoutInflater.inflate(
            R.layout.fragment_skilltochecksimple_recyclerview_item,
            parent,
            false
        )
        return OccupationsSkillsToCheckViewHolder(itemView)
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
     * set the skills list
     */
    fun setSkills(skillsToCheck: List<DomainSkillToCheck>) {
        if (skillsToCheck != null) {
            this.occupationSkills = skillsToCheck
        }
        notifyDataSetChanged()
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
    override fun onBindViewHolder(holder: OccupationsSkillsToCheckViewHolder, position: Int) {
        holder.bind(occupationSkills[position], position)
    }

}