// File HobbyFragment.kt
// @Author pierre.antoine - 09/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.hobby

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.skills.EditSkillActivity
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomFragment
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomOnItemSelectedListener
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.HOBBY_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.viewmodels.hobbies.HobbySkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.PointsToSpendViewModel
import com.uldskull.rolegameassistant.viewmodels.SkillsViewModel
import kotlinx.android.synthetic.main.fragment_hobby.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "HobbyFragment" :
 *   Holds hobby fragment logic
 **/
class HobbyFragment : CustomFragment() {

    /**
     * Tens value
     */
    private var tensValue: Int? = null

    /**
     * Units value
     */
    private var unitsValue: Int? = null

    /**
     * Spinner for tens values
     */
    private var spinnerTensValues: Spinner? = null

    /**
     * Spinner for units values.
     */
    private var spinnerUnitsValues: Spinner? = null

    /**
     * Hobby skills view model
     */
    private val hobbySkillsViewModel: HobbySkillsViewModel by sharedViewModel()

    /**
     * Points to spend view model
     */
    private val pointsToSpendViewModel: PointsToSpendViewModel by sharedViewModel()

    /**
     * Skills view model
     */
    private val skillsViewModel: SkillsViewModel by sharedViewModel()

    /**
     * button that validates the addition of value to a skill
     */
    private var buttonValidateAddValueToSkill: ImageButton? = null

    /**
     * Button to add skill
     */
    private var btnAddSkill: ImageButton? = null

    /**
     * Tens value adapter
     */
    private var tensAdapter: ArrayAdapter<Int>? = null

    /**
     * Units value adapter
     */
    private var unitAdapter: ArrayAdapter<Int>? = null

