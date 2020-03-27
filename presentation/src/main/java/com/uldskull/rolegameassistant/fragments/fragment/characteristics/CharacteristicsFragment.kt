// File AbilitiesFragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.characteristics

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.ABILITIES_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.viewmodels.CharacteristicViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "AbilitiesFragment" :
 *   Display
 **/
class CharacteristicsFragment(activity: Activity) : CustomFragment(activity) {
    private val characteristicsViewModel: CharacteristicViewModel by sharedViewModel()
    /**
     * Called when the view is created
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val domainCharacteristics = listOf<DomainCharacteristic>(
            DomainCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.APPEARANCE.characteristicName
            ),
            DomainCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.CHARISMA.characteristicName
            ),
            DomainCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.CONSTITUTION.characteristicName
            ),
            DomainCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.DEXTERITY.characteristicName
            ),
            DomainCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.INTELLIGENCE.characteristicName
            ),
            DomainCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.POWER.characteristicName
            ),
            DomainCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.SIZE.characteristicName
            ),
            DomainCharacteristic(
                characteristicId = null,
                characteristicName = CharacteristicsName.STRENGTH.characteristicName
            )
        )

        val insertResult = characteristicsViewModel.saveAll(domainCharacteristics)
        Log.d("BasicInfoFragment result = ", insertResult?.size.toString())

        val characteristicObserver = startCharacteristicsObservation()

        characteristicsViewModel.result?.observe(this, characteristicObserver)


        return initializeView(inflater, container)
    }

    /**
     * Start characteristics observation
     */
    private fun startCharacteristicsObservation(): Observer<List<DomainCharacteristic>> {
        return Observer { newCharacteristics ->
            newCharacteristics.forEach {
                Log.d("BasicInfoFragment", it.characteristicName)
            }
        }
    }


    /**
     * Initialize the view
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_characteristics, container, false
        )
        return initialRootView
    }

    override fun onResume() {
        super.onResume()
        Log.d("AbilitiesFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = ABILITIES_FRAGMENT_POSITION
        Log.d("AbilitiesFragment_2", NewCharacterActivity.progression.value.toString())
    }

    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity): CharacteristicsFragment {
            val fragment =
                CharacteristicsFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, ABILITIES_FRAGMENT_POSITION)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_characteristics,
                CharacteristicsRecyclerViewFragment.newInstance(activity)
            )

            return fragment
        }
    }
}