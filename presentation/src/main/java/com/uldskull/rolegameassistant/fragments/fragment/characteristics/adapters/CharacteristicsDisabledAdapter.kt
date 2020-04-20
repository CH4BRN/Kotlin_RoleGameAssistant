// File AbiltiesEnabledAdapter.kt
// @Author pierre.antoine - 18/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.characteristics.adapters

import android.content.Context
import android.util.Log
import com.uldskull.rolegameassistant.fragments.fragment.EditTextUtil.Companion.editTextEnabling

/**
 *   Class "CharacteristicsDisabledAdapter" :
 *   TODO: Fill class use.
 **/
class CharacteristicsDisabledAdapter(context: Context) : CharacteristicsAdapter(context) {
    companion object {
        private const val TAG = "CharacteristicsDisabledAdapter"
    }

    /** ViewHolder life-cycle   **/
    override fun onBindViewHolder(holder: CharacteristicsViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")
        super.onBindViewHolder(holder, position)
        editTextEnabling(holder.abilityRollItemView)
        editTextEnabling(holder.bonusItemView)
    }


}