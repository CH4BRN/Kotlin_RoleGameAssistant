// File NavigationBarFragment.kt
// @Author pierre.antoine - 17/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bars

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.viewmodels.*
import com.uldskull.rolegameassistant.viewmodels.breeds.DisplayedBreedsViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationSkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationsViewModel
import kotlinx.android.synthetic.main.fragment_navigation_bar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "NavigationBarFragment" :
 *   Fragment that displays the navigation bar.
 **/
class NavigationBarFragment : CustomFragment() {

    /**
     * New character view model.
     */
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /**
     * Displayed breed view model
     */
    private val displayedBreedsViewModel: DisplayedBreedsViewModel by sharedViewModel()


    /**
     * Characteristics view model.
     */
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()

    /**
     * Characters view model.
     */
    private val charactersViewModel: CharactersViewModel by sharedViewModel()

    /**
     * Derived values ViewModel
     */
    private val derivedValuesViewModel: DerivedValuesViewModel by sharedViewModel()

    /**
     * Bonds view model
     */
    private val bondsViewModel: BondsViewModel by sharedViewModel()

    private val idealsViewModel: IdealsViewModel by sharedViewModel()

    private val occupationsViewModel: OccupationsViewModel by sharedViewModel()

    private val occupationSkillsViewModel: OccupationSkillsViewModel by sharedViewModel()

    private val pointsToSpendViewModel: PointsToSpendViewModel by sharedViewModel()

