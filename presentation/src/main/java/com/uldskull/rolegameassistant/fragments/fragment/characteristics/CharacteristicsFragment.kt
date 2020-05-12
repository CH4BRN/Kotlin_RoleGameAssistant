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
import com.uldskull.rolegameassistant.activities.newCharacter.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.ABILITIES_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.viewmodels.BreedCharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.ProgressionBarViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "AbilitiesFragment" :
 *   Display
 **/
class CharacteristicsFragment() : CustomFragment() {
    /**
     * Characteristic's ViewModel.
     */
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()
    /**
     * Breed Characteristic's ViewModel.
     */
    private val breedCharacteristicsViewModel: BreedCharacteristicsViewModel by sharedViewModel()
    /**
     * Progression bar's ViewModel
     */
    private val progressionBarViewModel:ProgressionBarViewModel by sharedViewModel()
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
     * Called immediately after [.onCreateView]
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     * @param view The View returned by [.onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCharacteristicsRecyclerView()
    }

    private fun loadCharacteristicsRecyclerView() {
        if(activity != null){
            var transaction = childFragmentManager.beginTransaction()
            transaction.replace(
                R.id.fragmentCharacteristics_container_characteristics,
                CharacteristicsRecyclerViewFragment.newInstance(activity!!)
            ).commit()
        }

    }


    /**
     * Start characteristics observation
     */
    private fun getCharacteristicObserver(): Observer<List<DomainCharacteristic>> {
        Log.d(TAG, "startCharacteristicsObservation")
        return Observer { newCharacteristics ->
            newCharacteristics.forEach {
                if (it.characteristicName != null) {
                    Log.d(TAG, it.characteristicName!!)
                }

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
        progressionBarViewModel.progression.value = ABILITIES_FRAGMENT_POSITION
    }

    companion object : CustomCompanion() {
        private const val TAG = "CharacteristicsFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): CharacteristicsFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                CharacteristicsFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, ABILITIES_FRAGMENT_POSITION)
            fragment.arguments = args


            return fragment
        }
    }
}