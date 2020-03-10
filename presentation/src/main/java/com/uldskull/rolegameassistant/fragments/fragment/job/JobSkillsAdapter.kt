// File JobSkillsAdapter.kt
// @Author pierre.antoine - 05/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.job

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.models.skill.DomainSkill

/**
 *   Class "JobSkillsAdapter" :
 *   TODO: Fill class use.
 **/
class JobSkillsAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<JobSkillsAdapter.JobSkillsViewHolder>() {

    /** Inflater  **/
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    /**  Inner class to display  **/
    inner class JobSkillsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvJobskillname: TextView? = itemView.findViewById(R.id.tv_jobSkillName)
        var tvJobskillbase: TextView = itemView.findViewById(R.id.tv_jobSkillBase)!!
        var tvJobskillAddTens: TextView = itemView.findViewById(R.id.tv_jobSkillAddTens)!!
        var tvJobskillAddUnits: TextView = itemView.findViewById(R.id.tv_jobSkillAddUnits)!!
        var tvJobskilltotal: TextView = itemView.findViewById(R.id.tv_jobSkillTotal)!!


    }

    /**  Ideals list  **/
    private var jobSkills = emptyList<DomainSkill>()

    /**
     * Called when RecyclerView needs a new [JobSkillsViewHolder] of the given type to represent
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
    ): JobSkillsViewHolder {
        val itemView = layoutInflater.inflate(R.layout.recyclerview_item_jobskill, parent, false)
        return JobSkillsViewHolder(itemView)

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return jobSkills.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [JobSkillsViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [JobSkillsViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: JobSkillsViewHolder, position: Int) {
        val current = jobSkills[position]

        holder.tvJobskillAddTens.text = current.skillTensValue.toString()
        holder.tvJobskillAddUnits.text = current.skillUnitsValue.toString()
        holder.tvJobskillbase.text = current.skillBase.toString()
        holder.tvJobskillname?.text = current.skillName.toString()
    }


    fun setJobSkills(skills: List<DomainSkill>) {
        this.jobSkills = skills
        notifyDataSetChanged()
    }

}