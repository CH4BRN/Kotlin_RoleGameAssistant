// File IdealsRecyclerViewFragment.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.ideals

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
 *   Class "IdealsRecyclerViewFragment" :
 *   TODO: Fill class use.
 **/
class IdealsRecyclerViewFragment : Fragment() {

    /** ViewModel for bonds    **/
    private lateinit var idealsViewModel: IdealsViewModel

    /** Adapter for ideals recycler view    **/
    private var idealsAdapter: IdealsAdapter? = null

    /** Recycler view for ideals   **/
    private var idealsRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idealsViewModel = getViewModel()
    }


    /** Fragment Lifecycle  **/
    override fun onResume() {
        super.onResume()
        NewCharacterActivity.progression.value = 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idealsRecyclerView = activity?.findViewById(R.id.recycler_view_ideals)
                as RecyclerView?
        startIdealsObservation()
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }


    /** Set recycler view layout manager    **/
    private fun setRecyclerViewLayoutManager() {
        idealsRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_ideals, container, false
        )
        return initialRootView
    }

    /** Set recycler view adapter   **/
    private fun setRecyclerViewAdapter() {
        idealsAdapter = IdealsAdapter(activity as Context)
        idealsRecyclerView?.adapter = idealsAdapter
    }

    private fun startIdealsObservation() {
        this.idealsViewModel.ideals.observe(this, Observer { ideals ->
            kotlin.run {
                ideals?.let { idealsAdapter?.setIdeals(it) }
            }
        })
    }

    private lateinit var initialRootView: View

    companion object {

        @JvmStatic
        fun newInstance(activity: Activity, position: Int): IdealsRecyclerViewFragment {
            val fragment = IdealsRecyclerViewFragment()
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

        private const val KEY_POSITION = "position"
    }
}