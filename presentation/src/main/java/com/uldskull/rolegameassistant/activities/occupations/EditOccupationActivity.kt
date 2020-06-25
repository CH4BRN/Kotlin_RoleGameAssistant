// File NewJobActivity.kt
// @Author pierre.antoine - 16/03/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.occupations

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.core.CustomActivity
import com.uldskull.rolegameassistant.activities.core.replaceFragment
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomTextWatcher
import com.uldskull.rolegameassistant.fragments.fragment.bars.NavigationBarFragment
import com.uldskull.rolegameassistant.fragments.fragment.occupations.OccupationsSkillsToCheckSimpleAdapter
import com.uldskull.rolegameassistant.fragments.fragment.occupations.OccupationsToEditAdapter
import com.uldskull.rolegameassistant.models.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.models.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.viewmodels.SkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationsViewModel
import kotlinx.android.synthetic.main.activity_occupations_edit.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewJobActivity" :
 *   Handle occupation creation and edition.
 **/
class EditOccupationActivity : CustomActivity() {
    companion object {
        private const val TAG = "EditOccupationActivity"
    }

    /**
     * Adapter button listener for skills
     */
    private var skillAdapterButtonListener = object :
        CustomAdapterButtonListener<DomainSkillToCheck> {
        /**
         * Called when a recyclerview cell is pressed
         */
        override fun itemPressed(domainModel: DomainSkillToCheck?, position: Int?) {
            TODO("Not yet implemented")
        }

    }

    /**
     * Adapter button listener for occupations
     */
    private var occupationAdapterButtonListener = object :
        CustomAdapterButtonListener<DomainOccupation> {
        /**
         * Called when a recyclerview cell is pressed
         */
        override fun itemPressed(domainOccupation: DomainOccupation?, position: Int?) {
            Log.d("DEBUG$TAG", "DomainOccupation : $domainOccupation")
            if (domainOccupation != null) {
                activityEditOccupation_editText_occupationTitle.setText(domainOccupation.occupationName)
                activityEditOccupation_editText_occupationContacts.setText(domainOccupation.occupationContacts)
                activityEditOccupation_editText_occupationIncome.setText(domainOccupation.occupationIncome)
                activityEditOccupation_editText_occupationSpecial.setText(domainOccupation.occupationSpecial)


                val occupationWithChildren: DomainOccupationWithSkills? =
                    occupationsViewModel.findOneWithChildren(domainOccupation.occupationId)

                val oldList = skillsViewModel.mutableSkillsToCheck?.value

                for (i in oldList?.indices!!) {
                    oldList[i].skillIsChecked =
                        occupationWithChildren?.skills?.any { occupationSkill -> occupationSkill.skillId!! == oldList[i].skillId!! }!!
                }

                occupationsSkillsToEditRecyclerView?.adapter = skillRecyclerViewAdapter

                skillsViewModel.mutableSkillsToCheck?.value = oldList
                occupationsViewModel.currentOccupationToEdit = domainOccupation
            }
        }
    }

    /**
     * occupations view model
     */
    private lateinit var occupationsViewModel: OccupationsViewModel

    /**
     * skills view model
     */
    private lateinit var skillsViewModel: SkillsViewModel

    /**
     * occupation to edit recycler view
     */
    private var occupationsToEditRecyclerView: RecyclerView? = null

    /**
     * occupations skills to edit recycler view
     */
    private var occupationsSkillsToEditRecyclerView: RecyclerView? = null

    /**
     * Add occupation button
     */
    private var addOccupationButton: Button? = null

    /**
     * delete occupation button
     */
    private var deleteOccupationImageButton: ImageButton? = null

    /**
     * save occupation button
     */
    private var saveOccupationImageButton: ImageButton? = null

    /**
     * occupation title edit text
     */
    private var setOccupationTitleEditText: EditText? = null

    /**
     * occupation contact edit text
     */
    private var setOccupationContactsEditText: EditText? = null

    /**
     * occupation income edit text
     */
    private var setOccupationIncomeEditText: EditText? = null

    /**
     * occupation special edit text
     */
    private var setOccupationSpecialEditText: EditText? = null

    /**
     * initialize delete occupation button
     */
    private fun initializeDeleteOccupationButton() {
        deleteOccupationImageButton =
            this.findViewById(R.id.activityEditOccupation_imageButton_deleteOccupation)
        if (deleteOccupationImageButton == null) {
            throw Exception("Button is null.")
        }

        deleteOccupationImageButton!!.setOnClickListener {
            val currentOccupation = occupationsViewModel.currentOccupationToEdit
            if (currentOccupation != null) {
                val deleteResult = occupationsViewModel.deleteOccupation(currentOccupation)
                Log.d("DEBUG$TAG", "Delete result : $deleteResult")
            }

            occupationsViewModel.refreshDataFromRepository()
        }
    }

