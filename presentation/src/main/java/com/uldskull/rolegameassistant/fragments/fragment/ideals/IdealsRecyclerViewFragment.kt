// File IdealsRecyclerViewFragment.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.ideals

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
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.IDEAL_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.viewmodels.IdealsViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "IdealsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class IdealsRecyclerViewFragment() :
    CustomRecyclerViewFragment(),
    AdapterButtonListener<DomainIdeal> {

    /** ViewModel for bonds    **/
    private val idealsViewModel: IdealsViewModel by sharedViewModel()

    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /** Adapter for ideals recycler view    **/
    private var idealsAdapter: IdealsAdapter? = null

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
            R.layout.fragment_recyclerview_ideals, container, false
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

        var observedIdeals = idealsViewModel.observedIdeals
        var gotIdeals: MutableList<DomainIdeal> = mutableListOf()
        if (idealsViewModel.displayedIdeals.isEmpty()) {
            Log.d(TAG, "displayedIdeals == null or empty")
            observedIdeals?.observe(this, Observer { domainIdeals ->
                Log.d(TAG, "observe")
                domainIdeals.forEach {
                    Log.d(TAG, "Got ideal : $it")
                    gotIdeals.add(it)
                }
                Log.d(TAG, "gotIdeals size : ${gotIdeals.size}")

                idealsViewModel.displayedIdeals = gotIdeals

                Log.d(
                    TAG,
                    "idealsViewModel.displayedIdeals  size : ${idealsViewModel.displayedIdeals.size}"
                )
                idealsAdapter?.setIdeals(idealsViewModel.displayedIdeals)
            })
        }

    }

    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        Log.d(TAG, "setRecyclerViewAdapter")
        if (activity != null) {
            idealsAdapter = IdealsAdapter(activity as Context, this)
            idealsAdapter?.setIdeals(idealsViewModel.displayedIdeals)
            idealsRecyclerView?.adapter = idealsAdapter
        }
    }

    companion object : CustomCompanion() {

        private const val TAG = "IdealsRecyclerViewFragment"
        @JvmStatic
        override fun newInstance(activity: Activity): IdealsRecyclerViewFragment {
            Log.d(TAG, "newInstance")
            val fragment = IdealsRecyclerViewFragment()
            fragment?.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, IDEAL_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainIdeal?, position:Int?) {
        Log.d(TAG, "itemPressed")
        if (domainModel != null) {
            if (domainModel.isChecked) {
                Log.d(TAG, "newCharacterViewModel add $domainModel")
                newCharacterViewModel.addIdeal(domainModel)
            } else {
                Log.d(TAG, "newCharacterViewModel remove $domainModel")
                newCharacterViewModel.removeIdeal(domainModel)
            }
            var temp = idealsViewModel.displayedIdeals
            Log.d(TAG, "dis size :  ${temp.size}")
            var index = temp.indexOf(domainModel)
            Log.d(TAG, "dis.removeAt($index)")
            temp.removeAt(index)
            Log.d(TAG, "dis size :  ${temp.size}")
            Log.d(TAG, "dis.add($domainModel)")
            temp.add(index, domainModel)
            Log.d(TAG, "dis size :  ${temp.size}")
            idealsViewModel.displayedIdeals = temp
        }
    }
}