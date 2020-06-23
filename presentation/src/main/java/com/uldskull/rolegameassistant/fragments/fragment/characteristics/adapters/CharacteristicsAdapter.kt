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
import com.uldskull.rolegameassistant.models.characteristic.DomainRollsCharacteristic
import kotlinx.android.synthetic.main.fragment_characteristics_recyclerview_item.view.*


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

    /**
     * Is the adapter on bind ?
     */
    private var onBind: Boolean = false

    /** Inflater    **/
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /** Abilities list  **/
    private var rollCharacteristics = mutableListOf<DomainRollsCharacteristic?>()

    /** ViewHolder life-cycle   **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacteristicsViewHolder {
        Log.d(TAG, "onCreateViewHolder")

        val itemView =
            inflater.inflate(R.layout.fragment_characteristics_recyclerview_item, parent, false)

        itemView.et_characteristicRoll.inputType = InputType.TYPE_CLASS_NUMBER
        itemView.et_characteristicBonus.inputType = InputType.TYPE_CLASS_NUMBER

        return CharacteristicsViewHolder((itemView))
    }

    /** Custom ViewHolder   **/
    inner class CharacteristicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rollRuleItemView: TextView =
            itemView.findViewById(R.id.tv_characteristicRollRule)

        //  Get the ability's name edit text.
        private val characteristicNameItemView: TextView =
            itemView.findViewById(R.id.et_characteristicName)

        //  Get the roll's edit text.
        val abilityRollItemView: EditText = itemView.findViewById(R.id.et_characteristicRoll)

        //  Get the bonus' edit text.
        val bonusItemView: EditText = itemView.findViewById(R.id.et_characteristicBonus)

        //  Get the total's TextView.
        private val totalItemView: TextView = itemView.findViewById(R.id.tv_characteristicTotal)

        init {
            addBonusTextChangedListener()
        }

        private fun addBonusTextChangedListener() {
            bonusItemView.addTextChangedListener {
                val stringBonus = bonusItemView.text.toString()
                // Log.d(TAG, "String bonus $stringBonus")
                try {
                    if (stringBonus.isNotBlank() && stringBonus.isNotEmpty()) {
                        rollCharacteristics[adapterPosition]?.characteristicBonus =
                            stringBonus.toInt()
                        // Log.d(TAG, "Bonus : " + bonus.toString())
                    } else {
                        rollCharacteristics[adapterPosition]?.characteristicBonus = 0
                    }
                    rollCharacteristics[adapterPosition]?.characteristicTotal =
                        rollCharacteristics[adapterPosition]?.characteristicRoll!! + rollCharacteristics[adapterPosition]?.characteristicBonus!!
                    totalItemView.text =
                        rollCharacteristics[adapterPosition]?.characteristicTotal.toString()


                } catch (e: Exception) {
                    e.printStackTrace()
                    throw  e
                }
            }
        }

        /**
         * Bind the value
         */
        fun bind(domainRollsCharacteristic: DomainRollsCharacteristic?) {
            characteristicNameItemView.text = domainRollsCharacteristic?.characteristicName
            bonusItemView.setText(domainRollsCharacteristic?.characteristicBonus.toString())
            abilityRollItemView.setText(domainRollsCharacteristic?.characteristicRoll.toString())
            totalItemView.text = domainRollsCharacteristic?.characteristicTotal.toString()
            rollRuleItemView.text = domainRollsCharacteristic?.characteristicRollRule
        }
    }

    /** ViewHolder life-cycle   **/
    override fun onBindViewHolder(holder: CharacteristicsViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")
        onBind = true
        holder.bind(rollCharacteristics[position])
        onBind = false
    }

    /** Set the list containing domainAbilities to display   **/
    internal fun setCharacteristics(domainCharacteristics: List<DomainRollsCharacteristic?>?) {
        Log.d(TAG, "setCharacteristics")
        if (domainCharacteristics != null) {
            this.rollCharacteristics =
                domainCharacteristics.sortedBy { c -> c?.characteristicName }.toMutableList()
        }
        notifyDataSetChanged()
    }

    /** Number of items in the list **/
    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount")
        return rollCharacteristics.size
    }

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "CharacteristicsAdapter(onBind=$onBind,\n" +
                " inflater=$inflater,\n" +
                " rollCharacteristics=$rollCharacteristics)"
    }
}