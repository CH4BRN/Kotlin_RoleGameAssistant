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
import com.uldskull.rolegameassistant.fragments.fragment.*
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.BREED_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.breed.charactersBreed.DomainCharactersBreed
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.breeds.CharactersBreedsViewModel
import com.uldskull.rolegameassistant.viewmodels.breeds.DisplayedBreedsViewModel
import kotlinx.android.synthetic.main.fragment_recyclerview_breeds.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "BreedsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class BreedsRecyclerViewFragment() :
    CustomRecyclerViewFragment(),
    AdapterListTransmitter<DomainDisplayedBreed>,
    AdapterButtonListener<DomainDisplayedBreed> {
    /**
     * Recycler view for races.
     */
    private var breedsRecyclerView: RecyclerView? = null

    /**
     * ViewModel for displayed breeds
     */
    private val displayedBreedsViewModel: DisplayedBreedsViewModel by sharedViewModel()

    /**
     * ViewModel for Character's breeds.
     */
    private val characterBreedsViewModel: CharactersBreedsViewModel by sharedViewModel()

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
        //  Observe displayed breeds
        this.displayedBreedsViewModel.observedDisplayedBreeds?.observe(this, Observer { breeds ->
            kotlin.run {
                Log.d("DEBUG$TAG", "List changed.")
                breeds?.let {
                    it.forEach {
                        Log.d("DEBUG$TAG", "is breed checked ${it.breedChecked}")
                    }
                    Log.d("DEBUG$TAG", "breeds checked : ${it.filter { b -> b.breedChecked }.size}")
                    breedsAdapter?.setBreeds(it as MutableList<DomainDisplayedBreed?>)
                    Log.d(TAG, "BREEDS SIZE " + it.size.toString())
                }
            }
        })
        //  Observe character's breeds.
        this.characterBreedsViewModel.observedCharactersBreeds?.observe(this, Observer { breeds ->
            kotlin.run {
                Log.d("DEBUG$TAG", "Character's breeds changed")
                breeds?.let {
                    it.forEach {
                        Log.d("DEBUG$TAG", "breed number : ${it.charactersBreedId}")
                        var corresponding =
                            displayedBreedsViewModel?.findBreedWithId(it?.displayedBreedId)
                        Log.d("DEBUG$TAG", "Breed : ${corresponding}")
                    }
                }
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
                    this,
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
            R.layout.fragment_recyclerview_breeds, container, false
        )
        return initialRootView
    }


    /**
     * Called by the adapter when a breed is pressed.
     */
    override fun itemPressed(domainDisplayedModel: DomainDisplayedBreed?, position: Int?) {
        Log.d("DEBUG $TAG", "itemPressed")
        recyclerView_breeds?.requestFocus()

        var characterBreed = DomainCharactersBreed(
            displayedBreedId = domainDisplayedModel?.breedId
        )

        Log.d("DEBUG$TAG", "$domainDisplayedModel")

        if (characterBreedsViewModel?.selectedCharactersBreed.any {
                it.displayedBreedId == characterBreed?.displayedBreedId
            }) {
            characterBreedsViewModel?.selectedCharactersBreed?.remove(characterBreed)
        } else {
            characterBreedsViewModel?.selectedCharactersBreed?.add(characterBreed)
        }
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

    override fun transmitList(domainDisplayedModels: List<DomainDisplayedBreed>?) {
        Log.d("DEBUG$TAG", "The list is : ${domainDisplayedModels?.size} long")
        Log.d(
            "DEBUG$TAG",
            "There are ${domainDisplayedModels?.count { b -> b.breedChecked }} checked breeds."
        )

    }


}