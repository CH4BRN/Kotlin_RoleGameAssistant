// File AbilitiesRecyclerViewFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.characteristics

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.character.CharacterActivity
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.characteristics.adapters.CharacteristicsAdapter
import com.uldskull.rolegameassistant.fragments.fragment.characteristics.adapters.CharacteristicsDisabledAdapter
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.CHARACTERISTICS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.DerivedValuesViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_characteristics_recyclerview.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "AbilitiesRecyclerViewFragment" :
 *   Manage abilities's RecyclerView fragment.
 **/
class CharacteristicsRecyclerViewFragment :
    CustomRecyclerViewFragment() {

    private var editable: Boolean = false

    /** ViewModel for characteristics **/
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()

    private val derivedValuesViewModel: DerivedValuesViewModel by sharedViewModel()

    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /** Adapter for abilities recycler view **/
    private var characteristicsAdapter: CharacteristicsAdapter? = null

    /** Adapter to display disabled edit texts **/
    private var characteristicsDisabledAdapter: CharacteristicsDisabledAdapter? = null

    /** Recycler View for abilities **/
    private var characteristicsRecyclerView: RecyclerView? = null


    /** Fragment life-cycle **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    /** Initialize the view **/
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_characteristics_recyclerview, container, false
        )
        return initialRootView
    }


    /** Fragment life-cycle **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        setButtonUsePoints()
        setButtonRoll()


    }

    /**
     * Set the button that launch a dice roll for each ability
     */
    private fun setButtonRoll() {
        if (btn_roll != null) {
            btn_roll.setOnClickListener {
                Log.d(TAG, "Roll")
                populateRandomRollCharacteristics()
                setEditTextChangedToFalse()
                if (!editable) {
                    characteristicsRecyclerView?.adapter = characteristicsDisabledAdapter

                } else {
                    characteristicsRecyclerView?.adapter = characteristicsAdapter
                }
                (activity as CharacterActivity).addEndFragments()
                Log.d("DEBUG$TAG", "${(activity as CharacterActivity).fragmentAdapter?.itemCount}")
            }
        }

    }

    private fun setEditTextChangedToFalse() {
        derivedValuesViewModel.breedBonusEditTextHasChanged = false
        derivedValuesViewModel.sanityEditTextHasChanged = false
        derivedValuesViewModel.baseHealthEditTextHasChanged = false
        derivedValuesViewModel.ideaEditTextHasChanged = false
    }

    /**
     * Set the button that allow the user to use his points
     * manually.
     */
    private fun setButtonUsePoints() {
        if (btn_use_point != null) {
            btn_use_point.setOnClickListener {
                Log.d(TAG, "Use points")
                if (editable) {
                    characteristicsRecyclerView?.adapter = characteristicsDisabledAdapter
                    editable = false
                } else {
                    characteristicsRecyclerView?.adapter = characteristicsAdapter
                    editable = true
                }

                setRecyclerViewLayoutManager()
            }
        }
    }

    /** Initialize recycler view    **/
    override fun initializeRecyclerView() {
        characteristicsRecyclerView = activity?.findViewById(R.id.recycler_view_abilities)
                as RecyclerView?
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {


        observeCharacteristics()
        observeMutableCharacteristics()
        observeSelectedCharacter()
    }

    private fun observeSelectedCharacter() {
        newCharacterViewModel.selectedCharacter.observe(this, Observer { domainCharacter ->
            if (domainCharacter != null) {
                Log.d(
                    "DEBUG$TAG", "Selected character is :${domainCharacter.characterName}\n" +
                            "Characteristics : \n" + "\tdomainCharacter.characterEducation : ${domainCharacter.characterEducation}\n"
                )

                var characteristicList = listOf(
                    domainCharacter.characterAppearance,
                    domainCharacter.characterConstitution,
                    domainCharacter.characterDexterity,
                    domainCharacter.characterEducation,
                    domainCharacter.characterIntelligence,
                    domainCharacter.characterPower,
                    domainCharacter.characterSize,
                    domainCharacter.characterStrength
                )


                var test = true
                characteristicList.forEach {
                    if ((it?.characteristicTotal == null)
                        ||
                        (it.characteristicRoll == null)
                        ||
                        (it.characteristicBonus == null)
                    ) {
                        test = false
                    }
                }


                if(test){
                    if (characteristicsViewModel.observedMutableCharacteristics.value == null) {
                        characteristicsViewModel.observedMutableCharacteristics.value =
                            characteristicList.toMutableList()
                    }

                    if (characteristicsViewModel.displayedCharacteristics?.value == null) {
                        characteristicsViewModel.displayedCharacteristics?.value =
                            characteristicList
                    }


                    characteristicsAdapter?.setCharacteristics(characteristicList)
                    characteristicsDisabledAdapter?.setCharacteristics(characteristicList)
                    characteristicsRecyclerView?.adapter = characteristicsDisabledAdapter

                    (activity as CharacterActivity).addEndFragments()
                    Log.d("DEBUG$TAG", "${(activity as CharacterActivity).fragmentAdapter?.itemCount}")
                }


            }
        })
    }

    private fun observeCharacteristics() {
        characteristicsViewModel.observedRepositoryCharacteristics?.observe(
            this, Observer {
                Log.d("DEBUG$TAG", "observedCharacteristics changed size ${it.size}")


                characteristicsViewModel.observedMutableCharacteristics.value =
                    it as MutableList<DomainRollsCharacteristic?>

                var displayedCharacteristics =
                    characteristicsViewModel.displayedCharacteristics?.value
                if (displayedCharacteristics?.size == 0) {
                    characteristicsViewModel.displayedCharacteristics?.value =
                        it as MutableList<DomainRollsCharacteristic>
                }

            }
        )
    }

    private fun observeMutableCharacteristics() {
        characteristicsViewModel.observedMutableCharacteristics.observe(
            this,
            Observer { rollsCharacteristics ->
                if (characteristicsDisabledAdapter?.itemCount == 0) {
                    characteristicsDisabledAdapter?.setCharacteristics(rollsCharacteristics)
                }
                if (characteristicsAdapter?.itemCount == 0) {
                    characteristicsAdapter?.setCharacteristics(rollsCharacteristics)
                }
            })
    }

    private fun populateRandomRollCharacteristics() {
        characteristicsViewModel.populateRandomRollCharacteristics()
        characteristicsDisabledAdapter?.setCharacteristics(characteristicsViewModel.displayedCharacteristics?.value)
        Log.d(TAG, "item count = ${characteristicsDisabledAdapter?.itemCount.toString()}")
        characteristicsAdapter?.setCharacteristics(characteristicsViewModel.displayedCharacteristics?.value)
        characteristicsRecyclerView?.adapter = characteristicsDisabledAdapter

    }

    /** Set recycler view adapter   **/
    override fun setRecyclerViewAdapter() {
        var checkedBreedList = characteristicsViewModel.characterDisplayedBreeds?.filter { b ->
            b.breedChecked
        }
        characteristicsViewModel.calculateBreedBonuses(checkedBreedList)
        setRecyclerViewDisabledAdapter()
    }

    /**
     * Set Disabled recyclerview adapter
     */
    private fun setRecyclerViewDisabledAdapter() {
        if (activity != null) {
            characteristicsDisabledAdapter =
                CharacteristicsDisabledAdapter(
                    activity as Context
                )

            characteristicsAdapter =
                CharacteristicsAdapter(
                    activity as Context
                )
            characteristicsRecyclerView?.adapter = characteristicsDisabledAdapter
        }

    }

    /** Set recycler view layout manager    **/
    override fun setRecyclerViewLayoutManager() {
        characteristicsRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }


    companion object : CustomCompanion() {
        private const val TAG = "CharacteristicRecyclerViewFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): CharacteristicsRecyclerViewFragment {
            val fragment = CharacteristicsRecyclerViewFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, CHARACTERISTICS_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }

    }
}