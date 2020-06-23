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
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.*
import com.uldskull.rolegameassistant.fragments.fragment.EditTextUtil.Companion.editTextEnabling
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.DERIVED_VALUES_2_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.DerivedValuesViewModel
import com.uldskull.rolegameassistant.viewmodels.IdealsViewModel
import kotlinx.android.synthetic.main.fragment_derived_values_2.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "DerivedValues2Fragment" :
 *   Derived values 2 fragment
 **/
class DerivedValues2Fragment : CustomFragment() {
    /**
     * Ideals view model
     */
    private val idealsViewModel: IdealsViewModel by sharedViewModel()

    /**
     * Characteristics view model
     */
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()

    /**
     * Derived values view model
     */
    private val derivedValuesViewModel: DerivedValuesViewModel by sharedViewModel()


    /**
     * fragment lifecycle
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
     * fragment lifecycle
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")


        setDamageBonusSpinner()
        enableOrDisableEditTexts()
        enableOrDisableDamageBonusSpinner()

        setListeners()
        startObservation()
    }

    /**
     * start observation
     */
    private fun startObservation() {
        observeCharacteristics()
        observePowerScore()
        observeSizePlusStrengthScore()
        observeDamageBonusIndex()
        observeIdeals()
        observeAlignmentScore()
        observeCthulhuMythScore()
    }

    /**
     * Observe cthulhu myth score
     */
    private fun observeCthulhuMythScore() {
        derivedValuesViewModel.cthulhuMythScore.observe(this, Observer { score ->
            kotlin.run {
                if (score != null) {
                    Log.d("DEBUG$TAG", "Cthulhu Score : $score")
                    if (et_cthulhuMyth.text.toString() != score.toString()) {
                        et_cthulhuMyth.setText(score.toString())
                    }
                }
            }
        })
    }

    /**
     * observe alignment score
     */
    private fun observeAlignmentScore() {
        derivedValuesViewModel.alignmentScore.observe(this, Observer { score ->
            kotlin.run {
                if (score != null) {
                    Log.d("DEBUG$TAG", "Score : $score")
                    if (et_alignmentPoints.text.toString() != score.toString()) {
                        et_alignmentPoints.setText(score.toString())
                        setAlignmentPicture(score)
                    }

                }
            }
        })
    }

    /**
     * observe ideals
     */
    private fun observeIdeals() {
        idealsViewModel.mutableIdeals?.observe(this, Observer { idealList ->
            if (idealList != null) {
                var checkedIdeals = idealList.filter { i -> i?.isChecked!! }
                calculateAlignmentScore(checkedIdeals)
            }
        })
    }

    /**
     * calculate alignment score
     */
    private fun calculateAlignmentScore(checkedIdeals: List<DomainIdeal?>) {
        derivedValuesViewModel.calculateAlignmentScore(checkedIdeals)
    }

    /**
     * observe damage bonus index
     */
    private fun observeDamageBonusIndex() {
        derivedValuesViewModel.selectedDamageBonusIndex.observe(this, Observer {
            if (it != null) {
                spinner_damageBonus.post {
                    spinner_damageBonus.setSelection(it)
                }
            }

        })
    }

    /**
     * observe size plus strength score
     */
    private fun observeSizePlusStrengthScore() {
        derivedValuesViewModel.sizePlusStrengthScore.observe(this, Observer {
            if (et_sizePlusStrength.text.toString() != it.toString()) {
                et_sizePlusStrength.setText(it.toString())
            }
        })
    }

    /**
     * observe power score
     */
    private fun observePowerScore() {
        derivedValuesViewModel.energyPoints.observe(this, Observer {
            if (et_energyPoints.text.toString() != it.toString()) {
                et_energyPoints.setText(it.toString())
            }
        })
    }

    /**
     * observe characteristics score
     */
    private fun observeCharacteristics() {
        characteristicsViewModel.displayedCharacteristics?.observe(
            this,
            Observer { domainRollsCharacteristics ->
                kotlin.run {
                    var power =
                        domainRollsCharacteristics.findLast { c -> c?.characteristicName == CharacteristicsName.POWER.characteristicName }
                    calculateEnergyScore(power)
                    var size =
                        domainRollsCharacteristics.findLast { c -> c?.characteristicName == CharacteristicsName.SIZE.characteristicName }
                    var strength =
                        domainRollsCharacteristics.findLast { c -> c?.characteristicName == CharacteristicsName.STRENGTH.characteristicName }
                    calculateDamageBonus(size, strength)
                }
            })
    }

    /**
     * calculate damage bonus value
     */
    private fun calculateDamageBonus(
        size: DomainRollsCharacteristic?,
        strength: DomainRollsCharacteristic?
    ) {
        if (size != null && strength != null) {
            derivedValuesViewModel.calculateDamageBonus(size, strength)
        }
    }

    /**
     * calculate energy score
     */
    private fun calculateEnergyScore(power: DomainRollsCharacteristic?) {
        derivedValuesViewModel.calculateEnergyPoints(power)
    }

    /**
     * set miscellaneous listeners
     */
    private fun setListeners() {
        setBtnEditClickListener()
        setEnergyPointsTextChangedListener()
        setSizePlusStrengthTextChangedListener()
        setDamageBonusSpinnerSelectionChangedListener()
        setCthulhuMythPointsTextChangedListener()
        setAlignmentPointsTextChangedListener()
    }


