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
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomFragment
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomOnItemSelectedListener
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_JOBS_NEW_JOB
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.OCCUPATIONS_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.occupation.DomainOccupation
import com.uldskull.rolegameassistant.viewmodels.SkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.character.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationsViewModel
import kotlinx.android.synthetic.main.fragment_occupations.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "JobsFragment" :
 *   Fragment that manages and displays jobs.
 **/
class OccupationsFragment : CustomFragment() {

    //  VIEWMODELS
    /**
     * Occupation viewmodel
     */
    private val occupationsViewModel: OccupationsViewModel by sharedViewModel()

    /**
     * Skill view model
     */
    private val skillViewModel:SkillsViewModel by sharedViewModel()

    /**
     * New character view model
     */
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    //  ADAPTER
    /**
     * Occupations adapter for spinner
     */
    private var occupationsAdapter: ArrayAdapter<String?>? = null

    /**
     * Initialize occupations adapter
     */
    private fun initializeOccupationsAdapter() {
        if (activity != null) {
            occupationsAdapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_spinner_item
            )
        }
    }


    /**
     * Set occupations spinner onItemSelectedListener
     */
    private fun setSpinnerOccupationsOnItemSelectedListener() {
        spinner_occupations.onItemSelectedListener = object : CustomOnItemSelectedListener() {

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
                    occupationsViewModel.selectedOccupation?.value =
                        occupationsViewModel.repositoryOccupations?.value?.get(position)
                }
            }
        }
    }

    /**
     * Start observation
     */
    fun startObservation() {
        Log.d(TAG, "startObservation")
        //  Observe occupation from repository
        observeOccupations()
        //  Observe selected occupation
        observeSelectedOccupation()
        //  Observes selected occupations values.
        observeSelectedOccupationValues()
    }

    /**
     * Observe selected occupations values
     */
    private fun observeSelectedOccupationValues() {
        //  Observe occupation income
        observeSelectedOccupationIncome()
        //  Observe occupation contact
        observeSelectedOccupationContact()
        //  Observes selected occupation special
        observeSelectedOccupationSpecial()
    }

    /**
     * Observe occupations
     */
    private fun observeOccupations() {
        //  Observe displayed string occupations
        observeDisplayedOccupations()
    }

    /**
     * Observe displayed occupation
     */
    private fun observeDisplayedOccupations() {
        occupationsViewModel.displayedOccupations.observe(this, Observer { stringOccupations ->
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

    /**
     * Observe selected occupation
     */
    private fun observeSelectedOccupation() {
        occupationsViewModel.selectedOccupation?.observe(
            this,
            Observer { domainOccupation: DomainOccupation ->
                kotlin.run {

                    val index =
                        occupationsViewModel.displayedOccupations.value?.indexOfFirst { occupation ->
                            occupation.toString() == domainOccupation.occupationName
                        }

                    if (index != null) {
                        Log.d("DEBUG$TAG", "Index of occupation : $index")
                        spinner_occupations?.setSelection(index)
                    }

                    val occupationWithChildren = occupationsViewModel.findOneWithChildren(
                        domainOccupation.occupationId
                    )

                    val oldList = skillViewModel.mutableSkillsToCheck?.value

                    if(oldList != null){
                        for (i in oldList.indices) {
                            oldList[i].skillIsChecked =
                                occupationWithChildren?.skills?.any { occupationSkill -> occupationSkill.skillId!! == oldList[i].skillId!! }!!
                        }
                    }

                    skillViewModel.mutableSkillsToCheck?.value = oldList
                    setOccupationIncome(domainOccupation)
                    setOccupationContacts(domainOccupation)
                    setOccupationSpecial(domainOccupation)

                    Log.d("DEBUG$TAG", "selectedOccupation : $domainOccupation")

                    val character = newCharacterViewModel.currentCharacter

                    character?.characterOccupation = domainOccupation

                    newCharacterViewModel.currentCharacter = character
                }
            })
    }

    /**
     * Set occupation special
     */
    private fun setOccupationSpecial(domainOccupation: DomainOccupation) {
        //  Sets the occupation special.
        Log.d(
            "DEBUG$TAG",
            "Selected occupation Special : ${domainOccupation.occupationSpecial}"
        )
        occupationsViewModel.selectedOccupationSpecial?.value =
            domainOccupation.occupationSpecial
    }

    /**
     * set occupation contacts
     */
    private fun setOccupationContacts(domainOccupation: DomainOccupation) {
        //  Sets the occupation contacts.
        Log.d(
            "DEBUG$TAG",
            "Selected occupation contacts : ${domainOccupation.occupationContacts}"
        )
        occupationsViewModel.selectedOccupationContacts?.value =
            domainOccupation.occupationContacts
    }

    /**
     * Set occupation income
     */
    private fun setOccupationIncome(domainOccupation: DomainOccupation) {
        //  Sets the occupation income
        Log.d(
            "DEBUG$TAG",
            "Selected occupation income : ${domainOccupation.occupationIncome}"
        )
        occupationsViewModel.selectedOccupationIncome?.value =
            domainOccupation.occupationIncome
    }

    /**
     * Observe selected occupation contacts
     */
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

    /**
     * Observe selected occupation income
     */
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

    /**
     * Load occupation skill recycler view
     */
    private fun loadOccupationsSkillRecyclerView() {
        if (activity != null) {
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(
                R.id.container_recyclerView_occupationsSkills,
                OccupationsSkillsRecyclerViewFragment.newInstance(activity!!)
            ).commit()
        }

    }

    /**
     * Empties the text views
     */
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