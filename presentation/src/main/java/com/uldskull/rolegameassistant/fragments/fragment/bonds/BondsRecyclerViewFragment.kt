// File BondsRecyclerViewFragment.kt
// @Author pierre.antoine - 02/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bonds

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "BondsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class BondsRecyclerViewFragment : Fragment() {
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

    /** Fragment Lifecycle  **/
    override fun onResume() {
        super.onResume()
        NewCharacterActivity.progression.value = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    private fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_bonds, container, false
        )
        return initialRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bondsRecyclerView = activity?.findViewById(R.id.recycler_view_bonds)
                as RecyclerView?

        startBondsObservation()
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /** Set recycler view layout manager    **/
    private fun setRecyclerViewLayoutManager() {
        bondsRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }


    /** Set recycler view adapter   **/
    private fun setRecyclerViewAdapter() {
        bondsAdapter = BondsAdapter(activity as Context)
        bondsRecyclerView?.adapter = bondsAdapter
    }

    private fun startBondsObservation() {
        this.bondsViewModel.bonds.observe(this, Observer { bonds ->
            kotlin.run {
                bonds?.let { bondsAdapter?.setBonds(it) }
            }
        })
    }

    private lateinit var initialRootView: View

    companion object {

        @JvmStatic
        fun newInstance(activity: Activity, position: Int): BondsRecyclerViewFragment {
            val fragment = BondsRecyclerViewFragment()
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

        private const val KEY_POSITION = "position"
    }
// TODO : Fill class.
}