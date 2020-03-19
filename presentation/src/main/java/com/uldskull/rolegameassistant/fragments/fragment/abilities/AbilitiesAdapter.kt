// File AbilitiesAdapter.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.abilities

import android.content.Context
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.models.ability_score.DomainAbilityScore
import kotlinx.android.synthetic.main.recyclerview_item_ability.view.*


/**
 *   Class "AbilitiesAdapter" :
 *   Adapter for abilities recycler view.
 **/
open class AbilitiesAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<AbilitiesAdapter.AbilitiesViewHolder>() {

    private var onBind: Boolean = false
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
        val totalItemView: TextView = itemView.findViewById(R.id.tv_abilityTotal)
    }

    /** ViewHolder life-cycle   **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        var roll: Int? = 0
        var bonus: Int? = 0
        val itemView = inflater.inflate(R.layout.recyclerview_item_ability, parent, false)
        itemView.et_abilityRoll.inputType = InputType.TYPE_CLASS_NUMBER
        itemView.et_abilityRoll.addTextChangedListener {
            var stringRoll = itemView.et_abilityRoll.text.toString()
            Log.i(javaClass.simpleName, stringRoll)
            try {
                if (stringRoll.isNotBlank() && !stringRoll.isNullOrEmpty()) {
                    roll = stringRoll.toInt()
                    Log.i(javaClass.simpleName, "Roll : " + roll.toString())
                } else
                    roll = 0
                var total: Int = roll!! + bonus!!

                itemView.tv_abilityTotal.text = total.toString()

            } catch (e: Exception) {
                e.printStackTrace()
                throw  e
            }
        }
        itemView.et_abilityBonus.inputType = InputType.TYPE_CLASS_NUMBER

        itemView.et_abilityBonus.addTextChangedListener {
            var stringBonus = itemView.et_abilityBonus.text.toString()
            Log.i(javaClass.simpleName, stringBonus)
            try {
                if (stringBonus.isNotBlank() && !stringBonus.isNullOrEmpty()) {
                    bonus = stringBonus.toInt()
                    Log.i(javaClass.simpleName, "Bonus : " + bonus.toString())
                } else {
                    bonus = 0
                }
                var total: Int = roll!! + bonus!!

                itemView.tv_abilityTotal.text = total.toString()


            } catch (e: Exception) {
                e.printStackTrace()
                throw  e
            }
        }

        return AbilitiesViewHolder((itemView))
    }


    /** ViewHolder life-cycle   **/
    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        onBind = true
        val current = abilities[position]
        holder.abilityNameItemView.text = current.abilityScoreAbility.toString()
        holder.abilityRollItemView.hint = current.abilityScoreRoll.toString()
        holder.bonusItemView.hint = current.abilityScoreBonus.toString()
        onBind = false


    }

    /** Set the list containing domainAbilities to display   **/
    internal fun setAbilities(domainAbilities: List<DomainAbilityScore>) {
        this.abilities = domainAbilities
        Log.i(this.javaClass.simpleName, this.abilities.size.toString())
        notifyDataSetChanged()
    }

    /** Number of items in the list **/
    override fun getItemCount(): Int = abilities.size
}