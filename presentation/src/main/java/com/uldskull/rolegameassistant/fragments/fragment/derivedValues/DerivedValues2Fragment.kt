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
        return initializeView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEnergyPoints()
        if (et_sizePlusStrength != null) {
            var couple = characteristicsViewModel.rollCharacteristics.filter { c ->
                c.characteristicName == CharacteristicsName.STRENGTH.characteristicName || c.characteristicName == CharacteristicsName.SIZE.characteristicName
            }
            var score = 0
            couple.forEach {
                if (it.characteristicTotal != null) {
                    score += it.characteristicTotal!!
                }
            }
            et_sizePlusStrength.setText(score.toString())

        }
    }

    private fun setEnergyPoints() {
        if (et_energyPoints != null) {
            var power = characteristicsViewModel.rollCharacteristics.find { c ->
                c.characteristicName == CharacteristicsName.POWER.characteristicName
            }

            var energyPoints = derivedValuesViewModel.calculateEnergyPoints(power)
            et_energyPoints.setText(energyPoints.toString())
        }
    }

    /**
     * Called when the focus return on this view
     */
    override fun onResume() {
        super.onResume()
        Log.i("DerivedValues2Fragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = DERIVED_VALUES_2_FRAGMENT_POSITION
        Log.i("DerivedValues2Fragment_2", NewCharacterActivity.progression.value.toString())
    }

    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_derived_values_2, container, false
        )
        return initialRootView
    }

    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity): DerivedValues2Fragment {

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