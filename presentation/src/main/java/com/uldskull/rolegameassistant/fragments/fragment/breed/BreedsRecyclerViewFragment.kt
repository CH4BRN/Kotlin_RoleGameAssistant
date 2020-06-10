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
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
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
     * ViewModel for character
     */
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

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
        //  Observe character's breeds.
        observeSelectedCharacter()
        //  Observe displayed breeds
        observeMutableBreedsList()
    }

    /**
     * Observe the mutable breeds list that will be displayed.
     */
    private fun observeMutableBreedsList() {
        this.displayedBreedsViewModel?.observedMutableBreeds.observe(
            this,
            Observer { domainDisplayedBreeds ->
                var breedCount = domainDisplayedBreeds?.count()
                var checkedBreedsCount = domainDisplayedBreeds?.count { b -> b.breedChecked }
                Log.d("DEBUG$TAG", "checked : $checkedBreedsCount on total : $breedCount")

                if (domainDisplayedBreeds != null) {
                    this.breedsAdapter?.setBreeds(domainDisplayedBreeds.toMutableList())
                }
            })
    }

    /**
     * Observe the selected character.
     */
    private fun observeSelectedCharacter() {
        this.newCharacterViewModel?.selectedCharacter?.observe(this, Observer { domainCharacter ->
            if (domainCharacter != null) {
                Log.d("DEBUG$TAG", "\nSelected character is : ${domainCharacter.characterName}")
                observeCharactersSelectedBreeds(domainCharacter)
            }
        })
    }

    /**
     * Observe Character's selected breeds, to display previously selected breeds.
     */
    private fun observeCharactersSelectedBreeds(domainCharacter: DomainCharacter?) {
        characterBreedsViewModel?.observedCharactersBreeds?.observe(
            this,
            Observer { domainCharacterBreeds ->
                domainCharacterBreeds?.forEach { domainCharacterBreed ->
                    if (domainCharacterBreed.characterId!! == domainCharacter?.characterId) {
                        var corresponding =
                            displayedBreedsViewModel?.findBreedWithId(domainCharacterBreed?.displayedBreedId)
                        Log.d(
                            "DEBUG$TAG", "found breed : \n" +
                                    "$corresponding"
                        )
                        var displayedBreeds =
                            displayedBreedsViewModel?.observedMutableBreeds?.value?.toMutableList()
                        if (displayedBreeds != null) {
                            Log.d("DEBUG$TAG", "breeds size = ${displayedBreeds.size}")

                            var found =
                                displayedBreeds?.find { b -> b.breedId == corresponding?.breedId }
                            if (found != null) {
                                Log.d("DEBUG$TAG", "found : $found")
                                found?.breedChecked = true
                                var index =
                                    displayedBreeds?.indexOfFirst { b -> b.breedId == found?.breedId }
                                displayedBreeds[index] = found
                            }

                        }
                        displayedBreedsViewModel?.observedMutableBreeds?.value = displayedBreeds
                    }
                }
            })
    }

    /**
     * Observe breeds from repository, to load breeds to display.
     */
    private fun observeRepositoryBreeds() {
        this.displayedBreedsViewModel.observedRepositoryBreeds?.observe(this, Observer { breeds ->
            kotlin.run {
                Log.d("DEBUG$TAG", "List changed. ${breeds.size}")
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
            R.layout.fragment_recyclerview_breeds, container, false
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

    override fun transmitList(domainDisplayedModels: List<DomainDisplayedBreed>?) {
        Log.d("DEBUG$TAG", "The list is : ${domainDisplayedModels?.size} long")
        displayedBreedsViewModel?.observedMutableBreeds?.value = domainDisplayedModels
    }


}