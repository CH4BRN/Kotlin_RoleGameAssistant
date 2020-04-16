// File AbilitiesRecyclerViewFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.characteristics

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.adapter.ABILITIES_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.characteristics.adapters.CharacteristicsAdapter
import com.uldskull.rolegameassistant.fragments.fragment.characteristics.adapters.CharacteristicsDisabledAdapter
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import kotlinx.android.synthetic.main.fragment_recyclerview_characteristics.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "AbilitiesRecyclerViewFragment" :
 *   Manage abilities's RecyclerView fragment.
 **/
class CharacteristicsRecyclerViewFragment(activity: Activity) :
    CustomRecyclerViewFragment(activity) {

    private var editable: Boolean = false

    /** ViewModel for characteristics **/
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()

    /** Adapter for abilities recycler view **/
    private var characteristicsAdapter: CharacteristicsAdapter? = null

    /** Adapter to display disabled edit texts **/
    private var characteristicsDisabledAdapter: CharacteristicsDisabledAdapter? = null

    /** Recycler View for abilities **/
    private var characteristicsRecyclerView: RecyclerView? = null


    /** Fragment life-cycle **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return initializeView(inflater, container)
    }
    /** Initialize the view **/
    override fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_recyclerview_characteristics, container, false
        )
        return initialRootView
    }


    /** Fragment life-cycle **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        setButtonUsePoints()
        setButtonRoll()
    }

    /**
     * Set the button that launch a dice roll for each ability
     */
    private fun setButtonRoll() {
        if (btn_roll != null) {
            btn_roll.setOnClickListener {
                Log.d(TAG, "Roll")
                populateRandomRollCharacteristics()
                if (!editable) {
                    characteristicsRecyclerView?.adapter = characteristicsDisabledAdapter

                } else {
                    characteristicsRecyclerView?.adapter = characteristicsAdapter
                }
            }
        }
    }

    /**
     * Set the button that allow the user to use his points
     * manually.
     */
    private fun setButtonUsePoints() {
        if (btn_use_point != null) {
            btn_use_point.setOnClickListener {
                Log.d(TAG, "Use points")
                if (editable) {
                    characteristicsRecyclerView?.adapter = characteristicsDisabledAdapter
                    editable = false
                } else {
                    characteristicsRecyclerView?.adapter = characteristicsAdapter
                    editable = true
                }

                setRecyclerViewLayoutManager()
            }
        }
    }

    /** Initialize recycler view    **/
    override fun initializeRecyclerView() {
        characteristicsRecyclerView = activity.findViewById(R.id.recycler_view_abilities)
                as RecyclerView?
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
    }

    fun populateRandomRollCharacteristics() {
        characteristicsViewModel.populateRandomRollCharacteristics()
        characteristicsDisabledAdapter?.setCharacteristics(characteristicsViewModel.rollCharacteristics)
        characteristicsAdapter?.setCharacteristics(characteristicsViewModel.rollCharacteristics)
        characteristicsRecyclerView?.adapter = characteristicsDisabledAdapter
    }

    fun populateRollCharacteristics() {
        Log.d(TAG, "populateRollCharacteristics")
        var checkedBreedList = characteristicsViewModel.characterBreeds.filter { b ->
            b.breedChecked
        }
        Log.d(TAG, "checkedBreedList.size} ${checkedBreedList.size}")
        characteristicsViewModel.calculateBreedBonuses(checkedBreedList)
        characteristicsViewModel.updateCharacteristics()

        characteristicsDisabledAdapter?.setCharacteristics(characteristicsViewModel.rollCharacteristics)
        characteristicsAdapter?.setCharacteristics(characteristicsViewModel.rollCharacteristics)
        characteristicsRecyclerView?.adapter = characteristicsDisabledAdapter
    }

    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        setRecyclerViewDisabledAdapter()
    }

    /**
     * Set Disabled recyclerview adapter
     */
    private fun setRecyclerViewDisabledAdapter() {
        characteristicsDisabledAdapter =
            CharacteristicsDisabledAdapter(
                activity as Context
            )

        characteristicsAdapter =
            CharacteristicsAdapter(
                activity as Context
            )
        populateRollCharacteristics()
        characteristicsRecyclerView?.adapter = characteristicsDisabledAdapter
    }

    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
        characteristicsRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }


    companion object : CustomCompanion() {
        private const val TAG = "CharacteristicRecyclerViewFragment"
        @JvmStatic
        override fun newInstance(activity: Activity): CharacteristicsRecyclerViewFragment {
            val fragment = CharacteristicsRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, ABILITIES_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }


    }
}