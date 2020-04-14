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
import com.uldskull.rolegameassistant.fragments.adapter.IDEAL_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.viewmodels.IdealsViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "IdealsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class IdealsRecyclerViewFragment(activity: Activity) :
    CustomRecyclerViewFragment(activity),
    AdapterButtonListener<DomainIdeal> {

    /** ViewModel for bonds    **/
    private lateinit var idealsViewModel: IdealsViewModel

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
        Log.d("IdealsRecyclerViewFragment", "onCreate")
        idealsViewModel = getViewModel()
    }


    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
        Log.d("IdealsRecyclerViewFragment", "setRecyclerViewLayoutManager")
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
        Log.d("IdealsRecyclerViewFragment", "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_ideals, container, false
        )
        return initialRootView
    }

    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        Log.d("IdealsRecyclerViewFragment", "initializeRecyclerView")
        idealsRecyclerView = activity.findViewById(R.id.recycler_view_ideals)
                as RecyclerView?
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        Log.d("IdealsRecyclerViewFragment", "startObservation")
        this.idealsViewModel.ideals?.observe(this, Observer { ideals ->
            kotlin.run {
                ideals?.let { idealsAdapter?.setIdeals(it) }
            }
        })
    }

    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        Log.d("IdealsRecyclerViewFragment", "setRecyclerViewAdapter")
        idealsAdapter = IdealsAdapter(activity as Context, this)
        idealsRecyclerView?.adapter = idealsAdapter
    }

    companion object : CustomCompanion() {

        @JvmStatic
        override fun newInstance(activity: Activity): IdealsRecyclerViewFragment {
            Log.d("IdealsRecyclerViewFragment", "newInstance")
            val fragment = IdealsRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, IDEAL_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }

    }

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainIdeal?) {
        Log.d("IdealsRecyclerViewFragment", "itemPressed")
        if (domainModel != null) {
            if (domainModel.isChecked) {
                Log.d("ideal", "add $domainModel")
                newCharacterViewModel.addIdeal(domainModel)
            } else {
                Log.d("ideal", "remove $domainModel")
                newCharacterViewModel.removeIdeal(domainModel)
            }
        }
    }
}