// File JobsFragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.occupations

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NEW_JOB_ACTIVITY
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.NewSkillActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.OCCUPATIONS_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_JOBS_NEW_JOB
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.viewmodels.OccupationsViewModel
import kotlinx.android.synthetic.main.fragment_occupations.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "JobsFragment" :
 *   Fragment that manages and displays jobs.
 **/
class OccupationsFragment(activity: Activity) : CustomFragment(activity) {

    //  VIEWMODELS
    private val occupationsViewModel: OccupationsViewModel by sharedViewModel()

    //  ADAPTER
    /// Occupation's spinner
    var occupationsAdapter: ArrayAdapter<String?> = ArrayAdapter(
        activity,
        android.R.layout.simple_spinner_item
    )

    //  SPINNER



    private fun setSpinnerAdapter() {
        occupationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_occupations?.adapter = occupationsAdapter
    }

    private fun setSpinnerOccupationsOnItemSelectedListener() {
        spinner_occupations.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            /**
             * Callback method to be invoked when the selection disappears from this
             * view. The selection can disappear for instance when touch is activated
             * or when the adapter becomes empty.
             *
             * @param parent The AdapterView that now contains no selected item.
             */
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d(TAG, "onNothingSelected")
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
                Log.d(TAG, "onItemSelected")

                Log.d(TAG, "position : $position")

                if (position == 0) {
                    emptyOccupationTextViews()
                } else {
                    var occupation = occupationsViewModel.displayedOccupations[position]
                    if (occupation != null) {
                        occupationsViewModel.selectedOccupation?.value = occupation
                        //  Sets the selected occupation index
                        occupationsViewModel?.selectedOccupationIndex?.value = position

                    }
                }
            }
        }
    }







    fun startObservation() {
        Log.d(TAG, "startObservation")
        observeOccupations()
        observeSelectedOccupation()
        observeOccupationIncome()
        observeOccupationContact()
        //  Observes selected occupation special
        observeSelectedOccupationSpecial()
        observeSelectedOccupationIndex()

    }

    private fun observeSelectedOccupationIndex() {
        Log.d(TAG, "observeSelectedOccupationIndex")
        if (this.occupationsViewModel.selectedOccupationIndex != null) {
            this.occupationsViewModel.selectedOccupationIndex?.observe(
                this,
                Observer { selectedOccupationIndex: Int ->
                    kotlin.run {
                        Log.d(
                            TAG,
                            "selectedOccupationIndex has changed : \n$selectedOccupationIndex"
                        )
                        spinner_occupations.setSelection(selectedOccupationIndex)
                    }
                })
        }
    }

    /**
     * Observes the selected occupation special.
     */
    private fun observeSelectedOccupationSpecial() {
        Log.d(TAG, "observeSelectedOccupationSpecial()")
        if (this.occupationsViewModel?.selectedOccupationSpecial != null) {
            this.occupationsViewModel?.selectedOccupationSpecial?.observe(
                this,
                Observer { selectedOccupationSpecial: String ->
                    kotlin.run {
                        fragmentOccupations_tv_occupationSpecialValue.text =
                            selectedOccupationSpecial
                    }
                })
        }
    }


    /**
     * Observes occupation from the repository.
     */
    private fun observeOccupations() {
        var gotOccupations: MutableList<DomainOccupation?>
        this.occupationsViewModel.observedOccupations?.observe(this, Observer { domainOccupations ->
            kotlin.run {
                domainOccupations?.let {
                    Log.d(TAG, "observedOccupations has changed")
                    gotOccupations = domainOccupations.toMutableList()
                    occupationsViewModel.displayedOccupations = gotOccupations
                }

                var occupationList =
                    occupationsViewModel.displayedOccupations.map { occupation -> occupation?.occupationName }
                        .toMutableList()
                occupationsAdapter =
                    ArrayAdapter(
                        activity,
                        android.R.layout.simple_spinner_item,
                        occupationList
                    )

                setSpinnerAdapter()
            }
        })
    }

    private fun observeSelectedOccupation() {

        occupationsViewModel?.selectedOccupation?.observe(
            this,
            Observer { domainOccupation: DomainOccupation ->
                kotlin.run {

                    //  Sets the occupation income
                    occupationsViewModel?.selectedOccupationIncome?.value =
                        domainOccupation?.occupationIncome
                    //  Sets the occupation contacts.
                    occupationsViewModel?.selectedOccupationContacts?.value =
                        domainOccupation?.occupationContacts
                    //  Sets the occupation special.
                    occupationsViewModel?.selectedOccupationSpecial?.value =
                        domainOccupation?.occupationSpecial
                    Log.d(TAG, "selectedOccupation : $domainOccupation")


                }
            })
    }


    private fun observeOccupationContact() {
        if (this.occupationsViewModel.selectedOccupationContacts != null) {
            this.occupationsViewModel.selectedOccupationContacts!!.observe(
                this,
                Observer { contacts ->
                    run {
                        Log.d(TAG, "selectedOccupationContacts has changed : \n$contacts")
                        fragmentOccupations_tv_occupationContactsValue.text = contacts
                    }
                })
        }
    }

    private fun observeOccupationIncome() {
        if (this.occupationsViewModel.selectedOccupationIncome != null) {
            this.occupationsViewModel.selectedOccupationIncome!!.observe(this, Observer { income ->
                kotlin.run {
                    Log.d(TAG, "selectedOccupationIncome has changed \n$income")
                    fragmentOccupations_tv_occupationIncomeValue.text = income
                }
            })
        }
    }


    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_occupations, container, false
        )
        return initialRootView
    }

    /**
     * Fragment life-cycle : Called when the view is created.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

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

        var selectedOccupationIndex = occupationsViewModel?.selectedOccupationIndex?.value
        if (selectedOccupationIndex != null) {
            Log.d(TAG, "selectedOccupationIndex :$selectedOccupationIndex")
            spinner_occupations.setSelection(selectedOccupationIndex)
        }
        setButtonAddJob()
        startObservation()
        setSpinnerOccupationsOnItemSelectedListener()
        //  setSpinnerOccupationCurrentSelectedOccupation()
    }




    private fun emptyOccupationTextViews() {
        fragmentOccupations_tv_occupationIncomeValue.text = ""
        fragmentOccupations_tv_occupationContactsValue.text = ""
        fragmentOccupations_tv_occupationSpecialValue.text = ""
    }


    /**
     * Set the add job button.
     */
    private fun setButtonAddJob() {
        Log.d(TAG, "setButtonAddJob")
        if (btn_addOccupation != null) {
            btn_addOccupation!!.setOnClickListener {
                val intent = Intent(activity, NEW_JOB_ACTIVITY)
                startActivityForResult(intent, REQUEST_CODE_JOBS_NEW_JOB)
            }
        }
    }


    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to [Activity.onResume] of the containing
     * Activity's lifecycle.
     */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

        Log.i(TAG, NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = OCCUPATIONS_FRAGMENT_POSITION
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
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_recyclerView_occupationsSkills,
                OccupationsSkillsRecyclerViewFragment.newInstance(activity)
            )

            args.putInt(KEY_POSITION, OCCUPATIONS_FRAGMENT_POSITION)
            fragment.arguments = args


            return fragment
        }
    }


}