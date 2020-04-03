// File BreedsRecyclerViewFragment.kt
// @Author pierre.antoine - 26/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.basicinfo.breed

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
import com.uldskull.rolegameassistant.fragments.adapter.BREED_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.viewmodels.BreedsViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_recyclerview_breeds.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "BreedsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class BreedsRecyclerViewFragment(activity: Activity) :
    CustomRecyclerViewFragment(activity),
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
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /**
     * Adapter for races recycler view
     */
    private var breedsAdapter: BreedsAdapter? = null

    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        breedsRecyclerView = activity.findViewById(R.id.recycler_view_breeds)
                as RecyclerView?
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        this.breedsViewModel.result?.observe(this, Observer { races ->
            kotlin.run {
                races?.let {
                    breedsAdapter?.setBreeds(it)
                    Log.d("RACES IT SIZE ", it.size.toString())
                }
            }
        })
    }

    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {
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
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_breeds, container, false
        )
        return initialRootView
    }

    /**
     * Called by the adapter when a breed is pressed.
     */
    override fun itemPressed(t: DomainBreed?) {
        Log.d("BreedsRecyclerViewFragment", "Button pressed")
        recycler_view_breeds?.requestFocus()
        newCharacterViewModel.characterBreed = t
    }

    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity): BreedsRecyclerViewFragment {
            val fragment =
                BreedsRecyclerViewFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, BREED_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }


}