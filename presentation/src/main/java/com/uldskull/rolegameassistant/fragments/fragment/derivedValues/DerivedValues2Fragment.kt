// File DerivedValues2Fragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.derivedValues

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.DERIVED_VALUES_2_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.EditTextUtil.Companion.editTextEnabling
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.DerivedValuesViewModel
import com.uldskull.rolegameassistant.viewmodels.IdealsViewModel
import kotlinx.android.synthetic.main.fragment_derived_values_2.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "DerivedValues2Fragment" :
 *   TODO("COMMENT")
 **/
class DerivedValues2Fragment(activity: Activity) : CustomFragment(activity) {

    private val idealsViewModel: IdealsViewModel by sharedViewModel()
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()
    private val derivedValuesViewModel: DerivedValuesViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return initializeView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        setScores()

        setDamageBonusSpinner()
        setAlignmentPicture()

        enableOrDisableEditTexts()
        enableOrDisableDamageBonusSpinner()

        setListeners()
    }

    private fun setListeners() {
        setBtnEditClickListener()
        setEnergyPointsTextChangedListener()
        setSizePlusStrengthTextChangedListener()
        setDamageBonusSpinnerSelectionChangedListener()
        setCthulhuMythPointsTextChangedListener()
        setAlignmentPointsTextChangedListener()
    }

    private fun setScores() {
        setEnergyPointsScore()
        setSizePlusStrengthScore()
        setDamageBonusScore()
        setAlignmentScore()
        setCthulhuMythScore()
    }

    private fun setAlignmentPointsTextChangedListener() {
        if (et_alignmentPoints != null) {
            et_alignmentPoints.addTextChangedListener(alignmentPointsTextWatcher())
        }
    }

    private fun alignmentPointsTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setAlignmentPicture()
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    if (s.toString() != "-") {
                        try {
                            idealsViewModel.alignmentScore = s.toString().toInt()
                        } catch (e: Exception) {
                            Log.e(TAG, "et_alignmentPoints FAILED")
                            e.printStackTrace()
                            throw e
                        }
                    }
                }
            }
        }
    }

    private fun setDamageBonusSpinnerSelectionChangedListener() {
        spinner_damageBonus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                derivedValuesViewModel.selectedDamageBonusIndex = position
                derivedValuesViewModel.damageBonus =
                    DerivedValuesViewModel.DamageBonus.values()[position]
            }

        }
    }

    private fun setSizePlusStrengthTextChangedListener() {
        et_sizePlusStrength.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    try {
                        derivedValuesViewModel.sizePlusStrengthScore = s.toString().toInt()
                        derivedValuesViewModel.sizePlusStrengthEditTextHasChanged = true
                    } catch (e: Exception) {
                        Log.e(TAG, "et_sizePlusStrength FAILED")
                        e.printStackTrace()
                        throw e
                    }
                }
            }

        })
    }

    private fun setEnergyPointsTextChangedListener() {
        et_energyPoints.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setAlignmentPicture()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    try {
                        derivedValuesViewModel.energyPoints = s.toString().toInt()
                        derivedValuesViewModel.energyPointsEdiTextHasChanged = true
                    } catch (e: Exception) {
                        Log.e(TAG, "et_energyPoints FAILED")
                        e.printStackTrace()
                        throw e
                    }
                }
            }
        })
    }

    private fun setCthulhuMythPointsTextChangedListener() {
        et_cthulhuMyth.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    try {
                        derivedValuesViewModel.cthulhuMythScore = s.toString().toInt()
                        derivedValuesViewModel.cthulhuMythScoreEditTextHasChanged = true
                    } catch (e: Exception) {
                        Log.e(TAG, "et_cthulhuMyth FAILED")
                        e.printStackTrace()
                        throw e
                    }
                }
            }

        })
    }

    private fun setDamageBonusScore() {
        Log.d(TAG, "setDamageBonus")
        if (spinner_damageBonus != null && derivedValuesViewModel.selectedDamageBonusIndex != null) {
            Log.d(
                TAG,
                "Damage index : " + derivedValuesViewModel.selectedDamageBonusIndex.toString()
            )
            spinner_damageBonus.post(object : Runnable {
                override fun run() {
                    if (derivedValuesViewModel.selectedDamageBonusIndex != null) {
                        spinner_damageBonus.setSelection(derivedValuesViewModel.selectedDamageBonusIndex!!)
                    }
                }
            })
        }
    }

    private fun setDamageBonusSpinner() {
        if (spinner_damageBonus != null) {

            val adapter = ArrayAdapter<DerivedValuesViewModel.DamageBonus>(
                activity,
                android.R.layout.simple_spinner_item,
                DerivedValuesViewModel.DamageBonus.values()
            )
            spinner_damageBonus.adapter = adapter
        }
    }

    private fun setCthulhuMythScore() {
        if (et_cthulhuMyth != null) {
            et_cthulhuMyth?.setText(derivedValuesViewModel.cthulhuMythScore.toString())
        }
    }

    private fun setAlignmentScore() {
        if (et_alignmentPoints != null) {
            idealsViewModel.calculateAlignmentScore()
            et_alignmentPoints?.setText(idealsViewModel.alignmentScore.toString())
        }
    }

    private fun setAlignmentPicture() {
        Log.d(TAG, "setAlignmentPicture")
        if (derivedValues_img_alignment != null) {
            val imgResId: Int = when {
                idealsViewModel.alignmentScore < -25 -> {
                    Log.d(TAG, "${idealsViewModel.alignmentScore} Evil")
                    R.drawable.evil_icon
                }
                idealsViewModel.alignmentScore > 25 -> {
                    Log.d(TAG, "${idealsViewModel.alignmentScore} Good")
                    R.drawable.good_icon
                }
                else -> {
                    Log.d(TAG, "${idealsViewModel.alignmentScore} Neutral")
                    R.drawable.neutral_icon

                }
            }
            derivedValues_img_alignment.setImageResource(imgResId)
        }
    }


    private fun setSizePlusStrengthScore() {

        if (et_sizePlusStrength != null) {
            var sizePlusStrengthValue = derivedValuesViewModel.calculateSizePlusStrength(
                listOf(
                    characteristicsViewModel.getStrength(),
                    characteristicsViewModel.getSize()
                )
            ).toString()
            Log.d(
                TAG, "setSizePlusStrength\n" +
                        "\tsize : ${characteristicsViewModel.getSize()}\n" +
                        "\tstrength : ${characteristicsViewModel.getStrength()}\n" +
                        "\tResult :  $sizePlusStrengthValue"
            )
            et_sizePlusStrength.setText(sizePlusStrengthValue)

        }
    }

    private fun setBtnEditClickListener() {
        if (btn_edit != null) {
            btn_edit?.setOnClickListener {
                enableOrDisableEditTexts()
                enableOrDisableDamageBonusSpinner()
            }
        }
    }

    private fun enableOrDisableDamageBonusSpinner() {
        if (spinner_damageBonus != null) {
            val enabled = spinner_damageBonus.isEnabled
            spinner_damageBonus.isEnabled = !enabled
        }
    }

    private fun enableOrDisableEditTexts() {
        if (et_energyPoints != null) {
            editTextEnabling(et_energyPoints)
            et_energyPoints.requestFocus()
        }
        if (et_sizePlusStrength != null) {
            editTextEnabling(et_sizePlusStrength)
        }

        if (et_alignmentPoints != null) {
            editTextEnabling(et_alignmentPoints)
        }

        if (et_cthulhuMyth != null) {
            editTextEnabling(et_cthulhuMyth)
        }
    }

    private fun setEnergyPointsScore() {
        Log.d(TAG, "setEnergyPoints")
        if (et_energyPoints != null) {
            var power = characteristicsViewModel.getPower()
            Log.d(TAG, "power = ${power}")
            derivedValuesViewModel.calculateEnergyPoints(
                power
            ).toString()
            Log.d(TAG, "${derivedValuesViewModel.energyPoints}")
            et_energyPoints.setText(
                "${derivedValuesViewModel.energyPoints}"
            )
        }
    }

    /**
     * Called when the focus return on this view
     */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")


        NewCharacterActivity.progression.value = DERIVED_VALUES_2_FRAGMENT_POSITION

    }

    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_derived_values_2, container, false
        )
        return initialRootView
    }

    companion object : CustomCompanion() {
        const val TAG = "DerivedValues2Fragment"

        @JvmStatic
        override fun newInstance(activity: Activity): DerivedValues2Fragment {
            Log.d(TAG, "newInstance")
            val fragment =
                DerivedValues2Fragment(
                    activity
                )

            val args = Bundle()
            args.putInt(KEY_POSITION, DERIVED_VALUES_2_FRAGMENT_POSITION)
            fragment.arguments = args
            return fragment
        }
    }

}