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
import com.uldskull.rolegameassistant.activities.NEW_BREED_ACTIVITY
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.BASIC_INFO_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_BASIC_INFO_NEW_BREED
import com.uldskull.rolegameassistant.fragments.fragment.basicinfo.breed.BreedsRecyclerViewFragment
import com.uldskull.rolegameassistant.viewmodels.BreedsViewModel
import com.uldskull.rolegameassistant.viewmodels.CharacteristicViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
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
    private val breedsViewModel: BreedsViewModel by sharedViewModel()
    private val characteristicViewModel: CharacteristicViewModel by sharedViewModel()

    private fun testInsert() {
        thread(start = true) {
            //  BREED
            Log.d("testInsert", "BasicInfoFragment START")
            var breedId: Long? = breedsViewModel.testInsert()
            var breed = breedsViewModel.findBreedWithId(breedId)

            Log.d("testInsert", "BasicInfoFragment - Breed name : ${breed?.breedName}")

            //  CHARACTERISTIC
            var characteristicId: Long? = characteristicViewModel.testInsertOne(breedId)
            var characteristicIds: List<Long>? = characteristicViewModel.testInsertMultiple(breedId)

            var breedCharacteristic =
                characteristicViewModel.findBreedCharacteristicWithId(characteristicId)

            Log.d(
                "testInsert",
                "BasicInfoFragment - Characteristic name : ${breedCharacteristic?.characteristicName}"
            )
            Log.d(
                "testInsert",
                "BasicInfoFragment - Characteristic breedId : ${breedCharacteristic?.characteristicBreedId}"
            )

            //  BREED WITH CHILDREN


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
        setButtonAddBreed()
        setEditTextListeners()

        // testInsert()
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
     * Set the add breed button.
     */
    private fun setButtonAddBreed() {
        if (btn_addBreed != null) {
            btn_addBreed?.setOnClickListener {
                val intent = Intent(activity, NEW_BREED_ACTIVITY)
                startActivityForResult(
                    intent,
                    REQUEST_CODE_BASIC_INFO_NEW_BREED
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
                R.id.container_breed,
                BreedsRecyclerViewFragment.newInstance(
                    activity
                )
            )
            return fragment
        }
    }
}