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
import com.uldskull.rolegameassistant.fragments.fragment.AdapterListTransmitter
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.BREED_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.breeds.DisplayedBreedsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "BreedsRecyclerViewFragment" :
 *   Holds a recycler view that displays breeds.
 **/
class BreedsRecyclerViewFragment :
    CustomRecyclerViewFragment(),
    AdapterListTransmitter<DomainDisplayedBreed> {
    /**
     * Recycler view for races.
     */
    private var breedsRecyclerView: RecyclerView? = null

    /**
     * ViewModel for displayed breeds
     */
    private val displayedBreedsViewModel: DisplayedBreedsViewModel by sharedViewModel()

    /**
     * ViewModel for new character
     */
    private val newCharacterViewModel:NewCharacterViewModel by sharedViewModel()
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
        //  Observe repository breeds
        observeRepositoryBreeds()
        //  Observe displayed breeds
        observeMutableBreedsList()
    }

    /**
     * Observe the mutable breeds list that will be displayed.
     */
    private fun observeMutableBreedsList() {
        this.displayedBreedsViewModel.observedMutableBreeds.observe(
            this,
            Observer { domainDisplayedBreeds ->
                Log.d("DEBUG$TAG", "Checked breeds = ${domainDisplayedBreeds?.count { b -> b.breedIsChecked }}")

                var newCharacter = newCharacterViewModel?.currentCharacter

                newCharacter?.characterBreeds = domainDisplayedBreeds?.filter { breed -> breed.breedIsChecked }?.map { breed-> breed.breedId }?.toMutableList()

                newCharacterViewModel?.currentCharacter = newCharacter

                if (domainDisplayedBreeds != null) {
                    this.breedsAdapter?.setBreeds(domainDisplayedBreeds.toMutableList())
                }
            })
    }


    /**
     * Observe breeds from repository, to load breeds to display.
     */
    private fun observeRepositoryBreeds() {
        this.displayedBreedsViewModel.observedRepositoryBreeds?.observe(this, Observer { breeds ->
            kotlin.run {
                this.displayedBreedsViewModel.observedMutableBreeds.value = breeds
            }
        })
    }

    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {
        Log.d(TAG, "setRecyclerViewAdapter")
        if (activity != null) {
            breedsAdapter =
                BreedsAdapter(
                    activity as Context,
                    this
                )
            breedsRecyclerView?.adapter = breedsAdapter
        }
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
            R.layout.fragment_breeds_recyclerview, container, false
        )
        return initialRootView
    }


    companion object : CustomCompanion() {
        private const val TAG = "BreedsRecyclerViewFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): BreedsRecyclerViewFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                BreedsRecyclerViewFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, BREED_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }

    /**
     * List transmitter
     */
    override fun transmitList(domainDisplayedModels: List<DomainDisplayedBreed>?) {
        displayedBreedsViewModel.observedMutableBreeds.value = domainDisplayedModels
    }


}