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
import com.uldskull.rolegameassistant.fragments.core.AdapterListTransmitter
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.BREED_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.viewmodels.character.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.breeds.DisplayedBreedsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "BreedsRecyclerViewFragment" :
 *   Holds a recycler view that displays breeds.
 **/
class BreedsRecyclerViewFragment :
    AdapterListTransmitter<DomainDisplayedBreed>,
    CustomRecyclerViewFragment() {


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
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /**
     * Adapter for races recycler view
     */
    private var breedsToChooseAdapter: BreedsToChooseAdapter? = null

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
    }

    /**
     * Observe breeds from repository, to load breeds to display.
     */
    private fun observeRepositoryBreeds() {
        this.displayedBreedsViewModel.observedRepositoryBreeds?.observe(
            this,
            Observer { breeds ->

                var mutableBreeds = breeds.toMutableList()

                breeds.let {

                    displayedBreedsViewModel?.observableSelectedBreeds?.observe(this, Observer {list ->
                        list.forEach {id ->
                            Log.d("DEBUG$TAG","Selected breed = $id")
                            if (id != null) {
                               var index =  mutableBreeds?.indexOfFirst { b -> b.breedId == id }
                                Log.d("DEBUG$TAG", "Index : $index")
                                if(index != null){
                                    mutableBreeds[index].breedIsChecked = true
                                }
                            }
                        }

                    })

                    Log.d("DEBUG$TAG", "Breed : ${breeds.size}")
                    if (it != null && it.isNotEmpty()) {
                        breedsToChooseAdapter = BreedsToChooseAdapter(
                            activity as Context,
                            this
                        )

                        breedsToChooseAdapter?.setItems(mutableBreeds as List<DomainDisplayedBreed>)
                        breedsRecyclerView?.adapter = breedsToChooseAdapter

                        breedsRecyclerView?.layoutManager = LinearLayoutManager(
                            activity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )

                    }
                }
            })
    }

    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {
    }

    /**
     * Set the RecyclerView's layout manager.
     */
    override fun setRecyclerViewLayoutManager() {
        Log.d("DEBUG$TAG", "setRecyclerViewLayoutManager")
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

        /**
         * Array that holds breeds.
         */
        var breedValuesArray: ArrayList<DomainDisplayedBreed> = ArrayList()

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
     * Transmit the list
     */
    override fun transmitList(domainModels: List<DomainDisplayedBreed>) {
        // displayedBreedsViewModel.observedMutableBreeds.value = domainModels
    }

}

