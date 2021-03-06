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
import com.uldskull.rolegameassistant.activities.NewSkillActivity
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.OCCUPATION_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.skill.DomainFilledSkill
import com.uldskull.rolegameassistant.models.character.skill.DomainOccupationSkill
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.OccupationSkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.OccupationViewModel
import com.uldskull.rolegameassistant.viewmodels.OccupationsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "OccupationFragment" :
 *   "Occupation" fragment to set occupation's skills values.
 **/
class OccupationFragment() : CustomFragment() {

    /**
     * TextView that shows occupation's skills score to spend.
     */
    private var textViewTotalOccupationPoints: TextView? = null
    /**
     * Units to add to a skill
     */
    private var unitsValue: Int? = null
    /**
     * Tens to add to a skill
     */
    private var tensValue: Int? = null
    /**
     * Value list for spinners
     */
    var valuesList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    /**
     * Occupations view model.
     */
    private val occupationsViewModel: OccupationsViewModel by sharedViewModel()
    /**
     * Occupation view model
     */
    private val occupationViewModel: OccupationViewModel by sharedViewModel()
    /**
     * Occupation skills view model.
     */
    private val occupationSkillsViewModel: OccupationSkillsViewModel by sharedViewModel()
    /**
     * Characteristic view model.
     */
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()
    /**
     * Selected skill text view.
     */
    private var textViewSelectedSkill: TextView? = null
    /**
     * Tens value spinner
     */
    private var spinnerTensValue: Spinner? = null
    /**
     * Tens value text view
     */
    private var textViewOccupationPointsValue: TextView? = null
    /**
     * Units value spinner
     */
    private var spinnerUnitsValue: Spinner? = null
    /**
     * Units value text view
     */
    private var textViewUnitsValue: TextView? = null
    /**
     * Tens value adapter
     */
    private var tensAdapter: ArrayAdapter<Int>? = null
    /**
     * Units value adapter
     */
    private var unitAdapter: ArrayAdapter<Int>? = null
    /**
     * button that validates the addition of value to a skill
     */
    private var buttonValidateAddValueToSkill: ImageButton? = null
    /**
     * Button to add a skill
     */
    private var buttonAddSkill: ImageButton? = null

    /**
     * Initializes the view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        //  inflate the corresponding fragment.
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_occupation, container, false
        )
        return initialRootView
    }

    /**
     * Fragment life-cycle.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return initializeView(inflater, container)
    }

    /**
     * Sets the spinners adapters
     */
    private fun setSpinnerAdapter() {
        Log.d(TAG, "setSpinnerAdapter")
        setTensSpinnerAdapter()
        setUnitsSpinnerAdapter()
    }

    /**
     * Sets units spinner adapter.
     */
    private fun setUnitsSpinnerAdapter() {
        //  Initialize the units adapter
        initializeUnitsAdapter()
        //  Set the units spinner adapter.
        spinnerUnitsValue?.adapter = unitAdapter
    }

    /**
     * Sets tens spinner adapter.
     */
    private fun setTensSpinnerAdapter() {
        //  Initialize the tens adapter
        initializeTensAdapter()
        //  Set the tens spinner adapter.
        spinnerTensValue?.adapter = tensAdapter
    }

