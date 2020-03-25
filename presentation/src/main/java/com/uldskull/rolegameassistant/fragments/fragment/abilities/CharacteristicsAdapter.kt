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
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import kotlinx.android.synthetic.main.recyclerview_item_ability.view.*


/**
 *   Class "AbilitiesAdapter" :
 *   Adapter for abilities recycler view.
 **/
open class CharacteristicsAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CharacteristicsAdapter.CharacteristicsViewHolder>() {

    private var onBind: Boolean = false
    /** Inflater    **/
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /** Abilities list  **/
    private var rollCharacteristics = emptyList<DomainCharacteristic>()

    /** Custom ViewHolder   **/
    inner class CharacteristicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //  Get the ability's name edit text.
        val characteristicNameItemView: TextView = itemView.findViewById(R.id.et_abilityName)

        //  Get the roll's edit text.
        val abilityRollItemView: EditText = itemView.findViewById(R.id.et_abilityRoll)

        //  Get the bonus' edit text.
        val bonusItemView: EditText = itemView.findViewById(R.id.et_abilityBonus)
        //  Get the total's TextView.
        val totalItemView: TextView = itemView.findViewById(R.id.tv_abilityTotal)
    }

    /** ViewHolder life-cycle   **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacteristicsViewHolder {
        var roll: Int? = 0
        var bonus: Int? = 0
        val itemView = inflater.inflate(R.layout.recyclerview_item_ability, parent, false)
        itemView.et_abilityRoll.inputType = InputType.TYPE_CLASS_NUMBER
        itemView.et_abilityRoll.addTextChangedListener {
            val stringRoll = itemView.et_abilityRoll.text.toString()
            Log.d(javaClass.simpleName, stringRoll)
            try {
                if (stringRoll.isNotBlank() && !stringRoll.isEmpty()) {
                    roll = stringRoll.toInt()
                    Log.d(javaClass.simpleName, "Roll : " + roll.toString())
                } else
                    roll = 0
                val total: Int = roll!! + bonus!!

                itemView.tv_abilityTotal.text = total.toString()

            } catch (e: Exception) {
                e.printStackTrace()
                throw  e
            }
        }
        itemView.et_abilityBonus.inputType = InputType.TYPE_CLASS_NUMBER

        itemView.et_abilityBonus.addTextChangedListener {
            val stringBonus = itemView.et_abilityBonus.text.toString()
            Log.d(javaClass.simpleName, stringBonus)
            try {
                if (stringBonus.isNotBlank() && !stringBonus.isEmpty()) {
                    bonus = stringBonus.toInt()
                    Log.d(javaClass.simpleName, "Bonus : " + bonus.toString())
                } else {
                    bonus = 0
                }
                val total: Int = roll!! + bonus!!

                itemView.tv_abilityTotal.text = total.toString()


            } catch (e: Exception) {
                e.printStackTrace()
                throw  e
            }
        }

        return CharacteristicsViewHolder((itemView))
    }


    /** ViewHolder life-cycle   **/
    override fun onBindViewHolder(holder: CharacteristicsViewHolder, position: Int) {
        onBind = true
        val current = rollCharacteristics[position]
        holder.characteristicNameItemView.text = current.characteristicName
        onBind = false


    }

    /** Set the list containing domainAbilities to display   **/
    internal fun setAbilities(domainAbilities: List<DomainCharacteristic>) {
        this.rollCharacteristics = domainAbilities
        Log.d(this.javaClass.simpleName, this.rollCharacteristics.size.toString())
        notifyDataSetChanged()
    }

    /** Number of items in the list **/
    override fun getItemCount(): Int = rollCharacteristics.size
}