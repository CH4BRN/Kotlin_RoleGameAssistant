// File JobsFragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.occupations

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NEW_JOB_ACTIVITY
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.JOBS_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_JOBS_NEW_JOB
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.viewmodels.OccupationsViewModel
import kotlinx.android.synthetic.main.fragment_jobs.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "JobsFragment" :
 *   Fragment that manages and displays jobs.
 **/
class OccupationsFragment(activity: Activity) : CustomFragment(activity) {

    private val occupationsViewModel: OccupationsViewModel by sharedViewModel()

    fun startObservation() {
        Log.d(TAG, "startObservation")
        var gotOccupations: MutableList<DomainOccupation?> = mutableListOf()
        this.occupationsViewModel.observedOccupations?.observe(this, Observer { domainOccupations ->
            kotlin.run {
                domainOccupations?.let {
                    Log.d(TAG, "observedOccupations has changed.")
                    gotOccupations = domainOccupations.toMutableList()

                    occupationsViewModel.displayedOccupations = gotOccupations
                }
                occupationsAdapter =
                    ArrayAdapter(
                        activity,
                        android.R.layout.simple_spinner_item,
                        occupationsViewModel.displayedOccupations.map { o -> o?.activityName }
                    )

                setSpinnerAdapter()
            }
        })
    }

    private fun setSpinnerAdapter() {
        occupationsAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_occupations?.adapter = occupationsAdapter
    }

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_jobs, container, false
        )
        return initialRootView
    }

    /**
     * Called immediately after [.onCreateView]
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     * @param view The View returned by [.onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        setButtonAddJob()
        startObservation()
        setSpinnerOccupationsOnItemSelecteListener()
    }

    private fun setSpinnerOccupationsOnItemSelecteListener() {
        spinner_occupations.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            /**
             * Callback method to be invoked when the selection disappears from this
             * view. The selection can disappear for instance when touch is activated
             * or when the adapter becomes empty.
             *
             * @param parent The AdapterView that now contains no selected item.
             */
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            /**
             *
             * Callback method to be invoked when an item in this view has been
             * selected. This callback is invoked only when the newly selected
             * position is different from the previously selected position or if
             * there was no selected item.
             *
             * Implementers can call getItemAtPosition(position) if they need to access the
             * data associated with the selected item.
             *
             * @param parent The AdapterView where the selection happened
             * @param view The view within the AdapterView that was clicked
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var occupation = occupationsViewModel.displayedOccupations[position]
                if(occupation!= null){
                    Log.d(TAG, "Occupation $occupation")
                }


            }

        }
    }

    /**
     * Set the add job button.
     */
    private fun setButtonAddJob() {
        Log.d(TAG, "setButtonAddJob")
        if (btn_addJob != null) {
            btn_addJob!!.setOnClickListener {
                val intent = Intent(activity, NEW_JOB_ACTIVITY)
                startActivityForResult(intent, REQUEST_CODE_JOBS_NEW_JOB)
            }
        }
    }

    var occupationsAdapter: ArrayAdapter<String?> = ArrayAdapter(
        activity,
        android.R.layout.simple_spinner_item, emptyList()
    )


    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to [Activity.onResume] of the containing
     * Activity's lifecycle.
     */
    override

    fun onResume() {
        super.onResume()
        Log.i(TAG, NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = JOBS_FRAGMENT_POSITION
        Log.i(TAG, NewCharacterActivity.progression.value.toString())
    }

    companion object : CustomCompanion() {
        private const val TAG = "OccupationsFragment"
        @JvmStatic
        override fun newInstance(activity: Activity): OccupationsFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                OccupationsFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, JOBS_FRAGMENT_POSITION)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_jobs,
                OccupationsSkillsRecyclerViewFragment.newInstance(
                    activity
                )
            )

            return fragment
        }
    }


}