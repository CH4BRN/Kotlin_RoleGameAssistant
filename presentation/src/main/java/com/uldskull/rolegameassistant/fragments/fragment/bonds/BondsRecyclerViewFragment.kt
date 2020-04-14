// File BondsRecyclerViewFragment.kt
// @Author pierre.antoine - 02/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bonds

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
import com.uldskull.rolegameassistant.fragments.adapter.BONDS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.DomainBond
import com.uldskull.rolegameassistant.viewmodels.BondsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "BondsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class BondsRecyclerViewFragment(activity: Activity) :
    CustomRecyclerViewFragment(activity), AdapterButtonListener<DomainBond> {
    /** ViewModel for bonds    **/
    private val bondsViewModel: BondsViewModel by sharedViewModel()

    /** Adapter for bonds recycler view    **/
    private var bondsAdapter: BondsAdapter? = null

    /** Recycler view for bonds   **/
    private var bondsRecyclerView: RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return initializeView(inflater, container)
    }

    override fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_bonds, container, false
        )
        return initialRootView
    }

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainBond?) {

        Log.d(TAG, "Item pressed for $domainModel")

        Log.d(TAG, "viewmodel bonds size before =" + bondsViewModel.bonds.value?.size)
        val deleteResult = bondsViewModel.deleteBond(domainModel)
        Log.d(TAG, "viewmodel bonds size after =" + bondsViewModel.bonds.value?.size)
        bondsRecyclerView?.requestFocus()
        Log.d(TAG, "Adapter size : ${bondsAdapter?.itemCount}")
    }

    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
        Log.d(TAG, "setRecyclerViewLayoutManager")
        bondsRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        Log.d(TAG, "initializeRecyclerView")
        bondsRecyclerView = activity.findViewById(R.id.recycler_view_bonds)
                as RecyclerView?
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        Log.d(TAG, "startObservation")
        this.bondsViewModel.bonds.observe(this, Observer { bonds ->
            kotlin.run {
                bonds?.let { bondsAdapter?.setBonds(it) }
            }
        })
    }


    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        Log.d(TAG, "setRecyclerViewAdapter")
        bondsAdapter = BondsAdapter(
            activity as Context,
            this
        )
        bondsRecyclerView?.adapter = bondsAdapter
    }


    companion object : CustomCompanion() {
        private const val TAG = "BondsRecyclerViewFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): BondsRecyclerViewFragment {
            Log.d(TAG, "newInstance")
            val fragment = BondsRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, BONDS_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }


    }

}