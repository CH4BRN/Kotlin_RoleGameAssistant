// File SkillsAdapter.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.skills

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.models.character.DomainFilledSkill


/**
 *   Class "SkillsAdapter" :
 *   Adapter for skills recycler view.
 **/
class JobsSkillsAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<JobsSkillsAdapter.SkillsViewHolder>() {

    /** Inflater    **/
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /** Skills list **/
    private var skills = emptyList<DomainFilledSkill>()

    /** Custom view-holder  **/
    inner class SkillsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //  Get the skill's isChecked check box.
        val isSkillCheckedItemView: CheckBox = itemView.findViewById(R.id.chk_isSkillChecked)
        //  Get the skill's name edit text
        val skillNameItemView: TextView = itemView.findViewById(R.id.tv_skillName)
    }

    /** ViewHolder life-cycle **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item_jobsskill, parent, false)
        return SkillsViewHolder(itemView)
    }

    /** ViewHolder life-cycle   **/
    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {
        val current = skills[position]
        holder.isSkillCheckedItemView.isChecked = true
        holder.skillNameItemView.text = current.skillName
    }

    /** Set the list containing skills to display   **/
    internal fun setSkills(skills: List<DomainFilledSkill>) {
        this.skills = skills
        notifyDataSetChanged()
    }

    /** Number of item ine the list **/
    override fun getItemCount(): Int = skills.size
}