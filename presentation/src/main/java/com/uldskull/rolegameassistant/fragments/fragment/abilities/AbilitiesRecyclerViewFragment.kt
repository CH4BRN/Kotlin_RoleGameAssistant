// File AbilitiesRecyclerViewFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.abilities

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
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "AbilitiesRecyclerViewFragment" :
 *   Manage abilities's RecyclerView fragment.
 **/
class AbilitiesRecyclerViewFragment(activity: Activity) :
    CustomRecyclerViewFragment(activity) {
    /** ViewModel for abilities **/
    private lateinit var abilitiesViewModel: AbilitiesViewModel

    /** Adapter for abilities recycler view **/
    private var abilitiesAdapter: AbilitiesAdapter? = null

    /** Recycler View for abilities **/
    private var abilitiesRecyclerView: RecyclerView? = null

    /** Fragment fife-cycle   **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        abilitiesViewModel = getViewModel()
    }

    /** Fragment life-cycle **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return initializeView(inflater, container)
    }


    /** Initialize the view **/
    override fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_recyclerview_abilities, container, false
        )
        return initialRootView
    }

    /** Fragment life-cycle **/
    override fun onResume() {
        super.onResume()
        NewCharacterActivity.progression.value = 2
    }

    /** Fragment life-cycle **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
    }

    /** Initialize recycler view    **/
    override fun initializeRecyclerView() {
        abilitiesRecyclerView = activity.findViewById(R.id.recycler_view_abilities)
                as RecyclerView?
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        this.abilitiesViewModel.abilities.observe(this, Observer { abilities ->
            kotlin.run {
                abilities?.let { abilitiesAdapter?.setAbilities(it) }

            }
        })
    }

    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        abilitiesAdapter = AbilitiesAdapter(activity as Context)
        abilitiesRecyclerView?.adapter = abilitiesAdapter
    }

    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
        abilitiesRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }


    companion object {


        @JvmStatic
        fun newInstance(activity: Activity, position: Int): AbilitiesRecyclerViewFragment {
            val fragment = AbilitiesRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment

        }

        private const val KEY_POSITION = "position"


    }
}