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
class IdealsRecyclerViewFragment :
    CustomRecyclerViewFragment(),
    AdapterButtonListener<DomainIdeal> {

    /** ViewModel for bonds    **/
    private val idealsViewModel: IdealsViewModel by sharedViewModel()

    /**  ViewModel for new character  **/
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

        //  Observe
        observeSelectedCharacter()
        observeCharacterIdeals()
        observeIdealsMutableList()
        observeRepositoryIdeals()
    }

    private fun observeRepositoryIdeals() {
        idealsViewModel?.repositoryIdeals?.observe(this, Observer { domainIdealsList ->
            if(idealsViewModel?.mutableIdeals?.value == null){
                idealsViewModel?.mutableIdeals?.value = domainIdealsList.toMutableList()
            }
        })
    }

    /**
     * Observe the ideals mutable list.
     */
    private fun observeIdealsMutableList() {
        idealsViewModel?.mutableIdeals?.observe(this, Observer { domainIdealsList ->
            domainIdealsList?.forEach {
                Log.d("DEBUG$TAG", "Ideal : ${it?.idealName} is checked : ${it?.isChecked}")
            }
            idealsAdapter?.setIdeals(domainIdealsList?.toList())
        })
    }

    /**
     * Observe the character's ideals.
     */
    private fun observeCharacterIdeals() {
        idealsViewModel?.characterIdeals?.observe(
            this,
            Observer { domainIdeals: List<DomainIdeal?>? ->
                if (domainIdeals != null) {
                    Log.d("DEBUG$TAG", "Character ideals : ${domainIdeals?.size}")
                    Log.d(
                        "DEBUG$TAG",
                        "Mutable ideals : ${idealsViewModel?.mutableIdeals?.value?.size}"
                    )
                }
                idealsViewModel?.mutableIdeals?.value = domainIdeals?.toMutableList()
            })
    }

    /**
     * Observe the selected character to get its ideals.
     */
    private fun observeSelectedCharacter() {
        newCharacterViewModel?.selectedCharacter?.observe(this, Observer { domainCharacter ->
            domainCharacter?.characterIdeals?.forEach {
                Log.d("DEBUG$TAG", "Character Ideal ${it?.idealName} is checked : ${it?.isChecked}")
            }
            idealsViewModel?.characterIdeals?.value = domainCharacter?.characterIdeals?.toList()
        })
    }

    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        Log.d(TAG, "setRecyclerViewAdapter")
        if (activity != null) {
            idealsAdapter = IdealsAdapter(activity as Context, this)
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
    override fun itemPressed(domainModel: DomainIdeal?, position: Int?) {
        Log.d(TAG, "itemPressed")
        if (domainModel != null) {
            if (domainModel!!.isChecked != null) {
                if (domainModel!!.isChecked!!) {
                    Log.d("DEBUG$TAG", "newCharacterViewModel add ${domainModel.idealName}")
                    newCharacterViewModel.addIdeal(domainModel)
                } else {
                    Log.d("DEBUG$TAG", "newCharacterViewModel remove ${domainModel.idealName}")
                    newCharacterViewModel.removeIdeal(domainModel)
                }
            }

        }
    }
}