    /**
     * Value list for spinners
     */
    var valuesList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_hobby, container, false
        )
        return initialRootView
    }

    /**
     * Fragment lifecycle
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deserializeWidgets()
        setListeners()
        setSpinnerAdapters()
        startObservation()

        loadHobbySkillsRecyclerViewFragment()


    }

    /**
     * Set button add skill onClick listener
     */
    private fun buttonAddSkillSetOnClickListener() {
        btnAddSkill?.setOnClickListener {
            val intent = Intent(activity, EditSkillActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Set listeners
     */
    private fun setListeners() {
        setSpinnerListeners()
        setButtonsListeners()
        buttonAddSkillSetOnClickListener()
    }

    /**
     * Set buttons listeners
     */
    private fun setButtonsListeners() {
        setButtonValidateOnClickListener()
    }

    /**
     * Set button validate onClickListener
     */
    private fun setButtonValidateOnClickListener() {
        buttonValidateAddValueToSkill?.setOnClickListener {
            //  Get the spent points
            val spentPoints = pointsToSpendViewModel.observableHobbySpentPoints.value
            // Gets the total points to spend
            val pointsToSpend = hobbySkillsViewModel.hobbySkillsTotalPointsToSpend.value
            //Checks if activity is not null
            if (activity != null) {
                if (spentPoints != null && pointsToSpend != null) {
                    if (spentPoints >= pointsToSpend) {
                        val alertDialog = androidx.appcompat.app.AlertDialog.Builder(context!!)

                        alertDialog.setTitle("Warning !")
                        alertDialog.setMessage("No more points to spend.")

                        alertDialog.show()
                    } else {
                        val domainFilledSkill =
                            hobbySkillsViewModel.currentHobbySkill.value
                        if (tensValue != null) {
                            domainFilledSkill?.filledSkillTensValue = tensValue
                        }
                        if (unitsValue != null) {
                            Log.d("DEBUG$TAG", "Units value : $unitsValue")
                            domainFilledSkill?.filledSkillUnitsValue = unitsValue
                        }

                        hobbySkillsViewModel.currentHobbySkill.value = domainFilledSkill

                        val index =
                            skillsViewModel.hobbySkills.value?.indexOfFirst { skill ->
                                skill.skillId == domainFilledSkill?.skillId
                            }

                        val filledSkill = skillsViewModel.hobbySkills.value?.toMutableList()
                        if (filledSkill != null && index != null) {
                            if (domainFilledSkill != null) {
                                Log.d("DEBUG$TAG", "domainFilledSkill :$domainFilledSkill")
                                filledSkill[index] = domainFilledSkill
                            }
                        }
                        skillsViewModel.hobbySkills.value = filledSkill
                    }
                } else {
                    val domainSkillToFill = hobbySkillsViewModel.currentHobbySkill.value

                    if (tensValue != null) {
                        domainSkillToFill?.filledSkillTensValue = tensValue
                    }
                    if (unitsValue != null) {
                        domainSkillToFill?.filledSkillUnitsValue = unitsValue
                    }

                    hobbySkillsViewModel.currentHobbySkill.value = domainSkillToFill

                    val index = skillsViewModel.hobbySkills.value?.indexOfFirst { skill ->
                        (skill.skillId == domainSkillToFill?.skillId) && (skill.skillName == domainSkillToFill?.skillName)
                    }

                    Log.d("DEBUG$TAG", "Index : $index")

                    val filledSkills = skillsViewModel.hobbySkills.value?.toMutableList()

                    if (filledSkills != null && index != null) {
                        if (domainSkillToFill != null) {
                            filledSkills[index] = domainSkillToFill
                        }
                    }
                    skillsViewModel.hobbySkills.value = filledSkills
                }
            }
        }
    }

    /**
     * Set spinner listeners
     */
    private fun setSpinnerListeners() {
        setTensSpinnerOnItemSelectedListener()
        setUnitsSpinnerOnItemSelectedListener()
    }

    /**
     * Set units spinner on item selected listener
     */
    private fun setUnitsSpinnerOnItemSelectedListener() {
        Log.d(TAG, "setUnitsSpinnerOnItemSelectedListener")
        spinnerUnitsValues?.onItemSelectedListener = object : CustomOnItemSelectedListener() {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                unitsValue = this@HobbyFragment.valuesList[position]
            }

        }
    }

    /**
     * Set tens spinner on item selected listener
     */
    private fun setTensSpinnerOnItemSelectedListener() {
        Log.d(TAG, "setTensSpinnerOnItemSelectedListener")
        spinnerTensValues?.onItemSelectedListener = object : CustomOnItemSelectedListener() {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (hobbySkillsViewModel.currentHobbySkill.value != null) {
                    tensValue = this@HobbyFragment.valuesList[position]
                }
            }

        }
    }

    /**
     * Start observation
     */
    private fun startObservation() {
        observeSelectedHobbySkill()
    }

    /**
     * Deserialize widgets
     */
    private fun deserializeWidgets() {
        spinnerTensValues = view?.findViewById(R.id.spinner_hobby_skill_tens)
        spinnerUnitsValues = view?.findViewById(R.id.spinner_hobby_skill_units)
        buttonValidateAddValueToSkill = view?.findViewById(R.id.btn_validateSkillPoints)
        btnAddSkill = view?.findViewById(R.id.btn_hobby_add_skill)
    }

    /**
     * Set spinner adapters
     */
    private fun setSpinnerAdapters() {
        setTensSpinnerAdapter()
        setUnitsSpinnerAdapter()
    }

    /**
     * Set units spinner adapters.
     */
    private fun setUnitsSpinnerAdapter() {
        initializeUnitsAdapter()
        //  Set the units spinner adapter.
        spinnerUnitsValues?.adapter = unitAdapter
    }

    /**
     * Initialize units adapter
     */
    private fun initializeUnitsAdapter() {
        if (activity != null) {
            unitAdapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_spinner_item,
                valuesList
            )
        }
    }

    /**
     * Set tens spinner adapter
     */
    private fun setTensSpinnerAdapter() {
        initializeTensAdapter()
        //  Set the tens spinner adapter.
        spinnerTensValues?.adapter = tensAdapter
    }

    /**
     * Initialize tens adapter
     */
    private fun initializeTensAdapter() {
        if (activity != null) {
            tensAdapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_spinner_item,
                valuesList
            )
        }
    }

    /**
     * Observe selected hobby skill
     */
    private fun observeSelectedHobbySkill() {
        hobbySkillsViewModel.currentHobbySkill.observe(
            this,
            Observer { domainSkillToFill: DomainSkillToFill? ->
                kotlin.run {
                    Log.d("DEBUG$TAG", "Hobby selected skill = $domainSkillToFill")
                    if (domainSkillToFill == null) {
                        spinnerUnitsValues?.isEnabled = false
                        spinnerTensValues?.isEnabled = false
                        buttonValidateAddValueToSkill?.isEnabled = false
                    } else {
                        spinnerUnitsValues?.isEnabled = true
                        spinnerTensValues?.isEnabled = true
                        buttonValidateAddValueToSkill?.isEnabled = true
                    }
                    tv_selectedSkill.text = domainSkillToFill?.skillName
                    val tensIndex =
                        valuesList.indexOfFirst { v -> v == domainSkillToFill?.filledSkillTensValue }
                    if (tensIndex != null) {
                        spinnerTensValues?.setSelection(tensIndex)
                    }

                    val unitsIndex =
                        valuesList.indexOfFirst { v -> v == domainSkillToFill?.filledSkillUnitsValue }
                    if (unitsIndex != null) {
                        spinnerUnitsValues?.setSelection(unitsIndex)
                    }

                }

            })
    }

    /**
     * load hobby skills recyclerview fragment
     */
    private fun loadHobbySkillsRecyclerViewFragment() {
        if (activity != null) {
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(
                R.id.container_hobbySkills,
                HobbySkillsRecyclerViewFragment.newInstance(activity!!)
            ).commit()
        }
    }


    companion object : CustomCompanion() {
        private const val TAG = "HobbyFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): HobbyFragment {
            val fragment =
                HobbyFragment()

            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, HOBBY_FRAGMENT_POSITION)

            fragment.arguments = args


            return fragment
        }
    }

}