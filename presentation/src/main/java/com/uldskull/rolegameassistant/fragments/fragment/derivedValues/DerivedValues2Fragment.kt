// File DerivedValues2Fragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.derivedValues

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.fragments.adapter.DERIVED_VALUES_2_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.EditTextUtil.Companion.editTextEnabling
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.DerivedValuesViewModel
import kotlinx.android.synthetic.main.fragment_derived_values_2.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "DerivedValues2Fragment" :
 *   TODO("COMMENT")
 **/
class DerivedValues2Fragment(activity: Activity) : CustomFragment(activity) {

    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()
    private val derivedValuesViewModel: DerivedValuesViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return initializeView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        setEnergyPoints()
        setSizePlusStrength()
        setDamageBonus()
        editTextsEnabling()
        setBtnEditClickListener()
    }

    private fun setDamageBonus() {
        Log.d(TAG, "setDamageBonus")
        if (et_damageBonus != null) {
            et_damageBonus.setText(derivedValuesViewModel.damageBonus?.name)
        }
    }

    private fun setSizePlusStrength() {
        Log.d(TAG, "setSizePlusStrength")
        if (et_sizePlusStrength != null) {
            var characteristics = characteristicsViewModel.rollCharacteristics.filter { c ->
                c.characteristicName == CharacteristicsName.STRENGTH.name || c.characteristicName == CharacteristicsName.SIZE.name
            }
            et_sizePlusStrength.setText(
                derivedValuesViewModel.calculateSizePlusStrength(
                    characteristics
                ).toString()
            )
        }
    }

    private fun setBtnEditClickListener() {
        if (btn_edit != null) {
            btn_edit?.setOnClickListener {
                editTextsEnabling()
            }
        }
    }

    private fun editTextsEnabling() {
        if (et_energyPoints != null) {
            editTextEnabling(et_energyPoints)
        }
        if (et_sizePlusStrength != null) {
            editTextEnabling(et_sizePlusStrength)
        }
        if (et_damageBonus != null) {
            editTextEnabling(et_damageBonus)
        }
    }
    private fun setEnergyPoints() {
        Log.d(TAG, "setEnergyPoints")
        if (et_energyPoints != null) {
            var power = characteristicsViewModel.rollCharacteristics.find { c ->
                c.characteristicName == CharacteristicsName.POWER.toString()
            }
            Log.d(TAG, "$power")
            et_energyPoints.setText(derivedValuesViewModel.calculateEnergyPoints(power).toString())
        }
    }

    /**
     * Called when the focus return on this view
     */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        NewCharacterActivity.progression.value = DERIVED_VALUES_2_FRAGMENT_POSITION
    }

    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_derived_values_2, container, false
        )
        return initialRootView
    }

    companion object : CustomCompanion() {
        const val TAG = "DerivedValues2Fragment"

        @JvmStatic
        override fun newInstance(activity: Activity): DerivedValues2Fragment {
            Log.d(TAG, "newInstance")
            val fragment =
                DerivedValues2Fragment(
                    activity
                )

            val args = Bundle()
            args.putInt(KEY_POSITION, DERIVED_VALUES_2_FRAGMENT_POSITION)
            fragment.arguments = args
            return fragment
        }
    }

}