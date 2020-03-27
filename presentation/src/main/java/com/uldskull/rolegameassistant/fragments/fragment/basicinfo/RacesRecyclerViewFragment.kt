// File RaceRecyclerViewFragment.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.basicinfo

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.adapter.RACES_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.DomainRace
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.RacesViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "RaceRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class RacesRecyclerViewFragment(activity: Activity) :
    CustomRecyclerViewFragment(activity),
    AdapterButtonListener<DomainRace> {
    /**
     * Recycler view for races.
     */
    private var raceRecyclerView: RecyclerView? = null

    /**
     * ViewModel for races
     */
    private val racesViewModel: RacesViewModel by sharedViewModel()

    /**
     * ViewModel for characters
     */
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /**
     * Adapter for races recycler view
     */
    private var racesAdapter: RacesAdapter? = null

    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        raceRecyclerView = activity.findViewById(R.id.recycler_view_races)
                as RecyclerView?
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        this.racesViewModel.result?.observe(this, Observer { races ->
            kotlin.run {
                races?.let {
                    racesAdapter?.setRaces(it)
                    Log.d("RACES IT SIZE ", it.size.toString())
                }
            }
        })

    }

    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {
        racesAdapter = RacesAdapter(activity as Context, this)
        raceRecyclerView?.adapter = racesAdapter
    }

    /**
     * Set the RecyclerView's layout manager.
     */
    override fun setRecyclerViewLayoutManager() {
        raceRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_races, container, false
        )
        return initialRootView
    }

    /**
     * Called by the adapter when a race is pressed.
     */
    override fun buttonPressed(t: DomainRace) {
        Log.d("RaceRecyclerViewFragment", "Button pressed")
        newCharacterViewModel.characterRace = t
    }

    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity): RacesRecyclerViewFragment {
            val fragment = RacesRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, RACES_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }


}