// File BasicInfoFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.basicinfo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NEW_BREED_ACTIVITY
import com.uldskull.rolegameassistant.fragments.fragment.*
import com.uldskull.rolegameassistant.fragments.fragment.breed.BreedsRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.BASIC_INFO_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.ProgressionBarViewModel
import com.uldskull.rolegameassistant.viewmodels.breeds.DisplayedBreedsViewModel
import kotlinx.android.synthetic.main.fragment_basic_info.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "BasicInfoFragment" :
 *   Fragment to fill information concerning basic info.
 **/
class BasicInfoFragment() : CustomFragment() {

    private var displayedBreeds: MutableList<DomainDisplayedBreed?>? = mutableListOf()
    set(value) {
        Log.d("DEBUG$TAG", "breeds list size = ${value?.size}")
        field = value
    }

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
    private val displayedBreedsViewModel: DisplayedBreedsViewModel by sharedViewModel()

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
    private var editTextCharacterBiography: EditText? = null

    /**
     * Edit text for height
     */
    private var editTextCharacterHeight: EditText? = null

    /**
     * EditText for weight
     */
    private var editTextCharacterWeight: EditText? = null

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
     * Fragment Lifecycle
     */
    override fun onResume() {
        super.onResume()
        progressionBarViewModel.progression.value = BASIC_INFO_FRAGMENT_POSITION
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
        startObservation()
        loadChildrenFragments()

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

            var fragment =  BreedsRecyclerViewFragment.newInstance(
                activity!!
            )

            Log.d("DEBUG$TAG", "breeds size then : ${this.displayedBreeds?.size}")

            displayedBreeds?.forEach { newBreed ->
                if(newBreed != null){
                    displayedBreedsViewModel?.observedDisplayedBreeds?.value?.find { it.breedId == newBreed?.breedId }?.breedChecked = newBreed?.breedChecked
                }
            }


            transaction.replace(
                R.id.basicInfo_container_breed,
               fragment
            ).commit()
        }
    }

    /**
     * Deserialize fragment's widgets.
     */
    private fun deserializeWidgets() {
        deserializeEditTexts()
        buttonAddBreed = view?.findViewById<ImageButton>(R.id.btn_addBreed)
    }

    /**
     * Deserialize edit texts
     */
    private fun deserializeEditTexts() {
        editTextCharacterName = view?.findViewById(R.id.et_characterName)
        editTextCharacterAge = view?.findViewById(R.id.et_characterAge)
        editTextCharacterGender = view?.findViewById<EditText>(R.id.et_CharacterGender)
        editTextCharacterBiography = view?.findViewById(R.id.et_CharacterBiography)
        editTextCharacterHeight = view?.findViewById(R.id.et_CharacterHeight)
        editTextCharacterWeight = view?.findViewById(R.id.et_CharacterWeight)
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
        newCharacterViewModel?.selectedCharacter?.observe(
            this,
            Observer { character: DomainCharacter? ->
                kotlin.run {
                    Log.d("DEBUG $TAG", "${newCharacterViewModel?.selectedCharacter.value}")

                    newCharacterViewModel?.currentCharacter = character

                    if (character?.characterName != null) {
                        editTextCharacterName?.setText(character?.characterName)
                    }

                    if (character?.characterAge != null) {
                        editTextCharacterAge?.setText(character?.characterAge?.toString())
                    }

                    if (character?.characterGender != null) {
                        editTextCharacterGender?.setText(character?.characterGender)
                    }

                    if (character?.characterBiography != null) {
                        editTextCharacterBiography?.setText(character?.characterBiography)
                    }

                    if (character?.characterHeight != null) {
                        editTextCharacterHeight?.setText(character?.characterHeight?.toString())
                    }

                    if (character?.characterWeight != null) {
                        editTextCharacterWeight?.setText(character?.characterWeight?.toString())
                    }


                }
            })
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
        setWeightTextChangedListener()

    }

    /**
     * Set weight EditText text changed listener
     */
    private fun setWeightTextChangedListener() {
        Log.d(TAG, "setWeightTextChangedListener")
        editTextCharacterWeight?.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                newCharacterViewModel?.characterWeight?.value = s.toString().toInt()
            }
        })
    }

    /**
     * Set gender text changed listener
     */
    private fun setGenderTextChangedListener() {
        Log.d(TAG, "setGenderTextChangedListener")
        editTextCharacterGender?.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(gender: Editable?) {
                newCharacterViewModel.characterGender = gender?.toString()
            }
        })
    }

    /**
     * Set Age EditText Text changed listener.
     */
    private fun setAgeTextChangedListener() {
        Log.d(TAG, "setAgeTextChangedListener")
        editTextCharacterAge?.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                newCharacterViewModel.saveAge(s.toString())
            }
        })
    }

    /**
     * Set Name EditText Text changed listener.
     */
    private fun setNameTextChangedListener() {
        Log.d(TAG, "setNameTextCHangedListener")
        et_characterName?.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                Log.d("DEBUG$TAG", "name : ${s.toString()}")
                newCharacterViewModel.characterName.value = s.toString()
            }
        })
    }

    /**
     * Set Biography EditText text changed listener.
     */
    private fun setBiographyTextChangedListener() {
        Log.d(TAG, "setBiographyTextChangedListener")
        editTextCharacterBiography?.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                newCharacterViewModel.characterBiography = s.toString()
            }
        })
    }

    /**
     * Set height EditText text changed listener.
     */
    private fun setHeightTextChangedListener() {
        Log.d(TAG, "setHeightTextChangedListener")
        et_CharacterHeight?.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                Log.d(TAG, "afterTextChanged")
                newCharacterViewModel.saveHeight(s.toString())
            }
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