    /**
     * initialize add occupation button
     */
    private fun initializeAddOccupationButton() {
        addOccupationButton =
            this.findViewById(R.id.activityEditOccupation_button_addNewOccupation)
        if (addOccupationButton == null) {
            throw Exception("Button is null.")
        }
        addOccupationButton?.setOnClickListener {
            val id = occupationsViewModel.insertOccupation(
                DomainOccupation(
                    occupationId = null,
                    occupationName = "Fill the name",
                    occupationContacts = "Fill the contacts",
                    occupationIncome = "Fill the income",
                    occupationSpecial = "Fill the special"
                )
            )
            occupationsViewModel.refreshDataFromRepository()

            val occupation = occupationsViewModel.getOccupationById(id)
            occupationAdapterButtonListener.itemPressed(occupation)


        }
    }

    /**
     * Activity lifecycle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_occupations_edit)
        this.loadNavigationBarFragment()

        occupationsViewModel = getViewModel()
        skillsViewModel = getViewModel()
        occupationsToEditRecyclerView =
            this.findViewById(R.id.activityEditOccupation_recyclerView_displayedOccupations)
        if (occupationsToEditRecyclerView == null) {
            throw Exception("Recycler view is null")
        }

        occupationsSkillsToEditRecyclerView =
            this.findViewById(R.id.activityEditOccupation_recyclerView_occupationSkills)
        if (occupationsSkillsToEditRecyclerView == null) {
            throw Exception("Recycler view is null")
        }
        initializeSetOccupationTitleEditText()
        initializeAddOccupationButton()
        initializeDeleteOccupationButton()


        startObservation()
    }


    /**
     * initialize set occupation title edit text
     */
    private fun initializeSetOccupationTitleEditText() {
        setOccupationTitleEditText =
            this.findViewById(R.id.activityEditOccupation_editText_occupationTitle)
        setOccupationTitleEditText?.addTextChangedListener(object : CustomTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                if (s != null) {
                    val oldOccupation = occupationsViewModel.currentOccupationToEdit
                    val newOccupation = DomainOccupation(
                        occupationName = s.toString(),
                        occupationSpecial = oldOccupation?.occupationSpecial,
                        occupationIncome = oldOccupation?.occupationIncome,
                        occupationContacts = oldOccupation?.occupationContacts,
                        occupationId = oldOccupation?.occupationId
                    )

                    occupationsViewModel.currentOccupationToEdit = newOccupation
                }
            }
        })
    }

    /**
     * start observation
     */
    private fun startObservation() {
        observeRepositoryOccupations()
        observeRepositorySkills()
        observeMutableSkillsToCheck()
    }

    /**
     * Skills recycler view adapter
     */
    var skillRecyclerViewAdapter: OccupationsSkillsToCheckSimpleAdapter? = null

    /**
     * observe mutable skills to check
     */
    private fun observeMutableSkillsToCheck() {
        skillsViewModel.mutableSkillsToCheck?.observe(
            this, Observer {
                skillRecyclerViewAdapter = OccupationsSkillsToCheckSimpleAdapter(
                    context = this,
                    buttonListenerCustom = this.skillAdapterButtonListener
                )

                skillRecyclerViewAdapter?.setSkills(it)
                occupationsSkillsToEditRecyclerView?.adapter = skillRecyclerViewAdapter
                val layoutManager = LinearLayoutManager(
                    this,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                occupationsSkillsToEditRecyclerView?.layoutManager = layoutManager
            }
        )
    }

    /**
     * Observe repository skills
     */
    private fun observeRepositorySkills() {
        skillsViewModel.repositorySkillsToCheck?.observe(this, Observer {
            skillsViewModel.mutableSkillsToCheck?.value = it
        })
    }

    /**
     * observe repository occupations
     */
    private fun observeRepositoryOccupations() {
        occupationsViewModel.repositoryOccupations?.observe(this, Observer {
            val recyclerViewAdapter =
                OccupationsToEditAdapter(this, this.occupationAdapterButtonListener)
            Log.d("DEBUG$TAG", "Occupations : $it")
            recyclerViewAdapter.setOccupations(it)
            Log.d("DEBUG$TAG", "Adapter size : ${recyclerViewAdapter.itemCount}")
            occupationsToEditRecyclerView?.adapter = recyclerViewAdapter

            Log.d("DEBUG$TAG", "Recyclerview adapter : ${occupationsToEditRecyclerView?.adapter}")
            val layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
            occupationsToEditRecyclerView?.layoutManager = layoutManager
        })
    }


    /**
     * Load the navigation bar fragment.
     */
    private fun loadNavigationBarFragment() {
        Log.d(TAG, "loadNavigationBarFragment")

        this.replaceFragment(
            R.id.activityEditOccupation_layout_navigationBar,
            NavigationBarFragment.newInstance(this)
        )
    }


}