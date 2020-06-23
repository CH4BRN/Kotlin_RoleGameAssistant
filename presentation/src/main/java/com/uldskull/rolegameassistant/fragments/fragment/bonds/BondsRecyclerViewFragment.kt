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
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.BONDS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.DomainBond
import com.uldskull.rolegameassistant.viewmodels.BondsViewModel
import com.uldskull.rolegameassistant.viewmodels.character.NewCharacterViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "BondsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class BondsRecyclerViewFragment :
    CustomRecyclerViewFragment(),
    CustomAdapterButtonListener<DomainBond> {
    /** ViewModel for bonds    **/
    private val bondsViewModel: BondsViewModel by sharedViewModel()

    /** ViewModel for character **/
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /** Adapter for bonds recycler view    **/
    private var bondsAdapter: BondsAdapter? = null

    /** Recycler view for bonds   **/
    private var bondsRecyclerView: RecyclerView? = null


    /**
     * Fragment life-cycle.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return initializeView(inflater, container)
    }

    /**
     * Initialize the view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_bonds_recyclerview, container, false
        )
        return initialRootView
    }

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainBond?, position: Int?) {

        Log.d(TAG, "Item pressed for $domainModel")

        Log.d(TAG, "viewmodel bonds size before =" + bondsViewModel.bonds.value?.size)
        bondsViewModel.deleteBond(domainModel)
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
        bondsRecyclerView = activity?.findViewById(R.id.recycler_view_bonds)
                as RecyclerView?
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        Log.d(TAG, "startObservation")
        this.bondsViewModel.bonds.observe(this, Observer { bonds ->
            kotlin.run {
                if(bonds != null){
                    Log.d("DEBUG$TAG", "Bonds size : ${bonds.size}")
                    bonds.let { bondsAdapter?.setBonds(it) }
                }

            }
        })

        this.newCharacterViewModel.selectedCharacter.observe(this, Observer {
            Log.d("DEBUG$TAG", "Character : $it")
            val bonds = it?.characterBonds
            bondsViewModel.bonds.value = bonds
        })
    }


    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        Log.d(TAG, "setRecyclerViewAdapter")
        if (activity != null) {
            bondsAdapter = BondsAdapter(
                activity as Context,
                this
            )
            bondsRecyclerView?.adapter = bondsAdapter
        }

    }


    companion object : CustomCompanion() {
        private const val TAG = "BondsRecyclerViewFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): BondsRecyclerViewFragment {
            Log.d(TAG, "newInstance")
            val fragment = BondsRecyclerViewFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, BONDS_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }
}