    /**
     * Is saving enabled ?
     */
    private var isSavingEnabled: Boolean = false

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_navigation_bar, container, false
        )
        return initialRootView
    }

    /**
     * Displays the "go back" confirmation alert dialog
     */
    private fun displayBackConfirmationAlertDialog() {
        Log.d(TAG, "displayBackConfirmation")
        if (activity != null) {
            val confirmDialog = AlertDialog.Builder(activity!!)

            confirmDialog.setTitle("Go back? : ")
            val confirmDialogItems = arrayOf(
                "Go back.",
                "No, continue."
            )

            confirmDialog.setItems(
                confirmDialogItems
            ) { _, which ->
                /**
                 * This method will be invoked when a button in the dialog is clicked.
                 *
                 * @param which the button that was clicked (ex.
                 * [DialogInterface.BUTTON_POSITIVE]) or the position
                 * of the item clicked
                 */
                when (which) {
                    0 -> activity?.onBackPressed()
                }

            }
            confirmDialog.show()
        }

    }

    /**
     * Fragment life-cycle
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        setBackButtonOnClickListener()
        setSaveButtonOnClickListener()

        observeCharacterName()
        observeCurrentCharacter()

        occupationsViewModel?.observedOccupationsSkills?.observe(this, Observer {
            it.forEach { Log.d("DEBUG$TAG", "Skill = ${it.skillName}") }
        })
    }

    private fun observeCurrentCharacter() {
        newCharacterViewModel?.currentCharacter.observe(this, Observer {
            Log.d("DEBUG$TAG", "Current character : $it")
        })
    }

    /**
     * Observe the character's name
     */
    private fun observeCharacterName() {
        newCharacterViewModel.characterName.observe(this, Observer { name ->
            run {
                isSavingEnabled = !(name.isNullOrBlank() || name.isNullOrEmpty())
            }
        })
    }

    /**
     * Set the back button onClickListener
     */
    private fun setBackButtonOnClickListener() {
        Log.d(TAG, "setBackButton")
        btn_back.setOnClickListener {
            displayBackConfirmationAlertDialog()
        }
    }

    /**
     * Set the save button OnClickListener
     */
    private fun setSaveButtonOnClickListener() {
        Log.d(TAG, "setSaveButton")
        btn_save.setOnClickListener {
            if (isSavingEnabled) {
                doSave()
            } else {
                displayNameAlertDialog()
            }
        }
    }

    /**
     * Displays alert dialog for characteristics
     */
    private fun displayNameAlertDialog() {
        if (activity != null) {
            val builder = AlertDialog.Builder(activity!!)
            builder.setTitle("Before continuing ...")
            builder.setMessage("Name is necessary to save.")
            builder.setPositiveButton(
                "OK",
                DialogInterface.OnClickListener(function = okButtonClick)
            )
            builder.show()
        }
    }


    /**
     * Displayed the "characters saved" alert dialog
     */
    private fun displaySavedAlertDialog() {
        if (activity != null) {
            val builder = AlertDialog.Builder(activity!!)
            builder.setTitle("Character is saved.")
            builder.setPositiveButton(
                "Continue",
                DialogInterface.OnClickListener(okButtonClick)
            )
            builder.show()
        }
    }

    /**
     * Called when the user clicks on the ok button into the name alert dialog
     */
    private val okButtonClick = { _: DialogInterface, _: Int -> }

    /**
     * Do the saving.
     */
    private fun doSave() {
        Log.d(TAG, "doSave")

        try {
            var bonds = bondsViewModel.bonds.value
            Log.d("DEBUG$TAG", "saved bonds = ${bonds?.size}")

            var characterIdeals =
                idealsViewModel.mutableIdeals?.value?.filter { i -> i?.isChecked!! }
            if (characterIdeals != null) {
                newCharacterViewModel.currentCharacter?.value?.characterIdeals =
                    characterIdeals.toMutableList()
            }

            newCharacterViewModel.characterBonds = bonds

            var occupationsSkills = occupationsViewModel?.observedOccupationsSkills?.value
            occupationsSkills?.forEach { Log.d("DEBUG$TAG", "Skill : ${it.skillName}") }

            var checkedSkills = occupationsSkills?.filter { s -> s.skillIsChecked }
            Log.d("DEBUG$TAG", "CheckedSkillSize : ${checkedSkills?.size}")
            var skillsIds = mutableListOf<Long?>()
            checkedSkills?.forEach {
                skillsIds.add(it?.skillId)
            }


            skillsIds?.forEach {
                Log.d("DEBUG$TAG", "Skills id : $it")
            }

            newCharacterViewModel?.characterSkillsIds = skillsIds

            val insertedId =
                saveCharacter()

            var characterWithSkills = newCharacterViewModel?.getCharacterWithSkills(insertedId)

            Log.d("DEBUG$TAG", "characterWithSkills : $characterWithSkills ")

            val result = charactersViewModel.findOneById(insertedId)
            result?.characterBreeds?.forEach { id ->
                kotlin.run {
                    var breed = displayedBreedsViewModel?.findBreedWithId(id)

                    Log.d("DEBUG$TAG", "Breed $breed")
                }
            }
            result?.characterIdeals?.forEach {
                Log.d(
                    "DEBUG$TAG",
                    "result?.characterIdeals? ${it?.idealName} is checked : ${it?.isChecked}"
                )
            }
            Log.d(TAG, "${result?.characterName} saved")
        } catch (e: Exception) {
            Log.e("ERROR", "Save failed")
            e.printStackTrace()
            throw e
        }
        displaySavedAlertDialog()

    }



    /**
     * Get the checked breeds from the view model
     */
    private fun getCheckedBreeds(): List<DomainDisplayedBreed>? {
        return displayedBreedsViewModel.observedMutableBreeds.value?.filter { b -> b.breedChecked }
    }

    /**
     * Save a character and gets the resulting id
     */
    private fun saveCharacter(): Long? {
        return newCharacterViewModel.saveCharacter(
            characteristics = characteristicsViewModel.displayedCharacteristics?.value,
            ideaScore = derivedValuesViewModel.ideaScore.value,
            healthScore = derivedValuesViewModel.totalHealth.value,
            energyScore = derivedValuesViewModel.energyPoints.value,
            knowScore = derivedValuesViewModel.knowScore.value,
            luckScore = derivedValuesViewModel.luckScore.value,
            sanityScore = derivedValuesViewModel.sanityScore.value,
            baseHealth = derivedValuesViewModel.baseHealth.value,
            breedBonus = derivedValuesViewModel.breedHealthBonus.value,
            skillsIds = newCharacterViewModel.characterSkillsIds,
            filledSkills = occupationSkillsViewModel.checkedOccupationSkills.value,
            spentOccupationPoints = pointsToSpendViewModel?.observableOccupationSpentPoints?.value
        )
    }


    companion object : CustomCompanion() {
        private const val TAG = "NavigationBarFragment"
        override fun newInstance(activity: Activity): CustomFragment {
            Log.d(TAG, "newInstance")
            val fragment = NavigationBarFragment()
            fragment.activity = activity
            return fragment
        }

    }

}