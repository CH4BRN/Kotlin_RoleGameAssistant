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
import com.uldskull.rolegameassistant.fragments.fragment.breed.BreedsRecyclerViewFragment
import com.uldskull.rolegameassistant.viewmodels.BreedsViewModel
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_basic_info.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


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
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()



    /**
     * Fragment Lifecycle
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
     * Initialize the view corresponding to this fragment class
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_basic_info, container, false
        )
        return initialRootView
    }

    /**
     * Fragment Lifecycle
     */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Progression before : " + NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = BASIC_INFO_FRAGMENT_POSITION
        Log.d(TAG, "Progression after : " + NewCharacterActivity.progression.value.toString())
    }

    /**
     * Fragment Lifecycle
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        setButtonAddBreed()
        setEditTextListeners()

        // testInsert()
    }


    /**
     * Set edit text listeners
     */
    private fun setEditTextListeners() {
        Log.d(TAG, "setEditTextListeners")
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
        Log.d(TAG, "setGenderTextChangedListener")
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
        Log.d(TAG, "setAgeTextChangedListener")
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
        Log.d(TAG, "setNameTextCHangedListener")
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
        Log.d(TAG, "setBiographyTextChangedListener")
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
        Log.d(TAG, "setHeightTextChangedListener")
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
                Log.d(TAG, "afterTextChanged")
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
        Log.d(TAG, "setButtonAddBreed")
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
        private const val TAG = "BasicInfoFragment"

        /**
         * Get a new instance of class BasicInfoFragment
         */
        @JvmStatic
        override fun newInstance(activity: Activity): BasicInfoFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                BasicInfoFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, BASIC_INFO_FRAGMENT_POSITION)
            fragment.arguments = args

            (activity as NewCharacterActivity).replaceFragment(
                R.id.basicInfo_container_picture,
                PictureFragment.newInstance(
                    activity
                )
            )
            activity.replaceFragment(
                R.id.basicInfo_container_breed,
                BreedsRecyclerViewFragment.newInstance(
                    activity
                )
            )
            return fragment
        }
    }
}