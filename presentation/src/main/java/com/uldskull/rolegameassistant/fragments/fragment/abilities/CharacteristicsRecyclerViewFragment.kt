// File AbilitiesRecyclerViewFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.abilities

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
import com.uldskull.rolegameassistant.fragments.adapter.ABILITIES_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import kotlinx.android.synthetic.main.fragment_recyclerview_abilities.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "AbilitiesRecyclerViewFragment" :
 *   Manage abilities's RecyclerView fragment.
 **/
class CharacteristicsRecyclerViewFragment(activity: Activity) :
    CustomRecyclerViewFragment(activity) {
    /** ViewModel for abilities **/
    private lateinit var characteristicsViewModel: CharacteristicsViewModel

    /** Adapter for abilities recycler view **/
    private var characteristicsAdapter: CharacteristicsAdapter? = null

    /** Adapter to display disabled edit texts **/
    private var abilitiesDisabledAdapter: CharacteristicsDisabledAdapter? = null

    /** Recycler View for abilities **/
    private var abilitiesRecyclerView: RecyclerView? = null

    /** Fragment fife-cycle   **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characteristicsViewModel = getViewModel()
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        setButtonUsePoints()
        setButtonRoll()
    }

    /**
     * Set the button that launch a dice roll for each ability
     */
    private fun setButtonRoll() {
        if (btn_roll != null) {
            btn_roll.setOnClickListener {
                Log.d(this.javaClass.simpleName, "Roll")
            }
        }
    }

    /**
     * Set the button that allow the user to use his points
     * manually.
     */
    private fun setButtonUsePoints() {
        if (btn_use_point != null) {
            btn_use_point.setOnClickListener {
                Log.d(this.javaClass.simpleName, "Use points")
                abilitiesRecyclerView?.adapter = characteristicsAdapter
                setRecyclerViewLayoutManager()
            }
        }
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
        this.characteristicsViewModel.characteristicsToChoose.observe(this, Observer { abilities ->
            kotlin.run {
                abilities?.let {
                    Log.d(
                        "abilitiesDisabledADAPTER", when (abilitiesDisabledAdapter) {
                            null -> "Is null"
                            else -> "Is not null"
                        }
                    )
                    abilitiesDisabledAdapter?.setAbilities(it)

                    Log.d("ABILITIES IT SIZE : ", it.size.toString())
                }
                abilities?.let {
                    Log.d(
                        "abilitiesADAPTER", when (characteristicsAdapter) {
                            null -> "Is null"
                            else -> "Is not null"
                        }
                    )
                    characteristicsAdapter?.setAbilities(it)

                    Log.d("ABILITIES IT SIZE : ", it.size.toString())
                }

            }
        })
    }

    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        setRecyclerViewDisabledAdapter()
    }

    /**
     * Set Disabled recyclerview adapter
     */
    private fun setRecyclerViewDisabledAdapter() {
        abilitiesDisabledAdapter = CharacteristicsDisabledAdapter(activity as Context)
        characteristicsAdapter = CharacteristicsAdapter(activity as Context)
        abilitiesRecyclerView?.adapter = abilitiesDisabledAdapter
    }

    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
        abilitiesRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }


    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity): CharacteristicsRecyclerViewFragment {
            val fragment = CharacteristicsRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, ABILITIES_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }


    }
}