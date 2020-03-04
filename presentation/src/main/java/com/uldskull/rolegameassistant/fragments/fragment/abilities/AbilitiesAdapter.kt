// File AbilitiesAdapter.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.abilities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.models.ability_score.DomainAbilityScore

/**
 *   Class "AbilitiesAdapter" :
 *   Adapter for abilities recycler view.
 **/
class AbilitiesAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<AbilitiesAdapter.AbilitiesViewHolder>() {
    /** Inflater    **/
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /** Abilities list  **/
    private var abilities = emptyList<DomainAbilityScore>()

    /** Custom ViewHolder   **/
    inner class AbilitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //  Get the ability's name edit text.
        val abilityNameItemView: TextView = itemView.findViewById(R.id.et_abilityName)
        //  Get the roll's edit text.
        val abilityRollItemView: EditText = itemView.findViewById(R.id.et_abilityRoll)
        //  Get the bonus' edit text.
        val bonusItemView: EditText = itemView.findViewById(R.id.et_abilityBonus)
        //  Get the total's TextView.
        val totalItemView: EditText = itemView.findViewById(R.id.et_abilityTotal)
    }

    /** ViewHolder life-cycle   **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item_ability, parent, false)
        return AbilitiesViewHolder((itemView))
    }

    /** ViewHolder life-cycle   **/
    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        val current = abilities[position]
        holder.abilityNameItemView.text = current.abilityScoreAbility.toString()
        holder.abilityRollItemView.setText(current.abilityScoreRoll.toString())
        holder.bonusItemView.setText(current.abilityScoreBonus.toString())
        holder.totalItemView.setText(current.abilityScoreTotal.toString())

    }

    /** Set the list containing domainAbilities to display   **/
    internal fun setAbilities(domainAbilities: List<DomainAbilityScore>) {
        this.abilities = domainAbilities
        notifyDataSetChanged()
    }

    /** Number of items in the list **/
    override fun getItemCount(): Int = abilities.size
}