// File AbiltiesEnabledAdapter.kt
// @Author pierre.antoine - 18/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.abilities

import android.content.Context
import android.text.InputType
import android.widget.EditText

/**
 *   Class "AbiltiesEnabledAdapter" :
 *   TODO: Fill class use.
 **/
class AbilitiesDisabledAdapter(context: Context) : AbilitiesAdapter(context) {
    /** ViewHolder life-cycle   **/
    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        disabledEditText(holder.abilityRollItemView)
        disabledEditText(holder.bonusItemView)

    }

    /**
     * Diable the edition for an EditText
     */
    private fun disabledEditText(editText: EditText) {
        editText.keyListener = null
        editText.inputType = InputType.TYPE_NULL
        editText.setTextIsSelectable(false)
        editText.background = null
    }
}