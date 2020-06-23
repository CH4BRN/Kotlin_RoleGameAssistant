// File AbilitiesFragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.characteristics

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.CHARACTERISTICS_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.viewmodels.breeds.BreedCharacteristicsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 *   Class "AbilitiesFragment" :
 *   Display
 **/
class CharacteristicsFragment : CustomFragment() {
    /**
     * Breed Characteristic's ViewModel.
     */
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
        characteristicsAlert()
    }

    /**
     * Displays alert dialog for characteristics
     */
    private fun characteristicsAlert() {
        val builder = AlertDialog.Builder(activity as Context)
        val title = getString(R.string.characteristics_trick_title)
        builder.setTitle(title)
        val content = getString(R.string.characteristics_trick_content)
        builder.setMessage(content)
        builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = okButtonClick))
        builder.show()
    }

    /**
     * Called when the user clicks on the ok button into the characteristics alert dialog
     */
    private val okButtonClick = { _: DialogInterface, _: Int -> }

    /**
     * Load characteristics recycler view.
     */
    private fun loadCharacteristicsRecyclerView() {
        if (activity != null) {
            val transaction = childFragmentManager.beginTransaction()
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

    companion object : CustomCompanion() {
        private const val TAG = "CharacteristicsFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): CharacteristicsFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                CharacteristicsFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, CHARACTERISTICS_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }
}