    /**
     * set alignment points text changed listener
     */
    private fun setAlignmentPointsTextChangedListener() {
        if (et_alignmentPoints != null) {
            et_alignmentPoints.addTextChangedListener(alignmentPointsTextWatcher())
        }
    }

    /**
     * Alignment points text watcher
     */
    private fun alignmentPointsTextWatcher(): CustomTextWatcher {
        return object : CustomTextWatcher() {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    if (s.toString() != "-") {
                        try {
                            if (derivedValuesViewModel.alignmentScore.value != s.toString()
                                    .toInt()
                            ) {
                                derivedValuesViewModel.alignmentScore.value = s.toString().toInt()
                            }
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

    /**
     * Set damage bonus spinner selection changed listener
     */
    private fun setDamageBonusSpinnerSelectionChangedListener() {
        spinner_damageBonus.onItemSelectedListener = object : CustomOnItemSelectedListener() {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (derivedValuesViewModel.selectedDamageBonusIndex.value != position) {
                    derivedValuesViewModel.selectedDamageBonusIndex.value = position
                    derivedValuesViewModel.damageBonus.value =
                        DerivedValuesViewModel.DamageBonus.values()[position]
                }
            }
        }
    }

    /**
     * set size plus strength text changed listener
     */
    private fun setSizePlusStrengthTextChangedListener() {
        et_sizePlusStrength.addTextChangedListener(object : CustomTextWatcher() {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    try {
                        if (derivedValuesViewModel.sizePlusStrengthScore.value != s.toString()
                                .toInt()
                        ) {
                            derivedValuesViewModel.sizePlusStrengthScore.value =
                                s.toString().toInt()
                            derivedValuesViewModel.sizePlusStrengthEditTextHasChanged = true
                        }

                    } catch (e: Exception) {
                        Log.e("ERROR$TAG", "et_sizePlusStrength FAILED")
                        e.printStackTrace()
                        throw e
                    }
                }
            }
        })
    }

    /**
     * Set energy points text changed listener
     */
    private fun setEnergyPointsTextChangedListener() {
        et_energyPoints.addTextChangedListener(object : CustomTextWatcher() {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    try {
                        if (derivedValuesViewModel.energyPoints.value != s.toString().toInt()) {
                            derivedValuesViewModel.energyPoints.value = s.toString().toInt()
                            derivedValuesViewModel.energyPointsEdiTextHasChanged = true
                        }

                    } catch (e: Exception) {
                        Log.e("ERROR$TAG", "et_energyPoints FAILED")
                        e.printStackTrace()
                        throw e
                    }
                }
            }
        })
    }

    /**
     * set cthulhu myth points text changed listener
     */
    private fun setCthulhuMythPointsTextChangedListener() {
        et_cthulhuMyth.addTextChangedListener(object : CustomTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    try {
                        if (derivedValuesViewModel.cthulhuMythScore.value.toString() != s.toString()) {
                            derivedValuesViewModel.cthulhuMythScore.value = s.toString().toInt()
                            derivedValuesViewModel.cthulhuMythScoreEditTextHasChanged = true
                        }

                    } catch (e: Exception) {
                        Log.e("ERROR$TAG", "et_cthulhuMyth FAILED")
                        e.printStackTrace()
                        throw e
                    }
                }
            }
        })
    }


    /**
     * set damage bonnus spinner
     */
    private fun setDamageBonusSpinner() {
        if (spinner_damageBonus != null) {

            if (activity != null) {
                val adapter = ArrayAdapter<DerivedValuesViewModel.DamageBonus>(
                    activity!!,
                    android.R.layout.simple_spinner_item,
                    DerivedValuesViewModel.DamageBonus.values()
                )
                spinner_damageBonus.adapter = adapter
            }

        }
    }


    /**
     * set alignment picture
     */
    private fun setAlignmentPicture(score: Int) {
        Log.d(TAG, "setAlignmentPicture")
        if (derivedValues_img_alignment != null) {
            val imgResId: Int = when {
                score < -25 -> {
                    R.drawable.evil_icon
                }
                score > 25 -> {
                    R.drawable.good_icon
                }
                else -> {
                    R.drawable.neutral_icon
                }
            }
            derivedValues_img_alignment.setImageResource(imgResId)
        }
    }

    /**
     * set button edit on click listener
     */
    private fun setBtnEditClickListener() {
        if (btn_edit != null) {
            btn_edit?.setOnClickListener {
                enableOrDisableEditTexts()
                enableOrDisableDamageBonusSpinner()
            }
        }
    }

    /**
     * enable or disable damage bonus spinner
     */
    private fun enableOrDisableDamageBonusSpinner() {
        if (spinner_damageBonus != null) {
            val enabled = spinner_damageBonus.isEnabled
            spinner_damageBonus.isEnabled = !enabled
        }
    }

    /**
     * enable or disable edit texts
     */
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

    /**
     * initialize view
     */
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
                DerivedValues2Fragment()
            fragment.activity = activity

            val args = Bundle()
            args.putInt(KEY_POSITION, DERIVED_VALUES_2_FRAGMENT_POSITION)
            fragment.arguments = args
            return fragment
        }
    }

}