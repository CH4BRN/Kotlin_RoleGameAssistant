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
import com.uldskull.rolegameassistant.fragments.adapter.DERIVED_VALUES_2_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.EditTextUtil.Companion.editTextEnabling
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.DerivedValuesViewModel
import com.uldskull.rolegameassistant.viewmodels.IdealsViewModel
import kotlinx.android.synthetic.main.fragment_derived_values_2.*
import kotlinx.android.synthetic.main.recyclerview_item_ideal.*
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
        setEnergyPoints()
        setSizePlusStrength()
        setDamageBonus()
        enableOrDisableEditTexts()
        enableOrDisableDamageBonusSpinner()
        setBtnEditClickListener()
        setAlignmentScore()

        setDamageBonusSpinner()
        setEnergyPointsTextListener()
        setSizePlusStrengthTextListener()
        setDamageBonusSpinnerSelectionChangedListsner()
        setAlignmentPicture()
    }

    private fun setDamageBonusSpinnerSelectionChangedListsner() {
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
                    DerivedValuesViewModel.DamageBonus.values().get(position)
            }

        }
    }

    private fun setSizePlusStrengthTextListener() {
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

    private fun setEnergyPointsTextListener() {
        et_energyPoints.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
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

    private fun setDamageBonus() {
        Log.d(TAG, "setDamageBonus")
        if (spinner_damageBonus != null && derivedValuesViewModel.selectedDamageBonusIndex != null) {
            Log.d(
                TAG,
                "Damage index : " + derivedValuesViewModel.selectedDamageBonusIndex.toString()
            )
            spinner_damageBonus.post(object : Runnable {
                override fun run() {
                    spinner_damageBonus.setSelection(derivedValuesViewModel.selectedDamageBonusIndex!!)
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

    private fun setAlignmentScore() {
        if (et_alignmentPoints != null) {
            idealsViewModel.calculateAlignmentScore()
            et_alignmentPoints?.setText(idealsViewModel.alignmentScore.toString())
        }
    }

    private fun setAlignmentPicture(){
        Log.d(TAG, "setAlignmentPicture")
        if(derivedValues_img_alignment != null){
            when{
                idealsViewModel.alignmentScore < -25 -> {
                    Log.d(TAG, "${idealsViewModel.alignmentScore} Evil")
                }
                idealsViewModel.alignmentScore in -25..25 ->{
                    Log.d(TAG, "${idealsViewModel.alignmentScore} Neutral")
                }
                idealsViewModel.alignmentScore > 25 -> {
                    Log.d(TAG, "${idealsViewModel.alignmentScore} Good")
                }


            }
        }
    }


    private fun setSizePlusStrength() {

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
        }
        if (et_sizePlusStrength != null) {
            editTextEnabling(et_sizePlusStrength)
        }

        if (et_alignmentPoints != null) {
            editTextEnabling(et_alignmentPoints)
        }
    }

    private fun setEnergyPoints() {
        Log.d(TAG, "setEnergyPoints")
        if (et_energyPoints != null) {
            var points = derivedValuesViewModel.calculateEnergyPoints(
                characteristicsViewModel.getPower()
            ).toString()
            Log.d(TAG, points)
            et_energyPoints.setText(
                points
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