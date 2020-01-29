// File AbilitiesRecyclerViewFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.fragments.abilities

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
import com.uldskull.rolegameassistant.ui.new_character.activities.NewCharacterActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "AbilitiesRecyclerViewFragment" :
 *   Manage abilities's RecyclerView fragment.
 **/
class AbilitiesRecyclerViewFragment : Fragment() {
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
        NewCharacterActivity.progression.value = arguments?.getInt(KEY_POSITION, -1)
        return initializeView(inflater, container)
    }

    /** Initialize the view **/
    private fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_recyclerview_abilities, container, false
        )
        return initialRootView
    }

    /** Fragment life-cycle **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        abilitiesRecyclerView = activity?.findViewById(R.id.recycler_view_abilities)
                as RecyclerView?

        abilitiesAdapter = AbilitiesAdapter(activity as Context)

        startAbilitiesObservation()

        abilitiesRecyclerView?.adapter = abilitiesAdapter

        abilitiesRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    /** Observe ViewModel's abilities   **/
    private fun startAbilitiesObservation() {
        this.abilitiesViewModel.abilities.observe(this, Observer { abilities ->
            kotlin.run {
                abilities?.let { abilitiesAdapter?.setAbilities(it) }

            }
        })
    }

    companion object {


        @JvmStatic
        fun newInstance(activity: Activity, position: Int): AbilitiesRecyclerViewFragment {
            var fragment = AbilitiesRecyclerViewFragment()
            var args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment

        }

        const val KEY_POSITION = "position"
        private lateinit var initialRootView: View

    }
}