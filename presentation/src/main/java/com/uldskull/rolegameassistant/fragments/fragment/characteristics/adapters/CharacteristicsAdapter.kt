// File AbilitiesAdapter.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.characteristics.adapters

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
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic
import kotlinx.android.synthetic.main.recyclerview_item_characteristic.view.*


/**
 *   Class "AbilitiesAdapter" :
 *   Adapter for abilities recycler view.
 **/
open class CharacteristicsAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CharacteristicsAdapter.CharacteristicsViewHolder>() {
    companion object {
        private const val TAG = "CharacteristicsAdapter"
    }

    private var onBind: Boolean = false

    /** Inflater    **/
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /** Abilities list  **/
    private var rollCharacteristics = mutableListOf<DomainRollsCharacteristic?>()

    fun getCharacteristics(): List<DomainRollsCharacteristic?> {
        return rollCharacteristics.toList()
    }

    /** Custom ViewHolder   **/
    inner class CharacteristicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rollRuleItemView: TextView =
            itemView.findViewById<TextView>(R.id.tv_characteristicRollRule)

        //  Get the ability's name edit text.
        val characteristicNameItemView: TextView =
            itemView.findViewById<TextView>(R.id.et_characteristicName)

        //  Get the roll's edit text.
        val abilityRollItemView: EditText = itemView.findViewById(R.id.et_characteristicRoll)

        //  Get the bonus' edit text.
        val bonusItemView: EditText = itemView.findViewById(R.id.et_characteristicBonus)

        //  Get the total's TextView.
        val totalItemView: TextView = itemView.findViewById(R.id.tv_characteristicTotal)
    }

    /** ViewHolder life-cycle   **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacteristicsViewHolder {
        Log.d(TAG, "onCreateViewHolder")

        val itemView = inflater.inflate(R.layout.recyclerview_item_characteristic, parent, false)
        itemView.et_characteristicRoll.inputType = InputType.TYPE_CLASS_NUMBER

        itemView.et_characteristicBonus.inputType = InputType.TYPE_CLASS_NUMBER


        return CharacteristicsViewHolder((itemView))
    }


    /** ViewHolder life-cycle   **/
    override fun onBindViewHolder(holder: CharacteristicsViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")
        onBind = true
        val current = rollCharacteristics[position]
        holder.characteristicNameItemView.text = current?.characteristicName
        holder.bonusItemView.setText(current?.characteristicBonus.toString())
        Log.d("DEBUG$TAG", "current?.characteristicRoll : ${current?.characteristicRoll} ")
        holder.abilityRollItemView.setText(current?.characteristicRoll.toString())
        holder.totalItemView.text = current?.characteristicTotal.toString()
        holder.rollRuleItemView.text = current?.characteristicRollRule

        holder.abilityRollItemView.addTextChangedListener {
            val stringRoll = holder.abilityRollItemView.text.toString()
            Log.d(TAG, "StringRoll = $stringRoll")
            try {
                if (stringRoll.isNotBlank() && stringRoll.isNotEmpty()) {
                    current?.characteristicRoll = stringRoll.toInt()
                    Log.d(TAG, "Roll : " + current?.characteristicRoll.toString())
                } else
                    current?.characteristicRoll = 0
                current?.characteristicTotal =
                    current?.characteristicRoll!! + current?.characteristicBonus!!

                holder.totalItemView.text = current.characteristicTotal.toString()
                rollCharacteristics[position] = current

            } catch (e: Exception) {
                e.printStackTrace()
                throw  e
            }
        }

        holder.bonusItemView.addTextChangedListener {
            val stringBonus = holder.bonusItemView.text.toString()
            // Log.d(TAG, "String bonus $stringBonus")
            try {
                if (stringBonus.isNotBlank() && stringBonus.isNotEmpty()) {
                    current?.characteristicBonus = stringBonus.toInt()
                    // Log.d(TAG, "Bonus : " + bonus.toString())
                } else {
                    current?.characteristicBonus = 0
                }
                current?.characteristicTotal =
                    current?.characteristicRoll!! + current.characteristicBonus!!
                holder.totalItemView.text = current.characteristicTotal.toString()


            } catch (e: Exception) {
                e.printStackTrace()
                throw  e
            }
        }

        onBind = false
    }

    /** Set the list containing domainAbilities to display   **/
    internal fun setCharacteristics(domainCharacteristics: List<DomainRollsCharacteristic?>?) {
        Log.d(TAG, "setCharacteristics")
        if (domainCharacteristics != null) {
            this.rollCharacteristics =
                domainCharacteristics.sortedBy { c -> c?.characteristicName }.toMutableList()
        }

        Log.d(TAG, "rollCharacteristics size = " + this.rollCharacteristics.size.toString())
        notifyDataSetChanged()
    }

    /** Number of items in the list **/
    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount")
        return rollCharacteristics.size
    }

    override fun toString(): String {
        return "CharacteristicsAdapter(onBind=$onBind,\n" +
                " inflater=$inflater,\n" +
                " rollCharacteristics=$rollCharacteristics)"
    }
}