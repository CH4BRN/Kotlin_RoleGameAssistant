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
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomFragment
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomTextWatcher
import com.uldskull.rolegameassistant.fragments.core.utils.EditTextUtil.Companion.editTextEnabling
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.models.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.DerivedValuesViewModel
import com.uldskull.rolegameassistant.viewmodels.character.NewCharacterViewModel
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

    /**
     * displayed breeds view model
     */
    private val displayedBreedsViewModel: DisplayedBreedsViewModel by sharedViewModel()

    /**
     * new character view model
     */
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

    /**
     * set total health score
     */
    private fun setTotalHealthScore() {
        Log.d(TAG, "setTotalHealthScore")
        if (et_totalHealthPoints != null) {
            derivedValuesViewModel.calculateTotalHealth()
        }
    }

    /**
     * Set know score
     */
    private fun setKnowScore() {
        if (et_knowPoints != null && !derivedValuesViewModel.knowEditTextHasChanged) {
            val education = characteristicsViewModel.getEducation()
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
    }

    /**
     * Observe breed health bonus
     */
    private fun observeBreedHealthBonus() {
        derivedValuesViewModel.breedHealthBonus.observe(this, Observer {
            Log.d("DEBUG$TAG", "Breed bonus : $it")
            if (it != null && et_breedHealthBonus.text.toString() != it.toString()) {
                et_breedHealthBonus.setText(it.toString())
                if(derivedValuesViewModel.baseHealth.value != null){
                    val baseHealth = derivedValuesViewModel.baseHealth.value
                    if(baseHealth != null){
                        val totalHealth = baseHealth + it
                        derivedValuesViewModel.totalHealth.value = totalHealth
                    }
                }else{
                    derivedValuesViewModel.totalHealth.value = it
                }

            }
        })
    }

    /**
     * Start observation
     */
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

    /**
     * Observe characteristics
     */
     private fun observeCharacteristics() {
        characteristicsViewModel.displayedCharacteristics?.observe(
            this,
            Observer { domainRollsCharacteristics ->
                run {
                    //  Base health
                    val size = domainRollsCharacteristics?.findLast { c ->c?.characteristicName == CharacteristicsName.SIZE.characteristicName }
                    val constitution = domainRollsCharacteristics?.findLast { c-> c?.characteristicName == CharacteristicsName.CONSTITUTION.characteristicName }
                    derivedValuesViewModel.calculateBaseHealth(listOf(size, constitution))
                    //  Idea
                    val intelligence = domainRollsCharacteristics?.findLast { c-> c?.characteristicName == CharacteristicsName.INTELLIGENCE.characteristicName }
                    derivedValuesViewModel.calculateIdeaScore(intelligence)
                    //  Sanity
                    val power = domainRollsCharacteristics?.findLast { c -> c?.characteristicName == CharacteristicsName.POWER.characteristicName }
                    derivedValuesViewModel.calculateSanityScore(power)
                    //  Luck
                    derivedValuesViewModel.calculateLuckScore(power)
                    //  Know
                    val education = domainRollsCharacteristics?.findLast { c -> c?.characteristicName == CharacteristicsName.EDUCATION.characteristicName }
                    derivedValuesViewModel.calculateKnowScore(education)
                }
            })
    }

    /**
     * Observe selected character
     */
    private fun observeSelectedCharacter() {
        newCharacterViewModel.selectedCharacter.observe(
            this,
            Observer { domainCharacter: DomainCharacter? ->
                if (domainCharacter != null) {
                    derivedValuesViewModel.totalHealth.value =
                        domainCharacter.characterHealthPoints
                    derivedValuesViewModel.baseHealth.value =
                        domainCharacter.characterBaseHealthPoints
                    derivedValuesViewModel.breedHealthBonus.value =
                        domainCharacter.characterBreedBonus

                    derivedValuesViewModel.ideaScore.value = domainCharacter.characterIdeaPoints
                    derivedValuesViewModel.sanityScore.value = domainCharacter.characterSanity
                    derivedValuesViewModel.luckScore.value = domainCharacter.characterLuck
                    derivedValuesViewModel.knowScore.value = domainCharacter.characterKnow
                }
            })
    }

    /**
     * Observe breeds
     */
    private fun observeBreeds() {
        //TODO("Implements breed bonus calculation")

    }

    /**
     * observe luck score
     */
    private fun observeLuckScore() {
        derivedValuesViewModel.luckScore.observe(this, Observer { luckScore ->
            if (luckScore != null && et_luckPoints.text.toString() != luckScore.toString()) {
                Log.d("DEBUG$TAG", "Luck : $luckScore")
                et_luckPoints.setText(luckScore.toString())
            }
        })
    }

    /**
     * observe total health score
     */
    private fun observeTotalHealthScore() {
        derivedValuesViewModel.totalHealth.observe(this, Observer {
            Log.d("DEBUG$TAG", "Total health : $it")
            if (it != null && et_totalHealthPoints.text.toString() != it.toString()) {
                et_totalHealthPoints.text = it.toString()
            }
        })
    }

    /**
     * observe idea score
     */
    private fun observeIdeaScore() {
        derivedValuesViewModel.ideaScore.observe(this, Observer {
            if (it != null && et_ideaPoints.text.toString() != it.toString()) {
                et_ideaPoints.setText(it.toString())
            }
        })
    }

    /**
     * observe sanity score
     */
    private fun observeSanityScore() {
        derivedValuesViewModel.sanityScore.observe(this, Observer {
            Log.d("DEBUG$TAG", "Sanity : $it")
            if (it != null && et_sanityPoints.text.toString() != it.toString()) {
                et_sanityPoints.setText(it.toString())
            }
        })
    }

    /**
     * observe base health score
     */
    private fun observeBaseHealthScore() {
        derivedValuesViewModel.baseHealth.observe(this, Observer {
            Log.d("DEBUG$TAG", "Base health : $it")
            if (it != null && et_baseHealthPoints.text.toString() != it.toString()) {
                et_baseHealthPoints.setText(it.toString())

                if(derivedValuesViewModel.breedHealthBonus.value != null){
                    val breedBonus = derivedValuesViewModel.breedHealthBonus.value
                    if(breedBonus != null){
                        val totalHealth = breedBonus + it
                        derivedValuesViewModel.totalHealth.value = totalHealth
                    }
                }else{
                    derivedValuesViewModel.totalHealth.value = it
                }
            }

        })
    }

    /**
     * observe know score
     */
    private fun observeKnowScore() {
        derivedValuesViewModel.knowScore.observe(this, Observer {
            Log.d("DEBUG$TAG", "Know score : $it")
            if (it != null && et_knowPoints.text.toString() != it.toString()) {
                et_knowPoints.setText(it.toString())
            }
        })
    }


    /**
     * Set edit texts listeners
     */
    private fun setEditTextsListeners() {
        setBaseHealthPointListener()
        setBreedBonusListener()
        setIdeaListener()
        setKnowPointsListener()
        setSanityListener()
        setLuckListener()
    }

    /**
     * set sanity edit text listener
     */
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

    /**
     * Set luck edit text listener
     */
    private fun setLuckListener() {
        et_luckPoints.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.luckEditTextHasChanged = true
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (!(s.isNullOrEmpty()) && !(s.isNullOrBlank())) {
                    try {
                        Log.d("DEBUG$TAG", "Sequence : $s")
                        if (s != null) {
                            val sequenceString = s.toString()
                            Log.d("DEBUG$TAG", "sequenceString : $sequenceString")
                            if (sequenceString != null) {
                                val sequenceInt = sequenceString.toInt()
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

    /**
     * set ideal edit text listener
     */
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

    /**
     * set know points edit text listener
     */
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


    /**
     * set breed bonus edit text listener
     */
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


    /**
     * set base health points listener
     */
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

    /**
     * set button edit onclick listener
     */
    private fun setBtnEditClickListener() {
        if (btn_edit != null) {
            btn_edit?.setOnClickListener {
                editTextsEnabling()
            }
        }
    }

    /**
     * enable or disable edit texts
     */
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