    /**
     * Initializes the units adapter.
     */
    private fun initializeUnitsAdapter() {
        if (activity != null) {
            //  Instantiate the adapter with the values.
            unitAdapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_spinner_item,
                valuesList
            )
            //  Check if the spinner was correctly initialized.
            if (unitAdapter == null) {
                throw Exception("spinner is null")
            }
        }

    }

    /**
     * Initializes the tens adapter.
     */
    private fun initializeTensAdapter() {
        if (activity != null) {
            //  Instantiate the adapter with the values.
            tensAdapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_spinner_item,
                valuesList
            )
            //  Check if the spinner was correctly initialized.
            if (tensAdapter == null) {
                throw Exception("spinner is null")
            }
        }

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
        observeCurrentOccupationSkill()
        setListeners()
        setSpinnerAdapter()
        disableTheValidateButton()
        setOccupationSkillsPointsTextView()
        observeOccupationTotalPointsToSpend()
        observeOccupationSkillsPointsToSpend()
        observeOccupationPointsValue()
        loadOccupationSkillsRecyclerView()
    }

    private fun loadOccupationSkillsRecyclerView() {
        if (activity != null) {
            var transaction = childFragmentManager.beginTransaction()
            transaction.replace(
                R.id.container_occupationSkills,
                OccupationSkillsRecyclerViewFragment.newInstance(activity!!)
            ).commit()
        }

    }


    private fun observeOccupationPointsValue() {
        occupationViewModel?.observableOccupationPointsValue.observe(this, Observer { tens ->
            if (tens == null) {
                //  Do nothing
            } else {
                textViewOccupationPointsValue?.text = tens.toString()
            }
        })
    }

    private fun observeOccupationSkillsPointsToSpend() {
        occupationSkillsViewModel?.occupationSkillsPointsToSpend.observe(this, Observer { score ->
            kotlin.run {
                if (score != null) {
                    occupationViewModel?.observableOccupationPointsValue.value = score
                    occupationViewModel?.totalOccupationsPointsFixedValue = score
                }
            }
        })
    }

    private fun observeOccupationTotalPointsToSpend() {
        occupationSkillsViewModel?.occupationSkillsTotalPointsToSpend.observe(
            this,
            Observer { score ->
                kotlin.run {
                    textViewTotalOccupationPoints?.text = score.toString()
                }
            })
    }

    /**
     * Set the occupation skills points to spend tew view.
     */
    private fun setOccupationSkillsPointsTextView() {

        var occupationScore = characteristicsViewModel?.getOccupationSkillsScore()
        occupationSkillsViewModel?.occupationSkillsTotalPointsToSpend.value = occupationScore
        occupationSkillsViewModel?.occupationSkillsPointsToSpend.value = occupationScore
    }

    /**
     * Sets the widgets listeners
     */
    private fun setListeners() {
        setButtonsListeners()
        setSpinnerListeners()
    }

    /**
     * Disables the validate button.
     */
    private fun disableTheValidateButton() {
        buttonValidateAddValueToSkill?.isEnabled = false
    }

    /**
     * Sets the spinners listeners.
     */
    private fun setSpinnerListeners() {
        setTensSpinnerOnItemSelectedListener()
        setUnitsSpinnerOnItemSelectedListener()
    }

    /**
     * Sets buttons listeners.
     */
    private fun setButtonsListeners() {
        setButtonAddSkillOnClickListener()
        setButtonValidateOnClickListener()
    }

    /**
     * Deserialize widgets
     */
    private fun deserializeWidgets(view: View) {
        Log.d(TAG, "deserializeWidgets")
        try {
            buttonAddSkill = view.findViewById<ImageButton>(R.id.btn_occupation_add_skill)
            buttonValidateAddValueToSkill = view.findViewById(R.id.btn_validateSkillPoints)
            textViewSelectedSkill = view.findViewById(R.id.tv_selectedSkill)
            spinnerTensValue = view.findViewById(R.id.spinner_skill_tens)
            spinnerUnitsValue = view.findViewById(R.id.spinner_skill_units)
            textViewTotalOccupationPoints = view.findViewById(R.id.tv_totalOccupationPoints)
            textViewOccupationPointsValue = view.findViewById(R.id.tv_occupationPoints)
        } catch (e: Exception) {
            Log.e(TAG, "deserialization failed")
            e.printStackTrace()
            throw e
        }
    }

    /**
     * Sets the units's spinner selection listener.
     */
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
                if (unitsValue != null) {
                    if (tensValue != null) {
                        occupationViewModel?.observableOccupationPointsValue.value =
                            occupationViewModel?.totalOccupationsPointsFixedValue.minus(unitsValue!! + (tensValue!! * 10))
                    } else {
                        occupationViewModel?.observableOccupationPointsValue.value =
                            occupationViewModel?.totalOccupationsPointsFixedValue.minus(unitsValue!!)
                    }
                }
            }
        }
    }

    /**
     * Sets the tens's spinner selection listener
     */
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

                if (tensValue != null) {
                    if (unitsValue != null) {
                        occupationViewModel?.observableOccupationPointsValue.value =
                            occupationViewModel?.totalOccupationsPointsFixedValue.minus(unitsValue!! + (tensValue!! * 10))
                    } else {
                        occupationViewModel?.observableOccupationPointsValue.value =
                            occupationViewModel?.totalOccupationsPointsFixedValue.minus(tensValue!!)
                    }
                }
            }
        }
    }

    /**
     * Sets the validate button's click listener.
     */
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
            filledSkills?.forEach { skill ->
                kotlin.run {
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

    /**
     * Observes the current occupation_skill.
     */
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

    /**
     * Sets the add skill button's click listener.
     */
    private fun setButtonAddSkillOnClickListener() {
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


            var finalList = mutableListOf<DomainFilledSkill>()
            var checked = occupationSkillsViewModel.checkedOccupationSkills.value
            if (checked == null) {
                occupationSkillsViewModel.checkedOccupationSkills.value = list
            } else {
                list.forEach { newSkill ->
                    kotlin.run {
                        checked.forEach { oldSKill ->
                            kotlin.run {
                                if (newSkill.skillId == oldSKill.skillId) {
                                    finalList.add(oldSKill)
                                }
                            }
                        }
                        if (!finalList.any { s -> s.skillId == newSkill.skillId }) {
                            finalList.add(newSkill)
                        }

                    }
                }
                occupationSkillsViewModel.checkedOccupationSkills.value = finalList
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
                OccupationFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, OCCUPATION_FRAGMENT_POSITION)
            fragment.arguments = args


            return fragment
        }
    }
}