// File IdealsRecyclerViewFragment.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.ideals

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "IdealsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class IdealsRecyclerViewFragment(activity: Activity) : CustomRecyclerViewFragment(activity) {

    /** ViewModel for bonds    **/
    private lateinit var idealsViewModel: IdealsViewModel

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
        idealsViewModel = getViewModel()
    }


    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
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
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_ideals, container, false
        )
        return initialRootView
    }

    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        idealsRecyclerView = activity.findViewById(R.id.recycler_view_ideals)
                as RecyclerView?
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        this.idealsViewModel.ideals.observe(this, Observer { ideals ->
            kotlin.run {
                ideals?.let { idealsAdapter?.setIdeals(it) }
            }
        })
    }

    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        idealsAdapter = IdealsAdapter(activity as Context)
        idealsRecyclerView?.adapter = idealsAdapter
    }

    companion object : CustomCompanion() {

        @JvmStatic
        override fun newInstance(activity: Activity, position: Int): IdealsRecyclerViewFragment {
            val fragment = IdealsRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

    }
}