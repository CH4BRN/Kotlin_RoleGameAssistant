// File DerivedValues1Fragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.derivedValues

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.fragments.adapter.DERIVED_VALUES_1_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.EditTextUtil.Companion.editTextEnabling
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollCharacteristic
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.DerivedValuesViewModel
import com.uldskull.rolegameassistant.viewmodels.IdealsViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_derived_values_1.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "DerivedValues1Fragment" :
 *   Fragment that handle derived values 1
 **/
class DerivedValues1Fragment(activity: Activity) : CustomFragment(activity) {

    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()

    private val derivedValuesViewModel: DerivedValuesViewModel by sharedViewModel()

    private val idealsViewModel: IdealsViewModel by sharedViewModel()

    /**
     * Called when the view is created
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
     * Called when the focus return on this view
     */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        NewCharacterActivity.progression.value = DERIVED_VALUES_1_FRAGMENT_POSITION
        setHealthPointsScore()
        setBreedHealthBonusScore()
        setTotalHealthScore()
        setIdeaScore()
        setChanceScore()
        setAlignmentScore()
    }

    /**
     * Set breed health bonus score
     */
    private fun setBreedHealthBonusScore() {
        if (et_breedHealthBonus != null) {
            derivedValuesViewModel.breedHealthBonus =
                derivedValuesViewModel.calculateBreedsHealthBonus(
                    characteristicsViewModel.characterBreeds.filter { b -> b.breedChecked })
            et_breedHealthBonus.setText(derivedValuesViewModel.breedHealthBonus!!.toString())
        }
    }

    private fun setTotalHealthScore() {
        if (et_totalHealth != null) {
            et_totalHealth.setText(derivedValuesViewModel.calculateTotalHealth().toString())
        }
    }

    private fun setIdeaScore() {
        if (et_ideaPoints != null) {
            et_ideaPoints.setText(derivedValuesViewModel.calculateIdeaScore(getIntelligence()).toString())
        }
    }

    private fun setChanceScore() {
        if (et_chancePoints != null) {
            et_chancePoints.setText(derivedValuesViewModel.calculateChanceScore(getPower()).toString())
        }
    }

    private fun setAlignmentScore() {
        if (et_alignmentPoints != null) {
            et_alignmentPoints.setText(idealsViewModel.calculateAlignmentScore().toString())
        }
    }

    private fun setHealthPointsScore() {
        if (et_healthPoints != null) {
            var size =
                getSize()
            var constitution =
                getConstitution()
            if (size != null && constitution != null) {
                var hp = derivedValuesViewModel.calculateBaseHealth(listOf(size, constitution))
                if (hp != null) {
                    et_healthPoints.setText(hp.toString())
                }
            }
        }
    }


    private fun getIntelligence(): DomainRollCharacteristic? {
        var intelligence =
            characteristicsViewModel.rollCharacteristics.find { c -> c.characteristicName == CharacteristicsName.INTELLIGENCE.toString() }
        Log.d(TAG, "INTELLIGENCE : $intelligence")
        return intelligence
    }

    private fun getPower(): DomainRollCharacteristic? {
        var power =
            characteristicsViewModel.rollCharacteristics.find { c -> c.characteristicName == CharacteristicsName.POWER.toString() }
        Log.d(TAG, "POWER : $power")
        return power
    }

    private fun getConstitution(): DomainRollCharacteristic? {
        var constitution =
            characteristicsViewModel.rollCharacteristics.find { c -> c.characteristicName == CharacteristicsName.CONSTITUTION.toString() }
        Log.d(TAG, "CONSTITUTION : $constitution")
        return constitution
    }

    private fun getSize(): DomainRollCharacteristic? {
        var size =
            characteristicsViewModel.rollCharacteristics.find { c -> c.characteristicName == CharacteristicsName.SIZE.toString() }
        Log.d(TAG, "SIZE : $size")
        return size
    }

    /**
     * Initialize the view
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_derived_values_1, container, false
        )
        return initialRootView
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

        editTextsEnabling()
        setBtnEditClickListener()
        Log.d(TAG, "set health points")

    }

    private fun setBtnEditClickListener() {
        if (btn_edit != null) {
            btn_edit?.setOnClickListener {
                editTextsEnabling()
            }
        }
    }

    private fun editTextsEnabling() {
        if (et_healthPoints != null) {
            editTextEnabling(et_healthPoints)
        }
        if (et_breedHealthBonus != null) {
            editTextEnabling(et_breedHealthBonus)
        }
        if (et_totalHealth != null) {
            editTextEnabling(et_totalHealth)
        }
        if (et_ideaPoints != null) {
            editTextEnabling(et_ideaPoints)
        }
        if (et_chancePoints != null) {
            editTextEnabling(et_chancePoints)
        }
        if (et_alignmentPoints != null) {
            editTextEnabling(et_alignmentPoints)
        }
    }

    companion object : CustomCompanion() {
        private const val TAG = "DerivedValues1Fragment"

        @JvmStatic
        override fun newInstance(activity: Activity): DerivedValues1Fragment {
            Log.d(TAG, "newInstance")

            val fragment =
                DerivedValues1Fragment(
                    activity
                )

            val args = Bundle()

            args.putInt(KEY_POSITION, DERIVED_VALUES_1_FRAGMENT_POSITION)
            fragment.arguments = args
            return fragment
        }
    }
}