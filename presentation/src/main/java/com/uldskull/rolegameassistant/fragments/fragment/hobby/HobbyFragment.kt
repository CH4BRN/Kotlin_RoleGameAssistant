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
import com.uldskull.rolegameassistant.activities.NewSkillActivity
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.CustomOnItemSelectedListener
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.HOBBY_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.viewmodels.hobbies.HobbySkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.PointsToSpendViewModel
import com.uldskull.rolegameassistant.viewmodels.SkillsViewModel
import kotlinx.android.synthetic.main.fragment_hobby.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "HobbyFragment" :
 *   TODO: Fill class use.
 **/
class HobbyFragment : CustomFragment() {

    private var tensValue: Int? = null
    private var unitsValue: Int? = null
    private var spinnerTensValues: Spinner? = null
    private var spinnerUnitsValues: Spinner? = null
    private val hobbySkillsViewModel: HobbySkillsViewModel by sharedViewModel()
    private val pointsToSpendViewModel: PointsToSpendViewModel by sharedViewModel()
    private val skillsViewModel: SkillsViewModel by sharedViewModel()

    /**
     * button that validates the addition of value to a skill
     */
    private var buttonValidateAddValueToSkill: ImageButton? = null

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        deserializeWidgets()
        setListeners()
        setSpinnerAdapters()
        startObservation()

        loadHobbySkillsRecyclerViewFragment()
        val btnAddSkill = view.findViewById<ImageButton>(R.id.btn_hobby_add_skill)
        btnAddSkill.setOnClickListener {
            val intent = Intent(activity, NewSkillActivity::class.java)
            startActivity(intent)

        }

    }

    private fun setListeners() {
        setSpinnerListeners()
        setButtonsListeners()
    }

    private fun setButtonsListeners() {
        setButtonValidateOnClickListener()
    }

    private fun setButtonValidateOnClickListener() {
        buttonValidateAddValueToSkill?.setOnClickListener {
            //  Get the spent points
            var spentPoints = pointsToSpendViewModel.observableHobbySpentPoints.value
            // Gets the total points to spend
            var pointsToSpend = hobbySkillsViewModel.hobbySkillsTotalPointsToSpend.value
            //Checks if activity is not null
            if (activity != null) {
                if (spentPoints != null && pointsToSpend != null) {
                    if (spentPoints >= pointsToSpend) {
                        val alertDialog = androidx.appcompat.app.AlertDialog.Builder(context!!)

                        alertDialog.setTitle("Warning !")
                        alertDialog.setMessage("No more points to spend.")

                        alertDialog.show()
                    } else {
                        var domainFilledSkill =
                            hobbySkillsViewModel.currentHobbySkill.value
                        if (tensValue != null) {
                            domainFilledSkill?.filledSkillTensValue = tensValue
                        }
                        if (unitsValue != null) {
                            Log.d("DEBUG$TAG", "Units value : $unitsValue")
                            domainFilledSkill?.filledSkillUnitsValue = unitsValue
                        }

                        hobbySkillsViewModel.currentHobbySkill.value = domainFilledSkill

                        var index =
                            skillsViewModel.hobbySkills.value?.indexOfFirst { skill ->
                                skill?.skillId == domainFilledSkill?.skillId
                            }

                        var filledSkill = skillsViewModel?.hobbySkills.value?.toMutableList()
                        if (filledSkill != null && index != null) {
                            if (domainFilledSkill != null) {
                                Log.d("DEBUG$TAG", "domainFilledSkill :$domainFilledSkill")
                                filledSkill[index] = domainFilledSkill
                            }
                        }
                        skillsViewModel.hobbySkills.value = filledSkill
                    }
                } else {
                    var domainSkillToFill = hobbySkillsViewModel?.currentHobbySkill.value

                    if (tensValue != null) {
                        domainSkillToFill?.filledSkillTensValue = tensValue
                    }
                    if (unitsValue != null) {
                        domainSkillToFill?.filledSkillUnitsValue = unitsValue
                    }

                    hobbySkillsViewModel?.currentHobbySkill.value = domainSkillToFill

                    var index = skillsViewModel?.hobbySkills.value?.indexOfFirst { skill ->
                        (skill?.skillId == domainSkillToFill?.skillId) && (skill?.skillName == domainSkillToFill?.skillName)
                    }

                    Log.d("DEBUG$TAG", "Index : $index")

                    var filledSkills = skillsViewModel?.hobbySkills.value?.toMutableList()

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

    private fun setSpinnerListeners() {
        setTensSpinnerOnItemSelectedListener()
        setUnitsSpinnerOnItemSelectedListener()
    }

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

    private fun startObservation() {
        observeSelectedHobbySkill()

    }

    private fun deserializeWidgets() {
        spinnerTensValues = view?.findViewById(R.id.spinner_hobby_skill_tens)
        spinnerUnitsValues = view?.findViewById(R.id.spinner_hobby_skill_units)
        buttonValidateAddValueToSkill = view?.findViewById(R.id.btn_validateSkillPoints)
    }

    private fun setSpinnerAdapters() {
        setTensSpinnerAdapter()
        setUnitsSpinnerAdapter()
    }

    private fun setUnitsSpinnerAdapter() {
        initializeUnitsAdapter()
        //  Set the units spinner adapter.
        spinnerUnitsValues?.adapter = unitAdapter
    }

    private fun initializeUnitsAdapter() {
        if (activity != null) {
            unitAdapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_spinner_item,
                valuesList
            )
        }
    }

    private fun setTensSpinnerAdapter() {
        initializeTensAdapter()
        //  Set the tens spinner adapter.
        spinnerTensValues?.adapter = tensAdapter
    }

    private fun initializeTensAdapter() {
        if (activity != null) {
            tensAdapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_spinner_item,
                valuesList
            )
        }
    }

    private fun observeSelectedHobbySkill() {
        hobbySkillsViewModel?.currentHobbySkill?.observe(
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
                    var tensIndex =
                        valuesList.indexOfFirst { v -> v == domainSkillToFill?.filledSkillTensValue }
                    if (tensIndex != null) {
                        spinnerTensValues?.setSelection(tensIndex)
                    }

                    var unitsIndex =
                        valuesList.indexOfFirst { v -> v == domainSkillToFill?.filledSkillUnitsValue }
                    if (unitsIndex != null) {
                        spinnerUnitsValues?.setSelection(unitsIndex)
                    }

                }

            })
    }

    private fun loadHobbySkillsRecyclerViewFragment() {
        if (activity != null) {
            var transaction = childFragmentManager.beginTransaction()
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