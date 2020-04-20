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
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.viewmodels.BreedCharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "AbilitiesFragment" :
 *   Display
 **/
class CharacteristicsFragment(activity: Activity) : CustomFragment(activity) {
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()
    private val breedCharacteristicsViewModel: BreedCharacteristicsViewModel by sharedViewModel()
    /**
     * Called when the view is created
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "onCreateView")
        val characteristicObserver = getCharacteristicObserver()
        breedCharacteristicsViewModel.breedCharacteristics?.observe(this, characteristicObserver)
        return initializeView(inflater, container)
    }

    /**
     * Start characteristics observation
     */
    private fun getCharacteristicObserver(): Observer<List<DomainCharacteristic>> {
        Log.d(TAG, "startCharacteristicsObservation")
        return Observer { newCharacteristics ->
            newCharacteristics.forEach {
                Log.d(TAG, it.characteristicName)
            }
        }
    }


    /**
     * Initialize the view
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_characteristics, container, false
        )
        return initialRootView
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
        NewCharacterActivity.progression.value = ABILITIES_FRAGMENT_POSITION
    }

    companion object : CustomCompanion() {
        private const val TAG = "CharacteristicsFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): CharacteristicsFragment {
            Log.d(TAG, "newInstance")
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