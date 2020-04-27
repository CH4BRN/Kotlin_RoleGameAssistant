// File SkillsAdapter.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

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
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill


/**
 *   Class "SkillsAdapter" :
 *   Adapter for skills recycler view.
 **/
class OccupationSkillsAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<OccupationSkillsAdapter.SkillsViewHolder>() {
    companion object{
        private const val TAG ="OccupationSkillsAdapter"
    }

    /** Inflater    **/
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /** Skills list **/
     var skills = emptyList<DomainOccupationSkill?>()
    set(value){
        Log.d(TAG, "skills : ${value.size}")
        field = value
    }

    /** Custom view-holder  **/
    inner class SkillsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //  Get the skill's isChecked check box.
        val isSkillCheckedItemView: CheckBox = itemView.findViewById(R.id.cb_occupationsSkillIsChecked)
        //  Get the skill's name edit text
        val skillNameItemView: TextView = itemView.findViewById(R.id.tv_occupationsSkillName)
        //  Gets the skill's description edit text
        val skillDescriptionItemView:TextView = itemView.findViewById(R.id.tv_occupationsSkillDescription)
    }

    /** ViewHolder life-cycle **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item_occupationsskill, parent, false)
        return SkillsViewHolder(itemView)
    }

    /** ViewHolder life-cycle   **/
    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {
        val current = skills[position]
        holder.isSkillCheckedItemView.isChecked = true
        holder.skillNameItemView.text = current?.skillName
        holder.skillDescriptionItemView.text = current?.skillDescription
    }

    /** Set the list containing skills to display   **/
    internal fun setSkills(skills: List<DomainOccupationSkill?>) {
        Log.d(TAG, "setSkills")
        this.skills = skills
        notifyDataSetChanged()
    }

    /** Number of item ine the list **/
    override fun getItemCount(): Int = skills.size
}