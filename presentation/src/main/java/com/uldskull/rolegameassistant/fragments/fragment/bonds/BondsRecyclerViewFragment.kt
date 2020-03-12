// File BondsRecyclerViewFragment.kt
// @Author pierre.antoine - 02/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bonds

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
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "BondsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class BondsRecyclerViewFragment(activity: Activity) :
    CustomRecyclerViewFragment(activity) {
    /** ViewModel for bonds    **/
    private lateinit var bondsViewModel: BondsViewModel

    /** Adapter for bonds recycler view    **/
    private var bondsAdapter: BondsAdapter? = null

    /** Recycler view for bonds   **/
    private var bondsRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bondsViewModel = getViewModel()
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    override fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_bonds, container, false
        )
        return initialRootView
    }

    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
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
        bondsRecyclerView = activity.findViewById(R.id.recycler_view_bonds)
                as RecyclerView?
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        this.bondsViewModel.bonds.observe(this, Observer { bonds ->
            kotlin.run {
                bonds?.let { bondsAdapter?.setBonds(it) }
            }
        })
    }


    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        bondsAdapter = BondsAdapter(activity as Context)
        bondsRecyclerView?.adapter = bondsAdapter
    }


    companion object {

        @JvmStatic
        fun newInstance(activity: Activity, position: Int): BondsRecyclerViewFragment {
            val fragment = BondsRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

        private const val KEY_POSITION = "position"
    }
// TODO : Fill class.
}