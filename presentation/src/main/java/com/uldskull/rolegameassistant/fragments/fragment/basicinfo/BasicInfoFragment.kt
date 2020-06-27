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
import com.uldskull.rolegameassistant.activities.core.NEW_BREED_ACTIVITY
import com.uldskull.rolegameassistant.activities.core.AddEndFragmentAndUpdateAdapter
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomFragment
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomTextWatcher
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_BASIC_INFO_NEW_BREED
import com.uldskull.rolegameassistant.fragments.fragment.breed.BreedsRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.BASIC_INFO_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.viewmodels.BasicInfoViewModel
import com.uldskull.rolegameassistant.viewmodels.ProgressionBarViewModel
import com.uldskull.rolegameassistant.viewmodels.breeds.DisplayedBreedsViewModel
import com.uldskull.rolegameassistant.viewmodels.character.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationsViewModel
import kotlinx.android.synthetic.main.fragment_basic_info.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "BasicInfoFragment" :
 *   Fragment to fill information concerning basic info.
 **/
class BasicInfoFragment : CustomFragment() {

    /**
     * Button to add breed.
     */
    private var buttonAddBreed: ImageButton? = null

    /**
     * ViewModel for basic info
     */
    private val basicInfoViewModel: BasicInfoViewModel by sharedViewModel()

    /**
     * ViewModel for new character
     */
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /**
     * ViewModel for breeds.
     */
    private val displayedBreedsViewModel: DisplayedBreedsViewModel by sharedViewModel()

    private val occupationsViewModel: OccupationsViewModel by sharedViewModel()

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
        Log.d("DEBUG$TAG", "activity is null ? ${activity == null}")
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
     * Load breeds RecyclerView fragment.
     */
    private fun loadBreedsRecyclerViewFragment() {
        if (activity != null) {
            val transaction = childFragmentManager.beginTransaction()

            val fragment = BreedsRecyclerViewFragment.newInstance(
                activity!!
            )

            Log.d("DEBUG$TAG", "breeds size then : ${basicInfoViewModel.displayedBreeds?.size}")

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
        buttonAddBreed = view?.findViewById(R.id.btn_addBreed)
    }

    /**
     * Deserialize edit texts
     */
    private fun deserializeEditTexts() {
        editTextCharacterName = view?.findViewById(R.id.et_characterName)
        editTextCharacterAge = view?.findViewById(R.id.et_characterAge)
        editTextCharacterGender = view?.findViewById(R.id.et_CharacterGender)
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
        Log.d("DEBUG$TAG", "startObservation")

        observeName()
        observeAge()
        observeGender()
        observeBiography()
        observeHeight()
        observeWeight()
        observeSelectedOccupation()
        observeSelectedCharacter()
    }

    /**
     * Observe selected character.
     */
    private fun observeSelectedCharacter() {
        newCharacterViewModel.selectedCharacter.observe(this, Observer { domainCharacter ->
            Log.d("DEBUG$TAG", "Selected character is null : ${domainCharacter == null}")



            if (domainCharacter != null) {
                var areCharacteristicsRolled = true
                val characteristicList = listOf(
                    domainCharacter.characterAppearance,
                    domainCharacter.characterConstitution,
                    domainCharacter.characterDexterity,
                    domainCharacter.characterEducation,
                    domainCharacter.characterIntelligence,
                    domainCharacter.characterPower,
                    domainCharacter.characterSize,
                    domainCharacter.characterStrength
                )
                characteristicList.forEach {
                    if (it?.characteristicRoll == 0) {
                        Log.d("DEBUG$TAG", "areCharacteristicRolled = $areCharacteristicsRolled")
                        areCharacteristicsRolled = false
                        Log.d("DEBUG$TAG", "areCharacteristicRolled = $areCharacteristicsRolled")
                    }
                }

                if (areCharacteristicsRolled) {
                    (activity as AddEndFragmentAndUpdateAdapter).addEndFragmentsAndUpdateAdapter()
                }
            }


        })
    }

    /**
     * Observe selected occupation
     */
    private fun observeSelectedOccupation() {
        occupationsViewModel.selectedOccupation?.observe(this, Observer { domainOccupation ->
            Log.d("DEBUG$TAG", "Occupation : ${domainOccupation.occupationName}")
        })
    }

    /**
     * Observe weight
     */
    private fun observeWeight() {
        newCharacterViewModel.characterWeight.observe(this, Observer { weight ->
            if (weight != null) {
                if (editTextCharacterWeight?.text.toString() != weight.toString()) {
                    editTextCharacterWeight?.setText(weight.toString())
                }
            }
        })
    }

    /**
     * Observe height
     */
    private fun observeHeight() {
        newCharacterViewModel.characterHeight.observe(this, Observer { height ->
            if (height != null) {
                if (editTextCharacterHeight?.text.toString() != height.toString()) {
                    editTextCharacterHeight?.setText(height.toString())
                }
            }

        })
    }

    /**
     * Observe biography
     */
    private fun observeBiography() {
        newCharacterViewModel.characterBiography.observe(this, Observer { biography ->
            if (biography != null) {
                if (editTextCharacterBiography?.text.toString() != biography.toString()) {
                    editTextCharacterBiography?.setText(biography)
                }
            }

        })
    }

    /**
     * Observe gender
     */
    private fun observeGender() {
        newCharacterViewModel.characterGender.observe(this, Observer { gender ->
            if (gender != null) {
                if (editTextCharacterGender?.text.toString() != gender.toString()) {
                    editTextCharacterGender?.setText(gender)
                }
            }

        })
    }

    /**
     * Observe age
     */
    private fun observeAge() {
        newCharacterViewModel.characterAge.observe(this, Observer { age ->
            if (age != null) {
                if (editTextCharacterAge?.text.toString() != age.toString()) {
                    editTextCharacterAge?.setText(age.toString())
                }
            }

        })
    }

    /**
     * observe name
     */
    private fun observeName() {
        newCharacterViewModel.characterName.observe(this, Observer { name ->
            if (name != null) {
                if (editTextCharacterName?.text.toString() != name.toString()) {
                    editTextCharacterName?.setText(name)
                }
            }
        })
    }

    /**
     * Load children fragments.
     */
    override fun loadChildrenFragments() {
        //loadPictureFragment()
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
                if (!s.isNullOrEmpty() || !s.isNullOrBlank()) {
                    newCharacterViewModel.characterWeight.value = s.toString().toInt()
                }

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
                newCharacterViewModel.characterGender.value = gender?.toString()
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
                if (newCharacterViewModel.characterName.value.toString() != s.toString()) {
                    newCharacterViewModel.characterName.value = s.toString()
                }
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
                newCharacterViewModel.characterBiography.value = s.toString()
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
                val intent = Intent(
                    activity,
                    NEW_BREED_ACTIVITY
                )
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