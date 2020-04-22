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
        setHealthPointsScore()
        setBreedHealthBonusScore()
        setTotalHealthScore()
        setIdeaScore()
        setChanceScore()

    }

    /**
     * Set breed health bonus score
     */
    private fun setBreedHealthBonusScore() {
        if (et_breedHealthBonus != null) {
            derivedValuesViewModel.calculateBreedsHealthBonus(characteristicsViewModel.getCheckedBreeds())
            et_breedHealthBonus.setText(derivedValuesViewModel.breedHealthBonus!!.toString())
        }
    }

    private fun setTotalHealthScore() {
        if (et_totalHealth != null && !derivedValuesViewModel.totalHealthEditTextHasChanged) {
            derivedValuesViewModel.calculateTotalHealth()

                et_totalHealth.setText(derivedValuesViewModel.totalHealth.toString())

        }
    }

    private fun setIdeaScore() {
        if (et_ideaPoints != null && !derivedValuesViewModel.ideaEditTextHasChanged) {
            derivedValuesViewModel.calculateIdeaScore(characteristicsViewModel.getIntelligence())

                et_ideaPoints.setText(derivedValuesViewModel.ideaScore.toString())

        }
    }

    private fun setChanceScore() {
        Log.d(TAG, "setChanceScore")
        if (et_chancePoints != null && !derivedValuesViewModel.chanceEditTextHasChanged) {
            derivedValuesViewModel.calculateChanceScore(characteristicsViewModel.getPower())

            et_chancePoints.setText(derivedValuesViewModel.chanceScore.toString())

        }
    }

    /**
     * Set the health points score.
     */
    private fun setHealthPointsScore() {
        if (et_healthPoints != null && !derivedValuesViewModel.healthEditTextHasChanged) {
            //  Gets the characteristics.
            var size =
                characteristicsViewModel.getSize()
            var constitution =
                characteristicsViewModel.getConstitution()

            if (size != null && constitution != null) {
                derivedValuesViewModel.calculateBaseHealth(listOf(size, constitution))

                    et_healthPoints.setText(derivedValuesViewModel.baseHealth.toString())

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
        setHealthPointsScore()
        editTextsEnabling()

        setEditTextsListeners()
        setBtnEditClickListener()
        Log.d(TAG, "set health points")

    }

    private fun setEditTextsListeners() {
        setHealthPointListener()
        setBreedBonusListener()
        setTotalHealthListener()
        setIdeaListener()
        setChanceListener()
    }

    private fun setChanceListener() {
        et_chancePoints.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.chanceEditTextHasChanged = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    try {
                        derivedValuesViewModel.chanceScore = s.toString().toInt()
                    } catch (e: Exception) {
                        Log.e(TAG, "et_chancePoints failed")
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

    private fun setTotalHealthListener() {
        et_totalHealth.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                derivedValuesViewModel.totalHealthEditTextHasChanged = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    try {
                        derivedValuesViewModel.totalHealth = s.toString().toInt()
                    } catch (e: Exception) {
                        Log.e(TAG, "et_totalHealth failed")
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
            }

        })
    }

    private fun setHealthPointListener() {
        et_healthPoints.addTextChangedListener(object : TextWatcher {
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
                derivedValuesViewModel.healthEditTextHasChanged = true
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
                        derivedValuesViewModel.baseHealth = s.toString().toInt()
                    } catch (e: Exception) {
                        Log.e(TAG, "et_healthPoints failed")
                        e.printStackTrace()
                        throw e
                    }
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