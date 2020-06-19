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
import com.uldskull.rolegameassistant.activities.CustomActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomTextWatcher
import com.uldskull.rolegameassistant.fragments.fragment.bars.NavigationBarFragment
import com.uldskull.rolegameassistant.fragments.fragment.occupations.OccupationsSkillsDescriptionAdapter
import com.uldskull.rolegameassistant.fragments.fragment.occupations.OccupationsSkillsToCheckSimpleAdapter
import com.uldskull.rolegameassistant.fragments.fragment.occupations.OccupationsToEditAdapter
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.viewmodels.SkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationsViewModel
import kotlinx.android.synthetic.main.activity_occupations_edit.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewJobActivity" :
 *   Handle job creation.
 **/
class EditOccupationActivity : CustomActivity() {
    companion object {
        private const val TAG = "EditOccupationActivity"
    }

    var skillAdapterButtonListener = object : AdapterButtonListener<DomainSkillToCheck> {
        /**
         * Called when a recyclerview cell is pressed
         */
        override fun itemPressed(domainModel: DomainSkillToCheck?, position: Int?) {
            TODO("Not yet implemented")
        }

    }

    var occupationAdapterButtonListener = object : AdapterButtonListener<DomainOccupation> {
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

                occupationsViewModel?.currentOccupationToEdit = domainOccupation
            }
        }

    }

    private lateinit var occupationsViewModel: OccupationsViewModel
    private lateinit var skillsViewModel: SkillsViewModel
    private var occupationsToEditRecyclerView: RecyclerView? = null
    private var occupationsSkillsToEditRecyclerView: RecyclerView? = null
    private var addOccupationButton: Button? = null
    private var deleteOccupationImageButton: ImageButton? = null
    private var saveOccupationImageButton: ImageButton? = null
    private var setOccupationTitleEditText: EditText? = null
    private var setOccupationContactsEditText: EditText? = null
    private var setOccupationIncomeEditText: EditText? = null
    private var setOccupationSpecialEditText: EditText? = null
    private fun initializeDeleteOccupationButton() {
        deleteOccupationImageButton =
            this?.findViewById(R.id.activityEditOccupation_imageButton_deleteOccupation)
        if (deleteOccupationImageButton == null) {
            throw Exception("Button is null.")
        }

        deleteOccupationImageButton!!.setOnClickListener {
            var currentOccupation = occupationsViewModel?.currentOccupationToEdit
            if (currentOccupation != null) {
               var deleteResult =  occupationsViewModel?.deleteOccupation(currentOccupation)
                Log.d("DEBUG$TAG", "Delete result : $deleteResult")
            }

            occupationsViewModel?.refreshDataFromRepository()
        }
    }

    private fun initializeAddOccupationButton() {
        addOccupationButton =
            this?.findViewById(R.id.activityEditOccupation_button_addNewOccupation)
        if (addOccupationButton == null) {
            throw Exception("Button is null.")
        }
        addOccupationButton?.setOnClickListener {
            var id = occupationsViewModel?.insertOccupation(
                DomainOccupation(
                    occupationId = null,
                    occupationName = "Fill the name",
                    occupationContacts = "Fill the contacts",
                    occupationIncome = "Fill the income",
                    occupationSpecial = "Fill the special"
                )
            )
            occupationsViewModel?.refreshDataFromRepository()

            var occupation = occupationsViewModel?.getOccupationById(id)
            occupationAdapterButtonListener.itemPressed(occupation)


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_occupations_edit)
        this.loadNavigationBarFragment()

        occupationsViewModel = getViewModel()
        skillsViewModel = getViewModel()
        if (occupationsViewModel == null) {
            throw Exception("View model is null")
        }
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


    private fun initializeSetOccupationTitleEditText() {
        setOccupationTitleEditText =
            this?.findViewById(R.id.activityEditOccupation_editText_occupationTitle)
        setOccupationTitleEditText?.addTextChangedListener(object : CustomTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                if (s != null) {
                    var oldOccupation = occupationsViewModel?.currentOccupationToEdit
                    var newOccupation = DomainOccupation(
                        occupationName = s.toString(),
                        occupationSpecial = oldOccupation?.occupationSpecial,
                        occupationIncome = oldOccupation?.occupationIncome,
                        occupationContacts = oldOccupation?.occupationContacts,
                        occupationId = oldOccupation?.occupationId
                    )

                    occupationsViewModel?.currentOccupationToEdit = newOccupation
                }
            }
        })
    }

    private fun startObservation() {
        observeRepositoryOccupations()
        observeRepositorySkills()
    }

    private fun observeRepositorySkills() {

        skillsViewModel?.repositorySkillsToCheck?.observe(this, Observer {
            var recyclerViewAdapter = OccupationsSkillsToCheckSimpleAdapter(
                context = this,
                buttonListener = this.skillAdapterButtonListener
            )
            recyclerViewAdapter?.setSkills(it)

            occupationsSkillsToEditRecyclerView?.adapter = recyclerViewAdapter
            var layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
            occupationsSkillsToEditRecyclerView?.layoutManager = layoutManager

        })
    }

    private fun observeRepositoryOccupations() {
        occupationsViewModel?.repositoryOccupations?.observe(this, Observer {
            var recyclerViewAdapter =
                OccupationsToEditAdapter(this, this.occupationAdapterButtonListener)
            Log.d("DEBUG$TAG", "Occupations : $it")
            recyclerViewAdapter?.setOccupations(it)
            Log.d("DEBUG$TAG", "Adapter size : ${recyclerViewAdapter?.itemCount}")
            occupationsToEditRecyclerView?.adapter = recyclerViewAdapter

            Log.d("DEBUG$TAG", "Recyclerview adapter : ${occupationsToEditRecyclerView?.adapter}")
            var layoutManager = LinearLayoutManager(
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