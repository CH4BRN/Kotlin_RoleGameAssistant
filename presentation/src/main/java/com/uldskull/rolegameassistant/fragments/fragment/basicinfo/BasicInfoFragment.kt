// File BasicInfoFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.basicinfo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NEW_RACE_ACTIVITY
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.BASIC_INFO_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_BASIC_INFO_NEW_RACE
import com.uldskull.rolegameassistant.viewmodels.CharacteristicViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.RacesViewModel
import kotlinx.android.synthetic.main.fragment_basic_info.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.concurrent.thread


/**
 *   Class "BasicInfoFragment" :
 *   Fragment to fill information concerning basic info.
 **/
class BasicInfoFragment(activity: Activity) : CustomFragment(activity) {
    /**
     * ViewModel for new character
     */
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()
    private val raceViewModel: RacesViewModel by sharedViewModel()
    private val characteristicViewModel: CharacteristicViewModel by sharedViewModel()

    private fun testInsert() {
        thread(start = true) {
            //  RACE
            Log.d("testInsert", "BasicInfoFragment START")
            var raceId: Long? = raceViewModel.testInsert()
            var race = raceViewModel.findRaceWithId(raceId)

            Log.d("testInsert", "BasicInfoFragment - Race name : ${race?.raceName}")

            //  CHARACTERISTIC
            var characteristicId: Long? = characteristicViewModel.testInsertOne(raceId)
            var characteristicIds: List<Long>? = characteristicViewModel.testInsertMultiple(raceId)

            var raceCharacteristic =
                characteristicViewModel.findRaceCharacteristicWithId(characteristicId)

            Log.d(
                "testInsert",
                "BasicInfoFragment - Characteristic name : ${raceCharacteristic?.characteristicName}"
            )
            Log.d(
                "testInsert",
                "BasicInfoFragment - Characteristic raceId : ${raceCharacteristic?.characteristicRaceId}"
            )

            //  RACE WITH CHILDREN
            var result = raceViewModel.findRaceWithCharacteristics()

            Log.d("testInsert", "BasicInfoFragment END")
        }
    }

    /**
     * Fragment Lifecycle
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newCharacterViewModel.displayDices()


        return initializeView(inflater, container)
    }


    /**
     * Initialize the view corresponding to this fragment class
     */
    override fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_basic_info, container, false
        )
        return initialRootView
    }

    /**
     * Fragment Lifecycle
     */
    override fun onResume() {
        super.onResume()
        Log.d("BasicInfoFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = BASIC_INFO_FRAGMENT_POSITION
        Log.d("BasicInfoFragment_2", NewCharacterActivity.progression.value.toString())
    }

    /**
     * Fragment Lifecycle
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonAddRace()
        setEditTextListeners()

        testInsert()
    }


    /**
     * Set edit text listeners
     */
    private fun setEditTextListeners() {
        setNameTextChangedListener()
        setAgeTextChangedListener()
        setGenderTextChangedListener()
        setBiographyTextChangedListener()
        setHeightTextChangedListener()

    }

    /**
     * Set gender text changed listener
     */
    private fun setGenderTextChangedListener() {
        et_gender?.addTextChangedListener(object : TextWatcher {
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
                newCharacterViewModel.characterGender = s?.toString()
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

    /**
     * Set Age EditText Text changed listener.
     */
    private fun setAgeTextChangedListener() {
        et_age?.addTextChangedListener(object : TextWatcher {
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
                newCharacterViewModel.saveAge(s.toString())
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

    /**
     * Set Name EditText Text changed listener.
     */
    private fun setNameTextChangedListener() {
        et_name?.addTextChangedListener(object : TextWatcher {
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
                newCharacterViewModel.characterName = s.toString()
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

    /**
     * Set Biography EditText text changed listener.
     */
    private fun setBiographyTextChangedListener() {
        et_biography?.addTextChangedListener(object : TextWatcher {
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
                newCharacterViewModel.characterBiography = s.toString()
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

            }

        })
    }

    private fun setHeightTextChangedListener() {
        et_height?.addTextChangedListener(object : TextWatcher {
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
                newCharacterViewModel.saveHeight(s.toString())
            }

            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * are about to be replaced by new text with length `after`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * have just replaced old text that had length `before`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

    /**
     * Set the add race button.
     */
    private fun setButtonAddRace() {
        if (btn_addRace != null) {
            btn_addRace?.setOnClickListener {
                val intent = Intent(activity, NEW_RACE_ACTIVITY)
                startActivityForResult(
                    intent,
                    REQUEST_CODE_BASIC_INFO_NEW_RACE
                )
            }
        }
    }


    companion object : CustomCompanion() {
        /**
         * Get a new instance of class BasicInfoFragment
         */
        @JvmStatic
        override fun newInstance(activity: Activity): BasicInfoFragment {
            val fragment =
                BasicInfoFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, BASIC_INFO_FRAGMENT_POSITION)
            fragment.arguments = args

            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_picture,
                PictureFragment.newInstance(
                    activity
                )
            )
            activity.replaceFragment(
                R.id.container_race,
                RacesRecyclerViewFragment.newInstance(
                    activity
                )
            )
            return fragment
        }
    }
}