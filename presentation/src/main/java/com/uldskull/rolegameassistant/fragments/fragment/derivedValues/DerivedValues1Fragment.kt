// File DerivedValues1Fragment.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.derivedValues

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.DERIVED_VALUES_1_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.CustomTextWatcher
import com.uldskull.rolegameassistant.fragments.fragment.EditTextUtil.Companion.editTextEnabling
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.DerivedValuesViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.breeds.DisplayedBreedsViewModel
import kotlinx.android.synthetic.main.fragment_derived_values_1.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "DerivedValues1Fragment" :
 *   Fragment that handle derived values 1
 **/
class DerivedValues1Fragment : CustomFragment() {

    /**
     * Characteristics view model.
     */
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()

    /**
     * Derived values view model.
     */
    private val derivedValuesViewModel: DerivedValuesViewModel by sharedViewModel()

    private val displayedBreedsViewModel: DisplayedBreedsViewModel by sharedViewModel()

    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()


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
        setBreedHealthBonusScore()
        setTotalHealthScore()
        setKnowScore()


    }

    /**
     * Set breed health bonus score
     */
    private fun setBreedHealthBonusScore() {
        Log.d(TAG, "setBreedHealthBonusScore")
        if (et_breedHealthBonus != null && !derivedValuesViewModel.breedBonusEditTextHasChanged) {
            derivedValuesViewModel.calculateBreedsHealthBonus(characteristicsViewModel.getCheckedBreeds())
        }
    }

    private fun setTotalHealthScore() {
        Log.d(TAG, "setTotalHealthScore")
        if (et_totalHealthPoints != null) {
            derivedValuesViewModel.calculateTotalHealth()
        }
    }

    private fun setKnowScore() {
        if (et_knowPoints != null && !derivedValuesViewModel.knowEditTextHasChanged) {
            //  Gets the characteristics
            var education = characteristicsViewModel.getEducation()
            Log.d("DEBUG$TAG", "Education : ${education?.characteristicRoll}")
            Log.d(TAG, "education : $education")
            if (education != null) {
                derivedValuesViewModel.calculateKnowScore(education)
            }
        }
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

        setEditTextsListeners()
        setBtnEditClickListener()
        startObservation()

        Log.d(TAG, "set health points")

    }

    private fun observeBreedHealthBonus() {
        derivedValuesViewModel?.breedHealthBonus?.observe(this, Observer {
            Log.d("DEBUG$TAG", "Breed bonus : ${it}")
            if (it != null && et_breedHealthBonus.text.toString() != it.toString()) {
                et_breedHealthBonus.setText(it.toString())
            }
        })
    }

    fun startObservation() {
        observeBaseHealthScore()
        observeBreedHealthBonus()
        observeTotalHealthScore()
        observeIdeaScore()
        observeSanityScore()
        observeBreeds()
        observeKnowScore()
        observeLuckScore()
        observeSelectedCharacter()
        observeCharacteristics()


    }

     private fun observeCharacteristics() {
        characteristicsViewModel?.displayedCharacteristics?.observe(
            this,
            Observer { domainRollsCharacteristics ->
                run {

                    //  Base health
                    var size = domainRollsCharacteristics?.findLast { c ->c?.characteristicName == CharacteristicsName.SIZE.characteristicName }
                    var constitution = domainRollsCharacteristics?.findLast { c-> c?.characteristicName == CharacteristicsName.CONSTITUTION.characteristicName }
                    calculateBaseHealthPoints(size, constitution)
                    //  Idea
                    var intelligence = domainRollsCharacteristics?.findLast { c-> c?.characteristicName == CharacteristicsName.INTELLIGENCE.characteristicName }
                    calculateIdeaScore(intelligence)
                    //  Sanity
                    var power = domainRollsCharacteristics?.findLast { c -> c?.characteristicName == CharacteristicsName.POWER.characteristicName }
                    calculateSanityScore(power)
                    //  Luck
                    calculateLuckScore(power)
                    //  Know
                    var education = domainRollsCharacteristics?.findLast { c -> c?.characteristicName == CharacteristicsName.EDUCATION.characteristicName }
                    calculateKnowScore(education)
                }
            })
    }

    private fun calculateKnowScore(education:DomainRollsCharacteristic?){
        if(education != null){
            derivedValuesViewModel.calculateKnowScore(education)
        }
    }

    private fun calculateLuckScore(power:DomainRollsCharacteristic?){
        if(power != null){
            derivedValuesViewModel.calculateLuckScore(power)
        }
    }

    private fun calculateSanityScore(power:DomainRollsCharacteristic?){
        if(power != null){
            derivedValuesViewModel.calculateSanityScore(power)
        }
    }

    private fun calculateIdeaScore(intelligence:DomainRollsCharacteristic?){
        if(intelligence != null){
            derivedValuesViewModel.calculateIdeaScore(intelligence)
        }
    }

    private fun calculateBaseHealthPoints(size:DomainRollsCharacteristic?, constitution:DomainRollsCharacteristic?){
        if (size != null && constitution != null) {
            derivedValuesViewModel.calculateBaseHealth(listOf(size, constitution))
        }
    }

    private fun observeSelectedCharacter() {
        newCharacterViewModel?.selectedCharacter?.observe(
            this,
            Observer { domainCharacter: DomainCharacter? ->
                if (domainCharacter != null) {
                    Log.d("DEBUG$TAG", "TotalHp : ${domainCharacter?.characterHealthPoints}")
                    derivedValuesViewModel?.totalHealth.value =
                        domainCharacter?.characterHealthPoints
                    Log.d("DEBUG$TAG", "BaseHp : ${domainCharacter?.characterBaseHealthPoints}")
                    derivedValuesViewModel?.baseHealth.value =
                        domainCharacter?.characterBaseHealthPoints
                    derivedValuesViewModel?.breedHealthBonus.value =
                        domainCharacter?.characterBreedBonus
                    Log.d("DEBUG$TAG", "Idea : ${domainCharacter?.characterIdeaPoints}")
                    derivedValuesViewModel?.ideaScore.value = domainCharacter?.characterIdeaPoints
                    Log.d("DEBUG$TAG", "Sanity : ${domainCharacter?.characterSanity}")
                    derivedValuesViewModel?.sanityScore.value = domainCharacter?.characterSanity
                    Log.d("DEBUG$TAG", "Luck : ${domainCharacter?.characterLuck}")
                    derivedValuesViewModel?.luckScore.value = domainCharacter?.characterLuck
                    Log.d("DEBUG$TAG", "Know : ${domainCharacter?.characterKnow}")
                    derivedValuesViewModel?.knowScore.value = domainCharacter?.characterKnow
                }
            })
    }

    private fun observeBreeds() {
        displayedBreedsViewModel?.observedMutableBreeds.observe(
            this,
            Observer { domainDisplayedBreeds ->
                kotlin.run {
                    var breedBonus = 0
                    domainDisplayedBreeds?.forEach {
                        if (it.breedChecked) {
                            Log.d("DEBUG$TAG", "Breed bonus : ${it.breedHealthBonus}")
                            if (it.breedHealthBonus != null) {
                                breedBonus += it.breedHealthBonus!!
                            }
                        }
                    }
                    derivedValuesViewModel?.breedHealthBonus?.value = breedBonus
                }
            })
    }

    private fun observeLuckScore() {
        derivedValuesViewModel?.luckScore?.observe(this, Observer { luckScore ->
            if (luckScore != null && et_luckPoints.text.toString() != luckScore.toString()) {
                Log.d("DEBUG$TAG", "Luck : $luckScore")
                et_luckPoints.setText(luckScore.toString())
            }
        })
    }

    private fun observeTotalHealthScore() {
        derivedValuesViewModel?.totalHealth?.observe(this, Observer {
            Log.d("DEBUG$TAG", "Total health : ${it}")
            if (it != null && et_totalHealthPoints.text.toString() != it.toString()) {
                et_totalHealthPoints.setText(it.toString())
            }
        })
    }

    private fun observeIdeaScore() {
        derivedValuesViewModel?.ideaScore?.observe(this, Observer {
            if (it != null && et_ideaPoints.text.toString() != it.toString()) {
                et_ideaPoints.setText(it.toString())
            }
        })
    }

    private fun observeSanityScore() {
        derivedValuesViewModel?.sanityScore?.observe(this, Observer {
            Log.d("DEBUG$TAG", "Sanity : $it")
            if (it != null && et_sanityPoints.text.toString() != it.toString()) {
                et_sanityPoints.setText(it.toString())
            }
        })
    }

    private fun observeBaseHealthScore() {
        derivedValuesViewModel?.baseHealth?.observe(this, Observer {
            Log.d("DEBUG$TAG", "Base health : ${it}")
            if (it != null && et_baseHealthPoints.text.toString() != it.toString()) {
                et_baseHealthPoints.setText(it.toString())
            }
        })
    }

    private fun observeKnowScore() {
        derivedValuesViewModel?.knowScore?.observe(this, Observer {
            Log.d("DEBUG$TAG", "Know score : $it")
            if (it != null && et_knowPoints.text.toString() != it.toString()) {
                et_knowPoints.setText(it.toString())
            }
        })
    }


    private fun setEditTextsListeners() {
        setBaseHealthPointListener()
        setBreedBonusListener()
        setIdeaListener()
        setKnowPointsListener()
        setSanityListener()
        setLuckListener()
    }

    private fun setSanityListener() {
        et_sanityPoints.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.sanityEditTextHasChanged = true
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    try {
                        derivedValuesViewModel.sanityScore.value = s.toString().toInt()
                    } catch (e: Exception) {
                        Log.e(TAG, "et_sanityPoints failed")
                        e.printStackTrace()
                        throw e
                    }
                }
            }

        })
    }

    private fun setLuckListener() {
        et_luckPoints.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.luckEditTextHasChanged = true
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (!(s.isNullOrEmpty()) && !(s.isNullOrBlank())) {
                    try {
                        var sequence = s
                        Log.d("DEBUG$TAG", "Sequence : $sequence")
                        if (sequence != null) {
                            var sequenceString = sequence.toString()
                            Log.d("DEBUG$TAG", "sequenceString : $sequenceString")
                            if (sequenceString != null) {
                                var sequenceInt = sequenceString.toInt()
                                derivedValuesViewModel.luckScore.value = sequenceInt
                            }

                        }

                    } catch (e: Exception) {
                        Log.e(TAG, "et_luckPoints failed")
                        e.printStackTrace()
                        throw e
                    }
                }
            }
        })
    }

    private fun setIdeaListener() {
        et_ideaPoints.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.ideaEditTextHasChanged = true
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank() && !s.isNullOrEmpty()) {
                    try {
                        derivedValuesViewModel.ideaScore.value = s.toString().toInt()
                    } catch (e: Exception) {
                        Log.e(TAG, "et_ideaPoints failed")
                        e.printStackTrace()
                        throw e
                    }
                }
            }
        })
    }

    private fun setKnowPointsListener() {
        et_knowPoints.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.knowScoreEditTextHasChanged = true
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank() && !s.isNullOrEmpty()) {
                    try {
                        derivedValuesViewModel.knowScore.value = s.toString().toInt()
                    } catch (e: Exception) {
                        Log.e(TAG, "et_knowPoints FAILED")
                        e.printStackTrace()
                        throw e
                    }
                }
            }

        })
    }


    private fun setBreedBonusListener() {
        et_breedHealthBonus.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.breedBonusEditTextHasChanged = true
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (!s.isNullOrBlank()) {
                    try {
                        derivedValuesViewModel.breedHealthBonus.value = s.toString().toInt()
                    } catch (e: Exception) {
                        Log.e(TAG, "et_breedHealthBonus failed")
                        e.printStackTrace()
                        throw e
                    }
                }
                setTotalHealthScore()
            }
        })
    }


    private fun setBaseHealthPointListener() {
        et_baseHealthPoints.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.baseHealthEditTextHasChanged = true
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank() && !s.isNullOrEmpty()) {
                    try {
                        Log.e(TAG, "et_healthPoints $s")
                        derivedValuesViewModel.baseHealth.value = s.toString().toInt()
                    } catch (e: Exception) {
                        Log.e(TAG, "et_healthPoints failed")
                        e.printStackTrace()
                        throw e
                    }
                    setTotalHealthScore()
                }
            }
        })
    }

    private fun setBtnEditClickListener() {
        if (btn_edit != null) {
            btn_edit?.setOnClickListener {
                editTextsEnabling()
            }
        }
    }

    private fun editTextsEnabling() {
        if (et_baseHealthPoints != null) {
            editTextEnabling(et_baseHealthPoints)
            et_baseHealthPoints.requestFocus()
        }
        if (et_breedHealthBonus != null) {
            editTextEnabling(et_breedHealthBonus)
        }
        if (et_ideaPoints != null) {
            editTextEnabling(et_ideaPoints)
        }
        if (et_sanityPoints != null) {
            editTextEnabling(et_sanityPoints)
        }
        if (et_luckPoints != null) {
            editTextEnabling(et_luckPoints)
        }
        if (et_knowPoints != null) {
            editTextEnabling(et_knowPoints)
        }

    }

    companion object : CustomCompanion() {
        private const val TAG = "DerivedValues1Fragment"

        @JvmStatic
        override fun newInstance(activity: Activity): DerivedValues1Fragment {
            Log.d(TAG, "newInstance")

            val fragment =
                DerivedValues1Fragment(

                )
            fragment.activity = activity

            val args = Bundle()

            args.putInt(KEY_POSITION, DERIVED_VALUES_1_FRAGMENT_POSITION)
            fragment.arguments = args
            return fragment
        }
    }
}