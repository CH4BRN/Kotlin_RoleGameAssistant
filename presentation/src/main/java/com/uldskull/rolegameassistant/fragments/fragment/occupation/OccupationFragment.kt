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
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.OccupationSkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.OccupationsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "JobFragment" :
 *   TODO: Fill class use.
 **/
class OccupationFragment(activity: Activity) : CustomFragment(activity) {

    private var unitsValue: Int? = null
    private var tensValue: Int? = null
    var valuesList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    private val occupationsViewModel: OccupationsViewModel by sharedViewModel()
    private val occupationSkillsViewModel: OccupationSkillsViewModel by sharedViewModel()
    val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()
    var textViewSelectedSkill: TextView? = null
    var spinnerTensValue: Spinner? = null
    var spinnerUnitsValue: Spinner? = null
    var tensAdapter: ArrayAdapter<Int>? = null
    var unitAdapter: ArrayAdapter<Int>? = null
    var buttonValidateAddValueToSkill: ImageButton? = null
    var buttonAddSkill: ImageButton? = null


    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
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
        Log.d(TAG, "onCreateView")
        return initializeView(inflater, container)
    }

    private fun setSpinnerAdapter() {
        Log.d(TAG, "setSpinnerAdapter")
        setTensAdapter()
        spinnerTensValue?.adapter = tensAdapter
        setUnitsAdapter()
        spinnerUnitsValue?.adapter = unitAdapter
    }

    private fun setUnitsAdapter() {
        unitAdapter = ArrayAdapter(
            activity,
            android.R.layout.simple_spinner_item,
            valuesList
        )
        Log.d(TAG, "tens spinner is null : ${spinnerTensValue == null}")
        Log.d(TAG, "tens spinner count : ${spinnerTensValue?.adapter?.count}")
    }

    private fun setTensAdapter() {
        tensAdapter = ArrayAdapter(
            activity,
            android.R.layout.simple_spinner_item,
            valuesList
        )
        Log.d(TAG, "tens adapter is null : ${tensAdapter == null}")
        Log.d(TAG, "tens adapter item count : ${tensAdapter?.count}")
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
        deserializeWidgets(view)
        setButtonAddSkillOnClickListener(buttonAddSkill)

        observeCurrentOccupationSkill()

        setButtonValidateOnClickListener()
        setSpinnerAdapter()
        setTensSpinnerOnItemSelectedListener()
        setUnitsSpinnerOnItemSelectedListener()
        buttonValidateAddValueToSkill?.isEnabled = false


    }

    private fun deserializeWidgets(view: View) {
        buttonAddSkill = view.findViewById<ImageButton>(R.id.btn_occupation_add_skill)
        buttonValidateAddValueToSkill = view.findViewById(R.id.btn_validateSkillPoints)
        textViewSelectedSkill = view.findViewById(R.id.tv_selectedSkill)
        spinnerTensValue = view.findViewById(R.id.spinner_skill_tens)
        spinnerUnitsValue = view.findViewById(R.id.spinner_skill_units)
    }

    private fun setUnitsSpinnerOnItemSelectedListener() {
        Log.d(TAG, "setUnitsSpinnerOnItemSelectedListener")
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
                unitsValue = this@OccupationFragment.valuesList[position]
            }

        }
    }

    private fun setTensSpinnerOnItemSelectedListener() {
        Log.d(TAG, "setTensSpinnerOnItemSelectedListener")
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
                tensValue = valuesList[position]
            }

        }
    }


    private fun setButtonValidateOnClickListener() {
        Log.d(TAG + "valid", "setButtonValidateOnClickListener")
        buttonValidateAddValueToSkill?.setOnClickListener {
            Log.d(TAG + "valid", "Click ${occupationSkillsViewModel.currentOccupationSkill.value}")
            var domainFilledSkill = occupationSkillsViewModel.currentOccupationSkill.value
            Log.d(
                TAG + "valid",
                "currentOccupationSkill : ${occupationSkillsViewModel.currentOccupationSkill.value}"
            )
            if (tensValue != null) {
                Log.d(TAG + "valid", "tens value : $tensValue")
                domainFilledSkill?.filledSkillTensValue = tensValue
            }
            if (unitsValue != null) {
                Log.d(TAG + "valid", "units value : $unitsValue")
                domainFilledSkill?.filledSkillUnitsValue = unitsValue
            }

            Log.d(TAG + "valid", "domainFilledSkill : $domainFilledSkill")
            occupationSkillsViewModel.currentOccupationSkill.value = domainFilledSkill
            Log.d(
                TAG + "valid",
                "currentOccupationSkill : ${occupationSkillsViewModel.currentOccupationSkill.value} "
            )

            var index =
                occupationSkillsViewModel.checkedOccupationSkills.value?.indexOfFirst { skill ->
                    skill.skillId == domainFilledSkill?.skillId
                }

            Log.d(TAG + "valid", "index : $index")

            var filledSkills =
                occupationSkillsViewModel.checkedOccupationSkills.value?.toMutableList()
            Log.d(TAG + "valid", "filledSkills size : ${filledSkills?.size}")
            filledSkills?.forEach {
                skill -> kotlin.run {
                Log.d(TAG, "skill : $skill")
            }
            }
            if (filledSkills != null && index != null) {
                Log.d(TAG + "valid", "filledSkill at index $index : ${filledSkills[index]}")

                if (domainFilledSkill != null) {
                    filledSkills[index] = domainFilledSkill
                }
                Log.d(TAG + "valid", "filledSkill at index $index : ${filledSkills[index]}")
            }
            occupationSkillsViewModel.checkedOccupationSkills.value = filledSkills


        }
    }

    private fun observeCurrentOccupationSkill() {
        Log.d(TAG, "observeCurrentOccupationSkill")
        this.occupationSkillsViewModel.currentOccupationSkill.observe(
            this,
            Observer { domainFilledSkill: DomainFilledSkill ->
                kotlin.run {
                    if (domainFilledSkill == null) {
                        spinnerUnitsValue?.isEnabled = false
                        spinnerTensValue?.isEnabled = false
                        buttonValidateAddValueToSkill?.isEnabled = false
                    } else {
                        spinnerUnitsValue?.isEnabled = true
                        spinnerTensValue?.isEnabled = true
                        buttonValidateAddValueToSkill?.isEnabled = true
                    }
                    Log.d(TAG, "observed : ${domainFilledSkill}")
                    textViewSelectedSkill?.text = domainFilledSkill.skillName
                    var tensIndex =
                        valuesList.findLast { v -> v == domainFilledSkill.filledSkillTensValue }
                    Log.d(TAG + "valid", "tensIndex : $tensIndex")
                    if (tensIndex != null) {
                        spinnerTensValue?.setSelection(tensIndex)
                    }
                    var unitsIndex =
                        valuesList.findLast { value: Int -> value == domainFilledSkill.filledSkillUnitsValue }
                    Log.d(TAG + "valid", "unitsIndex : $unitsIndex")
                    if (unitsIndex != null) {
                        spinnerUnitsValue?.setSelection(unitsIndex)
                    }
                }
            })
    }


    private fun setButtonAddSkillOnClickListener(btnAddSkill: ImageButton?) {
        Log.d(TAG, "setButtonAddSkillOnClickListener")
        buttonAddSkill?.setOnClickListener {
            val intent = Intent(activity, NewSkillActivity::class.java)
            startActivity(intent)

        }
    }

    /**
     * Called when the focus return on this view
     */
    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
        Log.d("JobFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = OCCUPATION_FRAGMENT_POSITION
        Log.d("JobFragment_2", NewCharacterActivity.progression.value.toString())
        var skills: List<DomainOccupationSkill>? =
            occupationsViewModel.observedOccupationsSkills?.value
        if (skills != null) {
            var checkedSkills: List<DomainOccupationSkill> =
                skills.filter { skill: DomainOccupationSkill ->
                    skill.skillIsChecked
                }
            Log.d(TAG, "checked skills size : ${checkedSkills.size}")
            var list: MutableList<DomainFilledSkill> = mutableListOf()
            checkedSkills.forEach { occupationSkill: DomainOccupationSkill ->
                kotlin.run {
                    list.add(
                        DomainFilledSkill(
                            filledSkillId = occupationSkill.skillId,
                            filledSkillTensValue = 0,
                            filledSkillUnitsValue = 0,
                            filledSkillName = occupationSkill.skillName,
                            filledSkillTotal = 0,
                            filledSkillBase = occupationSkill.skillBase,
                            filledSkillMax = occupationSkill.skillMax
                        )
                    )
                }
            }
            if (list.size != occupationSkillsViewModel.checkedOccupationSkills.value?.size) {
                occupationSkillsViewModel.checkedOccupationSkills.value = list
            }

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
                R.id.container_occupationSkills,
                OccupationSkillsRecyclerViewFragment.newInstance(
                    activity
                )
            )

            return fragment
        }
    }
}