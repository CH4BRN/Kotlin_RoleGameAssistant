// File DerivedValues1Fragment.kt
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
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.fragments.adapter.DERIVED_VALUES_1_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.EditTextUtil.Companion.editTextEnabling
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.DerivedValuesViewModel
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
        setBaseHealthPoints()
        setBreedHealthBonusScore()
        setTotalHealthScore()
        setIdeaScore()
        setSanityScore()
        setLuckScore()

    }

    /**
     * Set breed health bonus score
     */
    private fun setBreedHealthBonusScore() {
        Log.d(TAG, "setBreedHealthBonusScore")
        if (et_breedHealthBonus != null) {
            derivedValuesViewModel.calculateBreedsHealthBonus(characteristicsViewModel.getCheckedBreeds())
            et_breedHealthBonus.setText(derivedValuesViewModel.breedHealthBonus!!.toString())
        }
    }

    private fun setTotalHealthScore() {
        Log.d(TAG, "setTotalHealthScore")
        Log.d(
            TAG,
            "totalHealth is null : ${et_totalHealthPoints == null}\n"
        )
        if (et_totalHealthPoints != null) {
            Log.d(TAG, "calculateTotalHealth")
            derivedValuesViewModel.calculateTotalHealth()
            et_totalHealthPoints.text = derivedValuesViewModel.totalHealth.toString()
        }

    }

    private fun setIdeaScore() {
        Log.d(TAG, "setIdeaScore")
        if (et_ideaPoints != null && !derivedValuesViewModel.ideaEditTextHasChanged) {
            derivedValuesViewModel.calculateIdeaScore(characteristicsViewModel.getIntelligence())

            et_ideaPoints.setText(derivedValuesViewModel.ideaScore.toString())

        }
    }

    private fun setSanityScore() {
        Log.d(TAG, "setSanityScore")
        if (et_sanityPoints != null && !derivedValuesViewModel.sanityEditTextHasChanged) {
            derivedValuesViewModel.calculateSanityScore(characteristicsViewModel.getPower())

            et_sanityPoints.setText(derivedValuesViewModel.sanityScore.toString())

        }
    }

    private fun setLuckScore() {
        Log.d(TAG, "setLuckScore")
        if (et_luckPoints != null && !derivedValuesViewModel.luckEditTextHasChanged) {
            derivedValuesViewModel.calculateLuckScore(characteristicsViewModel.getPower())

            et_luckPoints.setText(derivedValuesViewModel.luckScore.toString())
        }
    }

    /**
     * Set the health points score.
     */
    private fun setBaseHealthPoints() {
        if (et_baseHealthPoints != null && !derivedValuesViewModel.baseHealthEditTextHasChanged) {
            //  Gets the characteristics.
            var size =
                characteristicsViewModel.getSize()
            var constitution =
                characteristicsViewModel.getConstitution()

            if (size != null && constitution != null) {
                derivedValuesViewModel.calculateBaseHealth(listOf(size, constitution))
                et_baseHealthPoints.setText(derivedValuesViewModel.baseHealth.toString())

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
        setBaseHealthPoints()
        editTextsEnabling()

        setEditTextsListeners()
        setBtnEditClickListener()
        Log.d(TAG, "set health points")

    }

    private fun setEditTextsListeners() {
        setBaseHealthPointListener()
        setBreedBonusListener()
        setIdeaListener()
        setSanityListener()
        setLuckListener()
    }

    private fun setSanityListener() {
        et_sanityPoints.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.sanityEditTextHasChanged = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    try {
                        derivedValuesViewModel.sanityScore = s.toString().toInt()
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
        et_luckPoints.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.luckEditTextHasChanged = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    try {
                        derivedValuesViewModel.luckScore = s.toString().toInt()
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
        et_ideaPoints.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.ideaEditTextHasChanged = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    try {
                        derivedValuesViewModel.ideaScore = s.toString().toInt()
                    } catch (e: Exception) {
                        Log.e(TAG, "et_ideaPoints failed")
                        e.printStackTrace()
                        throw e
                    }
                }
            }

        })
    }


    private fun setBreedBonusListener() {
        et_breedHealthBonus.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.breedBonusEditTextHasChanged = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (!s.isNullOrBlank()) {
                    try {
                        derivedValuesViewModel.breedHealthBonus = s.toString().toInt()
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
        et_baseHealthPoints.addTextChangedListener(object : TextWatcher {
            /**
             * This method is called to notify you that, somewhere within
             * `s`, the text has been changed.
             * It is legitimate to make further changes to `s` from
             * this callback, but be careful not to get yourself into an infinite
             * loop, because any changes you make will cause this method to be
             * called again recursively.
             * (You are not told where the change took place because other
             * afterTextChanged() methods may already have made other changes
             * and invalidated the offsets.  But if you need to know here,
             * you can use [Spannable.setSpan] in [.onTextChanged]
             * to mark your place and then look up from here where the span
             * ended up.
             */
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.baseHealthEditTextHasChanged = true

            }

            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * are about to be replaced by new text with length `after`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * have just replaced old text that had length `before`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    try {
                        Log.e(TAG, "et_healthPoints $s")
                        derivedValuesViewModel.baseHealth = s.toString().toInt()
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