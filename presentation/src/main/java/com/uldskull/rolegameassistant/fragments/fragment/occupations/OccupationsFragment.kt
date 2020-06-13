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
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_JOBS_NEW_JOB
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.OCCUPATIONS_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationSkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationsViewModel
import kotlinx.android.synthetic.main.fragment_occupations.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "JobsFragment" :
 *   Fragment that manages and displays jobs.
 **/
class OccupationsFragment : CustomFragment() {

    //  VIEWMODELS
    private val occupationsViewModel: OccupationsViewModel by sharedViewModel()
    private val occupationSkillsViewModel: OccupationSkillsViewModel by sharedViewModel()
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    //  ADAPTER
    /// Occupation's spinner
    var occupationsAdapter: ArrayAdapter<String?>? = null

    private fun initializeOccupationsAdapter() {
        if (activity != null) {
            occupationsAdapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_spinner_item
            )
        }
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
                    occupationsViewModel?.selectedOccupation?.value =
                        occupationsViewModel?.repositoryOccupations?.value?.get(position)
                }
            }
        }
    }


    fun startObservation() {
        Log.d(TAG, "startObservation")
        //  Observe occupation from repository
        observeOccupations()
        //  Observe selected occupation
        observeSelectedOccupation()
        //  Observes selected occupations values.
        observeSelectedOccupationValues()
        // Observe Selected character
        observeSelectedCharacter()

    }

    private fun observeSelectedOccupationValues() {
        //  Observe occupation income
        observeSelectedOccupationIncome()
        //  Observe occupation contact
        observeSelectedOccupationContact()
        //  Observes selected occupation special
        observeSelectedOccupationSpecial()
    }

    private fun observeOccupations() {
        //  Observe displayed string occupations
        observeDisplayedOccupations()
    }

    private fun observeDisplayedOccupations() {
        occupationsViewModel?.displayedOccupations?.observe(this, Observer { stringOccupations ->
            kotlin.run {
                occupationsAdapter = ArrayAdapter(
                    activity!!,
                    android.R.layout.simple_spinner_dropdown_item,
                    stringOccupations
                )
                spinner_occupations.adapter = occupationsAdapter
            }
        })
    }


    private fun observeSelectedCharacter() {
        newCharacterViewModel.selectedCharacter.observe(this, Observer { domainCharacter ->
            kotlin.run {
                if (domainCharacter != null) {
                    Log.d("DEBUG$TAG", "Selected character is : ${domainCharacter}")
                    var occupation = domainCharacter?.characterOccupation

                }
            }

        })
    }


    /**
     * Observes the selected occupation special.
     */
    private fun observeSelectedOccupationSpecial() {
        Log.d(TAG, "observeSelectedOccupationSpecial()")
        if (this.occupationsViewModel.selectedOccupationSpecial != null) {
            this.occupationsViewModel.selectedOccupationSpecial?.observe(
                this,
                Observer { selectedOccupationSpecial: String ->
                    kotlin.run {
                        fragmentOccupations_tv_occupationSpecialValue.text =
                            selectedOccupationSpecial
                    }
                })
        }
    }
    private fun observeSelectedOccupation() {

        occupationsViewModel.selectedOccupation?.observe(
            this,
            Observer { domainOccupation: DomainOccupation ->
                kotlin.run {
                    Log.d(
                        "DEBUG$TAG",
                        "Selected occupation is : ${domainOccupation.occupationName}"
                    )
                    var index = occupationsViewModel?.displayedOccupations?.value?.indexOfFirst {occupation -> occupation.toString().equals(domainOccupation?.occupationName) }

                    if(index != null){
                        Log.d("DEBUG$TAG", "Index of occupation : $index")
                        spinner_occupations?.setSelection(index)
                    }


                    setOccupationIncome(domainOccupation)
                    setOccupationContacts(domainOccupation)
                    setOccupationSpecial(domainOccupation)

                    Log.d("DEBUG$TAG", "selectedOccupation : $domainOccupation")

                    var character = newCharacterViewModel?.currentCharacter.value

                    character?.characterOccupation = domainOccupation

                    newCharacterViewModel?.currentCharacter.value = character
                }
            })
    }

    private fun setOccupationSpecial(domainOccupation: DomainOccupation) {
        //  Sets the occupation special.
        Log.d(
            "DEBUG$TAG",
            "Selected occupation Special : ${domainOccupation?.occupationSpecial}"
        )
        occupationsViewModel.selectedOccupationSpecial?.value =
            domainOccupation.occupationSpecial
    }

    private fun setOccupationContacts(domainOccupation: DomainOccupation) {
        //  Sets the occupation contacts.
        Log.d(
            "DEBUG$TAG",
            "Selected occupation contacts : ${domainOccupation?.occupationContacts}"
        )
        occupationsViewModel.selectedOccupationContacts?.value =
            domainOccupation.occupationContacts
    }

    private fun setOccupationIncome(domainOccupation: DomainOccupation) {
        //  Sets the occupation income
        Log.d(
            "DEBUG$TAG",
            "Selected occupation income : ${domainOccupation?.occupationIncome}"
        )
        occupationsViewModel.selectedOccupationIncome?.value =
            domainOccupation.occupationIncome
    }


    private fun observeSelectedOccupationContact() {
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

    private fun observeSelectedOccupationIncome() {
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



        loadOccupationsSkillRecyclerView()


        setButtonAddJob()
        startObservation()
        setSpinnerOccupationsOnItemSelectedListener()
        initializeOccupationsAdapter()
        //  setSpinnerOccupationCurrentSelectedOccupation()
    }

    private fun loadOccupationsSkillRecyclerView() {
        if (activity != null) {
            var transaction = childFragmentManager.beginTransaction()
            transaction.replace(
                R.id.container_recyclerView_occupationsSkills,
                OccupationsSkillsRecyclerViewFragment.newInstance(activity!!)
            ).commit()
        }

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


    companion object : CustomCompanion() {
        private const val TAG = "OccupationsFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): OccupationsFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                OccupationsFragment()
            fragment.activity = activity
            val args = Bundle()


            args.putInt(KEY_POSITION, OCCUPATIONS_FRAGMENT_POSITION)
            fragment.arguments = args


            return fragment
        }
    }


}