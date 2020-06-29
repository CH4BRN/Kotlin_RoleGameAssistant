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
import com.uldskull.rolegameassistant.fragments.fragment.breed.BreedsRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.IDEAL_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.DomainIdeal
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.viewmodels.DerivedValuesViewModel
import com.uldskull.rolegameassistant.viewmodels.IdealsViewModel
import com.uldskull.rolegameassistant.viewmodels.character.NewCharacterViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "IdealsRecyclerViewFragment" :
 *   Holds ideals recycler view
 **/
class IdealsRecyclerViewFragment :
    CustomRecyclerViewFragment() {

    /** ViewModel for bonds    **/
    private val idealsViewModel: IdealsViewModel by sharedViewModel()

    /**  ViewModel for derived values **/
    private val derivedValuesViewModel: DerivedValuesViewModel by sharedViewModel()

    /** Adapter for ideals recycler view    **/
    private var idealsAdapter: IdealsToCheckAdapter? = null

    /** Recycler view for ideals   **/
    private var idealsRecyclerView: RecyclerView? = null

    /**  Array for displayed ideals **/
    private var idealsValuesArray: ArrayList<DomainIdeal>? = ArrayList()

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
        observeRepositoryIdeals()
    }


    /**
     * Observe repository's ideals
     */
    private fun observeRepositoryIdeals() {
        idealsViewModel.repositoryIdeals?.observe(
            this,
            Observer { ideals ->
                var mutableIdeals = ideals.toMutableList()
                ideals.let {
                    idealsViewModel?.observableSelectedIdeals?.observe(this, Observer { list ->
                        list.forEach { id ->
                            Log.d("DEBUG$TAG", "Selected ideals = $id")
                            if (id != null) {
                                var index = mutableIdeals?.indexOfFirst { i -> i.idealId == id }
                                Log.d("DEBUG$TAG", "Index : $index")
                                if (index != null && index != -1) {
                                    mutableIdeals[index].isChecked = true
                                }
                            }
                        }
                    })
                    if (it != null && it.isNotEmpty()) {
                        idealsAdapter = IdealsToCheckAdapter(
                            activity as Context
                        )

                        idealsAdapter?.setItems(mutableIdeals as List<DomainIdeal>)
                        idealsRecyclerView?.adapter = idealsAdapter

                        idealsRecyclerView?.layoutManager = LinearLayoutManager(
                            activity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    }
                }
            }
        )
    }








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
                    activity as Context
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

}