// File JobFragment.kt
// @Author pierre.antoine - 05/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.occupation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.NewSkillActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.OCCUPATION_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.skill.DomainFilledSkill
import com.uldskull.rolegameassistant.viewmodels.OccupationSkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.OccupationsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "JobFragment" :
 *   TODO: Fill class use.
 **/
class OccupationFragment(activity: Activity) : CustomFragment(activity) {

    private var unitsValue: Int = 0
    private var tensValue: Int = 0
    var valuesList = listOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    val occupationsViewModel: OccupationsViewModel by sharedViewModel()
    val occupationSkillsViewModel: OccupationSkillsViewModel by sharedViewModel()
    var textViewSelectedSkill: TextView? = null
    var spinnerTensValue: Spinner? = null
    var spinnerUnitsValue: Spinner? = null
    var tensAdapter: ArrayAdapter<Int>? = null
    var unitAdapter: ArrayAdapter<Int>? = null
    var buttonValidateAddValueToSkill: ImageButton? = null


    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_occupation, container, false
        )
        return initialRootView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    fun setSpinnerAdapter() {
        Log.d(TAG, "setSpinnerAdapter")
        tensAdapter = ArrayAdapter(
            activity,
            android.R.layout.simple_spinner_item,
            valuesList
        )
        Log.d(TAG, "tens adapter is null : ${tensAdapter == null}")
        Log.d(TAG, "tens adapter item count : ${tensAdapter?.count}")
        spinnerTensValue?.adapter = tensAdapter
        unitAdapter = ArrayAdapter(
            activity,
            android.R.layout.simple_spinner_item,
            valuesList
        )
        Log.d(TAG, "tens spinner is null : ${spinnerTensValue == null}")
        Log.d(TAG, "tens spinner count : ${spinnerTensValue?.adapter?.count}")
        spinnerUnitsValue?.adapter = unitAdapter
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
        super.onViewCreated(view, savedInstanceState)
        val btnAddSkill = view.findViewById<ImageButton>(R.id.btn_job_add_skill)

        setButtonAddSkillOnClickListener(btnAddSkill)

        observeCurrentOccupationSkill()
        buttonValidateAddValueToSkill = view.findViewById(R.id.btn_validateSkillPoints)

        setButtonValidateOnClickListener()

        textViewSelectedSkill = view.findViewById(R.id.tv_selectedSkill)
        spinnerTensValue = view.findViewById(R.id.spinner_skill_tens)
        spinnerUnitsValue = view.findViewById(R.id.spinner_skill_units)
        setSpinnerAdapter()
        setTensSpinnerOnItemSelectedListener()
        setUnitsSpinnerOnItemSelectedListener()


    }

    private fun setUnitsSpinnerOnItemSelectedListener() {
        spinnerUnitsValue?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            /**
             * Callback method to be invoked when the selection disappears from this
             * view. The selection can disappear for instance when touch is activated
             * or when the adapter becomes empty.
             *
             * @param parent The AdapterView that now contains no selected item.
             */
            override fun onNothingSelected(parent: AdapterView<*>?) {

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
                unitsValue = valuesList.get(position)
            }

        }
    }

    private fun setTensSpinnerOnItemSelectedListener() {
        spinnerTensValue?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            /**
             * Callback method to be invoked when the selection disappears from this
             * view. The selection can disappear for instance when touch is activated
             * or when the adapter becomes empty.
             *
             * @param parent The AdapterView that now contains no selected item.
             */
            override fun onNothingSelected(parent: AdapterView<*>?) {

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
                tensValue = valuesList.get(position)
            }

        }
    }


    private fun setButtonValidateOnClickListener() {
        buttonValidateAddValueToSkill?.setOnClickListener() {
            Log.d(TAG, "Click ${occupationSkillsViewModel.currentOccupationSkill.value}")
            var domainFilledSkill = occupationSkillsViewModel.currentOccupationSkill.value
            Log.d(
                TAG,
                "currentOccupationSkill : ${occupationSkillsViewModel.currentOccupationSkill.value}"
            )
            if (tensValue != null) {
                domainFilledSkill?.filledSkillTensValue = tensValue
            }
            if (unitsValue != null) {
                domainFilledSkill?.filledSkillUnitsValue = unitsValue
            }

            occupationSkillsViewModel.currentOccupationSkill.value = domainFilledSkill

            var index =
                occupationSkillsViewModel.checkedOccupationSkills?.value?.indexOfFirst { skill ->
                    skill.skillId == domainFilledSkill?.skillId
                }

            Log.d(TAG, "index : $index")

            var filledSkills =
                occupationSkillsViewModel.checkedOccupationSkills?.value?.toMutableList()
            Log.d(TAG, "filledSkills size : ${filledSkills?.size}")
            if (filledSkills != null && index != null) {
                Log.d(TAG, "filledSkill at index $index : ${filledSkills[index]}")

                if (domainFilledSkill != null) {
                    filledSkills?.set(index, domainFilledSkill)
                }
                Log.d(TAG, "filledSkill at index $index : ${filledSkills[index]}")
            }
            occupationSkillsViewModel.checkedOccupationSkills?.value = filledSkills


        }
    }

    private fun observeCurrentOccupationSkill() {
        this.occupationSkillsViewModel?.currentOccupationSkill?.observe(
            this,
            Observer { domainFilledSkill: DomainFilledSkill ->
                kotlin.run {
                    Log.d(TAG, "observed : ${domainFilledSkill}")
                    textViewSelectedSkill?.setText(domainFilledSkill.skillName)
                    var tensIndex =
                        valuesList.findLast { v -> v.equals(domainFilledSkill.filledSkillTensValue) }
                    if (tensIndex != null) {
                        spinnerTensValue?.setSelection(tensIndex)
                    }

                }
            })
    }

    private fun setButtonAddSkillOnClickListener(btnAddSkill: ImageButton) {
        btnAddSkill.setOnClickListener {
            val intent = Intent(activity, NewSkillActivity::class.java)
            startActivity(intent)

        }
    }

    /**
     * Called when the focus return on this view
     */
    override fun onResume() {
        super.onResume()
        Log.d("JobFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = OCCUPATION_FRAGMENT_POSITION
        Log.d("JobFragment_2", NewCharacterActivity.progression.value.toString())
        var skills = occupationsViewModel?.observedOccupationsSkills?.value
        if (skills != null) {
            var checkedSkills = skills!!.filter { s ->
                s.skillIsChecked
            }
            Log.d(TAG, "checked skills size : ${checkedSkills.size}")
            var list: MutableList<DomainFilledSkill> = mutableListOf()
            checkedSkills?.forEach() { occupationSkill ->
                kotlin.run {
                    list.add(
                        DomainFilledSkill(
                            filledSkillId = occupationSkill?.skillId,
                            filledSkillTensValue = 0,
                            filledSkillUnitsValue = 0,
                            filledSkillName = occupationSkill?.skillName,
                            filledSkillTotal = 0,
                            filledSkillBase = occupationSkill?.skillBase,
                            filledSkillMax = occupationSkill?.skillMax
                        )
                    )
                }
            }
            occupationSkillsViewModel.checkedOccupationSkills.value = list
            Log.d(
                TAG,
                "checkedOccupationSkills size : ${occupationSkillsViewModel.checkedOccupationSkills.value?.size}"
            )
        }
    }


    companion object : CustomCompanion() {

        private const val TAG = "OccupationFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): OccupationFragment {
            val fragment =
                OccupationFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, OCCUPATION_FRAGMENT_POSITION)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_jobSkills,
                OccupationSkillsRecyclerViewFragment.newInstance(
                    activity
                )
            )

            return fragment
        }
    }
}