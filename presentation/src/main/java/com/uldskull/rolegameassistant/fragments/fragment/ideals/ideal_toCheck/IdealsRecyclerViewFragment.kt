// File IdealsRecyclerViewFragment.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.ideals.ideal_toCheck

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
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.IDEAL_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.DomainIdeal
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.viewmodels.IdealsViewModel
import com.uldskull.rolegameassistant.viewmodels.character.NewCharacterViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "IdealsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class IdealsRecyclerViewFragment :
    CustomRecyclerViewFragment(),
    CustomAdapterButtonListener<DomainIdeal> {

    /** ViewModel for bonds    **/
    private val idealsViewModel: IdealsViewModel by sharedViewModel()

    /**  ViewModel for new character  **/
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /** Adapter for ideals recycler view    **/
    private var idealsAdapter: IdealsToCheckAdapter? = null

    /** Recycler view for ideals   **/
    private var idealsRecyclerView: RecyclerView? = null

    /**
     * Called to do initial creation of a fragment.  This is called after
     * [.onAttach] and before
     * [.onCreateView].
     *
     *
     * Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see [.onActivityCreated].
     *
     *
     * Any restored child fragments will be created before the base
     * `Fragment.onCreate` method returns.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }


    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
        Log.d(TAG, "setRecyclerViewLayoutManager")

        idealsRecyclerView?.layoutManager = LinearLayoutManager(
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
            R.layout.fragment_ideals_recyclerview, container, false
        )
        return initialRootView
    }

    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        Log.d(TAG, "initializeRecyclerView")
        idealsRecyclerView = activity?.findViewById(R.id.recycler_view_ideals)
                as RecyclerView?
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        Log.d(TAG, "startObservation")

        //  Observe
        observeSelectedCharacter()
        observeIdealsMutableList()
        observeRepositoryIdeals()
    }

    /**
     * Observe repository's ideals
     */
    private fun observeRepositoryIdeals() {
        idealsViewModel.repositoryIdeals?.observe(this, Observer { domainIdealsList ->


            idealsViewModel.mutableIdeals?.value = domainIdealsList.toMutableList()

        })
    }

    /**
     * Observe the ideals mutable list.
     */
    private fun observeIdealsMutableList() {
        idealsViewModel.mutableIdeals?.observe(this, Observer { domainIdealsList ->
            Log.d("DEBUG$TAG", "Mutable ideals")

            domainIdealsList?.forEach {
                Log.d("DEBUG$TAG", "Ideal : ${it?.idealName} is checked : ${it?.isChecked}")
            }
            idealsAdapter?.setIdeals(domainIdealsList?.toList())
            idealsRecyclerView?.adapter = idealsAdapter
        })
    }


    /**
     * Observe the selected character to get its ideals.
     */
    private fun observeSelectedCharacter() {
        newCharacterViewModel.selectedCharacter.observe(this, Observer { domainCharacter ->
            if (domainCharacter != null) {
                if (characterIdealsAreNotNullOrEmpty(domainCharacter)) {
                    val characterIdeals = domainCharacter.characterIdeals
                    Log.d("DEBUG$TAG", "Character ideals is null = ${characterIdeals == null}")
                    var test = true
                    characterIdeals?.forEach { i ->
                        if (i == null) {
                            test = false
                        }
                    }

                    if (test) {
                        if (characterIdeals != null && !characterIdeals.isNullOrEmpty()) {
                            var count: Int? = characterIdeals.count { i -> i?.isChecked!! }
                            Log.d("DEBUG$TAG", "characterIdeals count : $count")

                            idealsViewModel.mutableIdeals?.value = domainCharacter.characterIdeals
                            count =
                                idealsViewModel.mutableIdeals?.value?.count { i -> i?.isChecked!! }
                            Log.d("DEBUG$TAG", "mutableIdeals count : $count")
                        }
                    }
                }
            }
        })
    }

    /**
     * Checks if ideals are not null or empty
     */
    private fun characterIdealsAreNotNullOrEmpty(domainCharacter: DomainCharacter) =
        domainCharacter.characterIdeals != null && !domainCharacter.characterIdeals.isNullOrEmpty()

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to [Activity.onResume] of the containing
     * Activity's lifecycle.
     */
    override fun onResume() {
        super.onResume()
        Log.d("DEBUG${TAG}", "OnResume")
        idealsViewModel.refreshDataFromRepository()
    }

    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        Log.d(TAG, "setRecyclerViewAdapter")
        if (activity != null) {
            idealsAdapter =
                IdealsToCheckAdapter(
                    activity as Context,
                    this
                )
            idealsRecyclerView?.adapter = idealsAdapter
        }
    }

    companion object : CustomCompanion() {

        private const val TAG = "IdealsRecyclerViewFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): IdealsRecyclerViewFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                IdealsRecyclerViewFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, IDEAL_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainIdeal?, position: Int?) {
        Log.d(TAG, "itemPressed")
        if (domainModel != null) {

            val ideals = idealsViewModel.mutableIdeals?.value
            var count = ideals?.count { i -> i?.isChecked!! }
            Log.d("DEBUG$TAG", "Checked Count = $count")

            val index = ideals?.indexOfFirst { i -> i?.idealId == domainModel.idealId }
            if (index != null) {
                ideals[index] = domainModel
            }
            count = ideals?.count { i -> i?.isChecked!! }

            Log.d("DEBUG$TAG", "Checked Count = $count")
            idealsViewModel.mutableIdeals?.value = ideals
            newCharacterViewModel.currentCharacter?.characterIdeals = ideals

        }
    }
}