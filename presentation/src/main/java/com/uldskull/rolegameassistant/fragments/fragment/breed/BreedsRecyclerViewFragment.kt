// File BreedsRecyclerViewFragment.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.breed

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
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.BREED_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.viewmodels.BreedsViewModel
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import kotlinx.android.synthetic.main.fragment_recyclerview_breeds.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "BreedsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class BreedsRecyclerViewFragment() :
    CustomRecyclerViewFragment(),
    AdapterButtonListener<DomainBreed> {
    /**
     * Recycler view for races.
     */
    private var breedsRecyclerView: RecyclerView? = null

    /**
     * ViewModel for races
     */
    private val breedsViewModel: BreedsViewModel by sharedViewModel()

    /**
     * ViewModel for characters
     */
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()

    /**
     * Adapter for races recycler view
     */
    private var breedsAdapter: BreedsAdapter? = null

    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        Log.d(TAG, "initializeRecyclerView")
        breedsRecyclerView = activity?.findViewById(R.id.recyclerView_breeds)
                as RecyclerView?
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        Log.d(TAG, "startObservation")
        this.breedsViewModel.observedBreeds?.observe(this, Observer { breeds ->
            kotlin.run {
                breeds?.let {
                    breedsViewModel.displayedBreeds = it as MutableList<DomainBreed>
                    breedsAdapter?.setBreeds(it)
                    Log.d(TAG, "BREEDS SIZE " + it.size.toString())
                }
            }
        })
    }

    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {
        Log.d(TAG, "setRecyclerViewAdapter")
        breedsAdapter =
            BreedsAdapter(
                activity as Context,
                this
            )
        breedsRecyclerView?.adapter = breedsAdapter
    }

    /**
     * Set the RecyclerView's layout manager.
     */
    override fun setRecyclerViewLayoutManager() {
        Log.d(TAG, "setRecyclerViewLayoutManager")
        breedsRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_breeds, container, false
        )
        return initialRootView
    }

    /**
     * Called by the adapter when a breed is pressed.
     */
    override fun itemPressed(domainModel: DomainBreed?) {
        Log.d(TAG, "itemPressed")
        recyclerView_breeds?.requestFocus()
        Log.d(
            TAG,
            "checked breed = " + breedsViewModel.displayedBreeds.filter { b -> b.breedChecked }.size
        )
        if (domainModel != null) {
            Log.d(
                TAG,
                "${domainModel.breedName} is not null  and is ${when (domainModel.breedChecked) {
                    true -> "checked"
                    else -> "not checked"
                }
                }"
            )
        }
        Log.d(TAG, "Filter")
        characteristicsViewModel.characterBreeds = breedsViewModel.displayedBreeds
        Log.d(
            TAG,
            "checked breed = " + breedsViewModel.displayedBreeds.filter { b -> b.breedChecked }.size
        )
    }

    companion object : CustomCompanion() {
        private const val TAG = "BreedsRecyclerViewFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): BreedsRecyclerViewFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                BreedsRecyclerViewFragment(

                )
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, BREED_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }


}