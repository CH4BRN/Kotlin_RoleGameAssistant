// File SkillsAdapter.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.occupation

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
) : RecyclerView.Adapter<OccupationSkillsAdapter.OccupationSkillsViewHolder>() {
    companion object {
        private const val TAG = "OccupationSkillsAdapter"
    }

    /** Inflater    **/
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /** Skills list **/
    var skills = emptyList<DomainOccupationSkill?>()
        set(value) {
            Log.d(TAG, "skills : ${value.size}")
            field = value
        }

    /** Custom view-holder  **/
    inner class OccupationSkillsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    /** ViewHolder life-cycle **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OccupationSkillsViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item_occupationsskill, parent, false)
        return OccupationSkillsViewHolder(itemView)
    }

    /** ViewHolder life-cycle   **/
    override fun onBindViewHolder(holder: OccupationSkillsViewHolder, position: Int) {
        val current = skills[position]

    }

    /** Set the list containing skills to display   **/
    internal fun setSkills(skills: List<DomainOccupationSkill?>?) {
        Log.d(TAG, "setSkills")
        if(skills != null){
            this.skills = skills
            notifyDataSetChanged()
        }

    }

    /** Number of item ine the list **/
    override fun getItemCount(): Int = skills.size
}