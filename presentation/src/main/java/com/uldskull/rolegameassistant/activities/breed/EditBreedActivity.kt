// File NewBreedActivity.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.breed

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.core.CustomActivity
import com.uldskull.rolegameassistant.activities.core.replaceFragment
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.bars.NavigationBarFragment
import com.uldskull.rolegameassistant.fragments.fragment.breed.BreedsNamesAdapter
import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.viewmodels.breeds.DisplayedBreedsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewBreedActivity" :
 *   Activity that manages the new breed creation.
 **/
class EditBreedActivity : CustomActivity(), CustomAdapterButtonListener<DomainDisplayedBreed> {
    companion object {
        private const val TAG = "NewBreedActivity"
    }

    /**
     * Breeds view model
     */
    private lateinit var breedsViewModel: DisplayedBreedsViewModel

    /**
     * Breeds name recycler view
     */
    private var breedsNamesRecyclerView: RecyclerView? = null

    /**
     * Selected breed name text view.
     */
    var editTextSelectedBreedName: EditText? = null

    /**
     * Selected breed description text view.
     */
    private var editTextSelectedBreedDescription: EditText? = null

    /**
     * Selected breed bonus text viex.
     */
    private var editTextSelectedBreedBonus:EditText? = null
    /**
     * Add breed button.
     */
    private var imageButtonAddBreed: ImageButton? = null

    /**
     * Activity lifecycle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breed_edit)
        deserializeWidgets()
        initializeWidgets()
        loadNavigationBarFragment()
    }

    /**
     * Initialize the activity ViewModels
     */
    override fun initializeViewModels() {
        breedsViewModel = getViewModel()
    }

    /**
     * Deserialize widgets
     */
    override fun deserializeWidgets() {
        breedsNamesRecyclerView =
            this.findViewById(R.id.activityEditBreed_recyclerView_breedsToChoose)
        editTextSelectedBreedName = this.findViewById(R.id.activityEditBreed_editText_breedName)
        editTextSelectedBreedDescription =
            this.findViewById(R.id.activityEditBreed_editText_breedDescription)
        imageButtonAddBreed = this.findViewById(R.id.activityEditBreed_imageButton_addBreed)
        editTextSelectedBreedBonus = this.findViewById(R.id.activityEditBreed_editText_breedBonus)
    }

    /**
     * Initialize the widgets
     */
    override fun initializeWidgets() {
        initializeAddBreedImageButton()
    }

    /**
     * Initialize the add breed button
     */
    private fun initializeAddBreedImageButton() {
        imageButtonAddBreed?.setOnClickListener {
            var breedId = breedsViewModel.saveOne(
                DomainDisplayedBreed(
                    breedDescription = resources.getString(R.string.type_a_breed_description),
                    breedHealthBonus = 0,
                    breedId = null,
                    breedIsChecked = false,
                    breedName = resources.getString(R.string.type_a_breed_name)
                )
            )
            if (breedId != null) {
                var newBreed = breedsViewModel.findBreedWithId(breedId)
                Log.d("DEBUG$TAG", "Breed id : $breedId")
                breedsViewModel.observableMutableSelectedBreed.value = newBreed
            }
        }
    }

    /**
     * Start livedata observation
     */
    override fun startObservation() {
        observeRepositoryBreeds()
        observeSelectedBreed()
    }

    /**
     * Observe the selected breed
     */
    private fun observeSelectedBreed() {
        breedsViewModel.observableMutableSelectedBreed.observe(this, Observer { domainModel ->
            editTextSelectedBreedDescription?.setText(domainModel?.breedDescription.toString())
            editTextSelectedBreedName?.setText(domainModel?.breedName.toString())
            editTextSelectedBreedBonus?.setText(domainModel?.breedHealthBonus.toString())

            breedsViewModel?.observableRepositoryBreeds?.observe(this, Observer {
                var position = it.indexOfFirst { b -> b.breedId == domainModel?.breedId }
            })

        })
    }

    /**
     * Start repository breeds observation
     */
    private fun observeRepositoryBreeds() {
        breedsViewModel.observableRepositoryBreeds?.observe(this, Observer { displayedBreeds ->
            displayedBreeds.let { displayedBreedsList ->
                var breedNamesAdapter: BreedsNamesAdapter = BreedsNamesAdapter(
                    context = this,
                    editBreedsActivity_customButtonListener = this
                )
                breedNamesAdapter.setItems(displayedBreedsList)
                breedsNamesRecyclerView?.adapter = breedNamesAdapter
                val layoutManager = LinearLayoutManager(
                    this,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                breedsNamesRecyclerView?.layoutManager = layoutManager

            }
        })
    }


    /**
     * Load the navigation bar fragment.
     */
    private fun loadNavigationBarFragment() {
        Log.d(TAG, "loadNavigationBarFragment")
        this.replaceFragment(
            R.id.activityEditBreed_layout_navigationBar,
            NavigationBarFragment.newInstance(this)
        )
    }

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainDisplayedBreed?, position: Int?) {
        breedsViewModel.observableMutableSelectedBreed.value = domainModel

    }
}