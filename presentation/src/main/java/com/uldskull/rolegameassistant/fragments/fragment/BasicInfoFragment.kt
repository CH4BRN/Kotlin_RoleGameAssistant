// File BasicInfoFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NEW_RACE_ACTIVITY
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.BASIC_INFO_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.viewmodels.CharacteristicViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_basic_info.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "BasicInfoFragment" :
 *   Fragment to fill information concerning basic info.
 **/
class BasicInfoFragment(activity: Activity) : CustomFragment(activity) {

    /** Fragment Lifecycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newCharacterViewModel = getViewModel()
        newCharacterViewModel.displayDices()
        return initializeView(inflater, container)
    }

    /**
     * Character Gender
     */
    private var characterGender: String = ""
    /**
     * Character age.
     */
    private var characterAge: String = ""
    /**
     * Character name.
     */
    private var characterName: String = ""
    /**
     * ViewModel for new character
     */
    private lateinit var newCharacterViewModel: NewCharacterViewModel

    /** Initialize the view corresponding to this fragment class    **/
    override fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_basic_info, container, false
        )
        return initialRootView
    }

    /** Fragment Lifecycle  **/
    override fun onResume() {
        super.onResume()
        Log.d("BasicInfoFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = BASIC_INFO_FRAGMENT_POSITION
        Log.d("BasicInfoFragment_2", NewCharacterActivity.progression.value.toString())
    }

    /** Fragment Lifecycle  **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonAddRace()
        setFocusListeners()
        var domainCharacteristic: DomainCharacteristic = DomainCharacteristic(
            characteristicId = null,
            characteristicName = CharacteristicsName.APPEARANCE.characteristicName
        )
        val characteristicViewModel: CharacteristicViewModel = getViewModel()
        var insertResult = characteristicViewModel.saveOne(domainCharacteristic)
        Log.d("BasicInfoFragment result = ", insertResult.toString())

        val characteristicObserver = Observer<List<DomainCharacteristic>> { newCharacteristics ->
            newCharacteristics.forEach {
                Log.d("BasicInfoFragment", it.characteristicName)
            }
        }

        characteristicViewModel.result?.observe(this, characteristicObserver)

        val deleteResult = characteristicViewModel.deleteAll()

        setEditTextListeners()
    }

    /** Set edit text listeners */
    private fun setEditTextListeners() {
        setNameTextChangedListener()
        setAgeTextChangedListener()
    }

    /**
     * Set Age EditText Text changed listener.
     */
    private fun setAgeTextChangedListener() {
        if (et_age != null) {
            et_age.addTextChangedListener(object : TextWatcher {
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
                    characterAge = s.toString()
                }

                /**
                 * This method is called to notify you that, within `s`,
                 * the `count` characters beginning at `start`
                 * are about to be replaced by new text with length `after`.
                 * It is an error to attempt to make changes to `s` from
                 * this callback.
                 */
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }


                /**
                 * This method is called to notify you that, within `s`,
                 * the `count` characters beginning at `start`
                 * have just replaced old text that had length `before`.
                 * It is an error to attempt to make changes to `s` from
                 * this callback.
                 */
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

            })
        }
    }

    /**
     * Set Name EditText Text changed listener.
     */
    private fun setNameTextChangedListener() {
        if (et_name != null) {
            et_name.addTextChangedListener(object : TextWatcher {
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
                    characterName = s.toString()
                }

                /**
                 * This method is called to notify you that, within `s`,
                 * the `count` characters beginning at `start`
                 * are about to be replaced by new text with length `after`.
                 * It is an error to attempt to make changes to `s` from
                 * this callback.
                 */
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                /**
                 * This method is called to notify you that, within `s`,
                 * the `count` characters beginning at `start`
                 * have just replaced old text that had length `before`.
                 * It is an error to attempt to make changes to `s` from
                 * this callback.
                 */
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

            })
        }
    }

    /**
     * Set the add race button.
     */
    private fun setButtonAddRace() {
        if (btn_addRace != null) {
            btn_addRace?.setOnClickListener {
                val intent = Intent(activity, NEW_RACE_ACTIVITY)
                startActivityForResult(intent, REQUEST_CODE_BASIC_INFO_NEW_RACE)
            }
        }
    }

    /** Set focus listeners **/
    private fun setFocusListeners() {
        setNameFocusListener()
        setAgeFocusListener()
        setGenderFocusListener()
    }

    /**
     * Set gender focus listener
     */
    private fun setGenderFocusListener() {
        Log.d("BASIC_INFO", "setGenderFocusListener $characterGender")
        et_gender.setOnFocusChangeListener { _, _
            ->
            newCharacterViewModel.saveGender(characterGender)
        }
    }

    /** Set name focus listener **/
    private fun setNameFocusListener() {
        Log.d("BASIC_INFO", "setNameFocusListener $characterName")
        et_name.setOnFocusChangeListener { _, _
            ->
            newCharacterViewModel.saveName(characterName)
        }
    }

    /**
     * set age focus listener.
     */
    private fun setAgeFocusListener() {
        Log.d("BASIC_INFO", "setAgeFocusListener $characterAge")
        et_age.setOnFocusChangeListener { _, _
            ->
            newCharacterViewModel.saveAge(characterAge)
        }
    }

    companion object : CustomCompanion() {
        /**
         * Get a new instance of class BasicInfoFragment
         */
        @JvmStatic
        override fun newInstance(activity: Activity): BasicInfoFragment {
            val fragment =
                BasicInfoFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, BASIC_INFO_FRAGMENT_POSITION)
            fragment.arguments = args

            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_picture,
                PictureFragment.newInstance(activity)
            )
            return fragment
        }
    }
}