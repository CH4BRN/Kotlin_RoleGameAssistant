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
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NEW_BREED_ACTIVITY
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_BASIC_INFO_NEW_BREED
import com.uldskull.rolegameassistant.fragments.fragment.breed.BreedsRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.BASIC_INFO_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.ProgressionBarViewModel
import com.uldskull.rolegameassistant.viewmodels.breeds.BreedsViewModel
import kotlinx.android.synthetic.main.fragment_basic_info.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "BasicInfoFragment" :
 *   Fragment to fill information concerning basic info.
 **/
class BasicInfoFragment() : CustomFragment() {
    /**
     * Button to add breed.
     */
    private var buttonAddBreed: ImageButton? = null

    /**
     * ViewModel for new character
     */
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /**
     * ViewModel for breeds.
     */
    private val breedsViewModel: BreedsViewModel by sharedViewModel()

    /**
     * ViewModel for characteristics
     */
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()

    /**
     * ViewModel for progression bar.
     */
    private val progressionBarViewModel: ProgressionBarViewModel by sharedViewModel()

    /**
     * edit text for character name
     */
    private var editTextCharacterName: EditText? = null

    /**
     * Edit text for character age.
     */
    private var editTextCharacterAge: EditText? = null

    /**
     * Edit text for gender.
     */
    private var editTextCharacterGender: EditText? = null

    /**
     * Edit text for biography
     */
    private var editTextBiography: EditText? = null

    /**
     * Edit text for height
     */
    private var editTextHeight: EditText? = null

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
        Log.d(TAG, "Progression before : " + progressionBarViewModel.progression.value.toString())
        progressionBarViewModel.progression.value = BASIC_INFO_FRAGMENT_POSITION
        Log.d(TAG, "Progression after : " + progressionBarViewModel.progression.value.toString())
    }

    /**
     * Load picture fragment
     */
    private fun loadPictureFragment() {
        Log.d(TAG, "Load picture fragment")
        if (activity != null) {
            Log.d("DEBUG $TAG", "activity is not null}")
            try {
                val transaction = childFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.basicInfo_container_picture,
                    PictureFragment.newInstance(
                        activity!!
                    )
                ).commit()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    /**
     * Load breeds RecyclerView fragment.
     */
    private fun loadBreedsRecyclerViewFragment() {
        if (activity != null) {
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(
                R.id.basicInfo_container_breed,
                BreedsRecyclerViewFragment.newInstance(
                    activity!!
                )
            ).commit()
        }
    }

    /**
     * Deserialize fragment's widgets.
     */
    private fun deserializeWidgets() {
        editTextCharacterName = view?.findViewById(R.id.et_name)
        editTextCharacterAge = view?.findViewById(R.id.et_age)
        editTextCharacterGender = view?.findViewById<EditText>(R.id.et_gender)
        editTextBiography = view?.findViewById(R.id.et_biography)
        buttonAddBreed = view?.findViewById<ImageButton>(R.id.btn_addBreed)
        editTextHeight = view?.findViewById(R.id.et_height)

    }

    /**
     * Fragment Lifecycle
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        activity = requireActivity()
        Log.d("DEBUG $TAG", "activity is null ? ${activity == null}")
        deserializeWidgets()
        setButtonAddBreed()
        initializeListeners()
        loadChildrenFragments()
        startObservation()
    }

    /**
     * Initialize miscellaneous listeners.
     */
    private fun initializeListeners() {
        setEditTextListeners()
    }

    /**
     * Start values observation.
     */
    private fun startObservation() {
        Log.d("DEBUG $TAG", "startObservation")
       if(newCharacterViewModel?.selectedCharacter != null){
           var character = newCharacterViewModel?.selectedCharacter
           newCharacterViewModel?.currentCharacter = character
           Log.d("DEBUG $TAG","${newCharacterViewModel?.selectedCharacter}" )
           editTextCharacterName?.setText(character?.characterName)
       }

    }


    /**
     * Load children fragments.
     */
    private fun loadChildrenFragments() {
        loadPictureFragment()
        loadBreedsRecyclerViewFragment()
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
        editTextCharacterGender?.addTextChangedListener(object : TextWatcher {
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
            override fun afterTextChanged(gender: Editable?) {
                newCharacterViewModel.characterGender = gender?.toString()
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
        editTextCharacterAge?.addTextChangedListener(object : TextWatcher {
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
                Log.d("DEBUG$TAG", "name : ${s.toString()}")
                newCharacterViewModel.characterName.value = s.toString()
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
        editTextBiography?.addTextChangedListener(object : TextWatcher {
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

    /**
     * Set height EditText text changed listener.
     */
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
        if (buttonAddBreed != null) {
            buttonAddBreed?.setOnClickListener {
                val intent = Intent(activity, NEW_BREED_ACTIVITY)
                startActivityForResult(
                    intent,
                    REQUEST_CODE_BASIC_INFO_NEW_BREED
                )
            }
        }
    }

    /**
     * Companion object.
     */
    companion object : CustomCompanion() {
        /**
         * Fragments tag.
         */
        private const val TAG = "BasicInfoFragment"

        /**
         * Get a new instance of class BasicInfoFragment
         */
        @JvmStatic
        override fun newInstance(activity: Activity): BasicInfoFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                BasicInfoFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, BASIC_INFO_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }
}