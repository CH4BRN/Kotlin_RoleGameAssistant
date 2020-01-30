// File AbilitiesAdapter.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.presentation.fragments.fragment.abilities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.domain.model.abilities.Ability

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
    private var abilities = emptyList<Ability>()

    /** Custom ViewHolder   **/
    inner class AbilitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //  Get the ability's name edit text.
        val abilityNameItemView: EditText = itemView.findViewById(R.id.et_ability)
        //  Get the roll's edit text.
        val abilityRollItemView: EditText = itemView.findViewById(R.id.et_roll)
        //  Get the bonus' edit text.
        val bonusItemView: EditText = itemView.findViewById(R.id.et_bonus)
        //  Get the total's TextView.
        val totalItemView: EditText = itemView.findViewById(R.id.et_total)
    }

    /** ViewHolder life-cycle   **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item_ability, parent, false)
        return AbilitiesViewHolder((itemView))
    }

    /** ViewHolder life-cycle   **/
    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        val current = abilities[position]
        holder.abilityNameItemView.setText(current.name)
        holder.abilityRollItemView.setText(current.roll.toString())
        holder.bonusItemView.setText(current.bonus.toString())
        holder.totalItemView.setText(current.total.toString())

    }

    /** Set the list containing abilities to display   **/
    internal fun setAbilities(abilities: List<Ability>) {
        this.abilities = abilities
        notifyDataSetChanged()
    }

    /** Number of items in the list **/
    override fun getItemCount(): Int = abilities.size
}