// File NewCharacterActivity.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.character

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.core.AddEndFragment
import com.uldskull.rolegameassistant.activities.core.AddEndFragmentAndUpdateAdapter
import com.uldskull.rolegameassistant.activities.FragmentAdapter
import com.uldskull.rolegameassistant.activities.core.replaceFragment
import com.uldskull.rolegameassistant.fragments.fragment.bars.NavigationBarFragment
import com.uldskull.rolegameassistant.fragments.fragment.bars.ProgressBarFragment
import com.uldskull.rolegameassistant.fragments.fragment.derivedValues.DerivedValues1Fragment
import com.uldskull.rolegameassistant.fragments.fragment.derivedValues.DerivedValues2Fragment
import com.uldskull.rolegameassistant.fragments.fragment.hobbies.HobbiesFragment
import com.uldskull.rolegameassistant.fragments.fragment.hobby.HobbyFragment
import com.uldskull.rolegameassistant.fragments.fragment.occupation.OccupationFragment
import com.uldskull.rolegameassistant.fragments.fragment.occupations.OccupationsFragment
import com.uldskull.rolegameassistant.models.breed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.models.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.viewmodels.*
import com.uldskull.rolegameassistant.viewmodels.breeds.DisplayedBreedsViewModel
import com.uldskull.rolegameassistant.viewmodels.character.CharactersPictureViewModel
import com.uldskull.rolegameassistant.viewmodels.character.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.hobbies.HobbiesViewModel
import com.uldskull.rolegameassistant.viewmodels.hobbies.HobbySkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationSkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewCharacterActivity" :
 *   Used to handle new character creation
 **/
class CharacterActivity :
    AddEndFragment,
    AddEndFragmentAndUpdateAdapter,
    AppCompatActivity() {
    /**
     * ViewPager2 to display fragments
     */
    var viewPager: ViewPager2? = null

    /**
     * Custom fragment adapter
     */
    var fragmentAdapter: FragmentAdapter? = null

    /** ViewModel for new character activity    **/
    private lateinit var newCharacterViewModel: NewCharacterViewModel

    /**
     * Viewmodel for characteristics
     */
    private lateinit var characteristicsViewModel: CharacteristicsViewModel

    /**
     * Viewmodel for progression bar
     */
    private lateinit var progressionBarViewModel: ProgressionBarViewModel

    /**
     * Viewmodel for occupations
     */
    private lateinit var occupationsViewModel: OccupationsViewModel

    /**
     * Viewmodel for hobbies
     */
    private lateinit var hobbiesViewModel: HobbiesViewModel

    /**
     * viewmodel for occupation
     */
    private lateinit var occupationViewModel: OccupationViewModel

    /**
     * ViewModel for displayed breeds
     */
    private lateinit var displayedBreedsViewModel: DisplayedBreedsViewModel

    /**
     * Viewmodel for character's picture
     */
    private lateinit var charactersPictureViewModel: CharactersPictureViewModel

    /**
     * Viewmodel for occupation skills
     */
    private lateinit var occupationSkillsViewModel: OccupationSkillsViewModel

    /**
     * View model for hobby skills
     */
    private lateinit var hobbySkillsViewModel: HobbySkillsViewModel

    /**
     * Viewmodel for points to spend
     */
    private lateinit var pointsToSpendViewModel: PointsToSpendViewModel

    /**
     * Viewmodel for skills
     */
    private lateinit var skillsViewModel: SkillsViewModel

    /** Activity life cycle
     * @param savedInstanceState the transmitted bundle**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        //  Set the view
        setContentView(R.layout.activity_character_edit)
        //  load view models
        loadViewModels()
        //  Set the character page adapter
        this.setCharacterPagerAdapter()
        //  Load the navigation bar fragment
        this.loadNavigationBarFragment()
        //  Update the progress bar
        this.initializeProgressBarFragment()

        deserializeDomainCharacter()
        observeRepositoryOccupations()
        observeRepositorySkills()
    }

    /**
     * Observes occupation from the repository.
     */
    private fun observeRepositoryOccupations() {
        this.occupationsViewModel.repositoryOccupations?.observe(
            this,
            Observer { domainOccupations ->
                kotlin.run {
                    domainOccupations?.let {
                        Log.d("DEBUG$TAG", "observedOccupations has changed")
                        occupationsViewModel.displayedOccupations.value =
                            domainOccupations.map { o -> o?.occupationName!! }

                        Log.d("DEBUG$TAG", "Occupations : $it")
                    }
                }
            })
    }

    /**
     * Observe skills from repository
     */
    private fun observeRepositorySkills() {
        this.skillsViewModel.repositorySkillsToCheck?.observe(
            this,
            Observer { domainSkillToCheck: List<DomainSkillToCheck> ->
                kotlin.run {
                    domainSkillToCheck.let { list ->
                        skillsViewModel.hobbiesSkills.value =
                            domainSkillToCheck

                        Log.d("DEBUG$TAG", "Repository Skills : $list")
                    }
                }
            }
        )
    }

    /**
     * Deserialize domain character
     */
    private fun deserializeDomainCharacter() {
        var jsonCharacter: String? = null
        val extras: Bundle? = intent.extras
        if (extras != null) {
            jsonCharacter = extras.getString("selectedCharacter")
        }
        val character: DomainCharacter? =
            Gson().fromJson(jsonCharacter, DomainCharacter::class.java)
        Log.d("DEBUG$TAG", "Character = ${character?.characterName}")
        if (character != null) {

            // Sets the values
            newCharacterViewModel.selectedCharacter.value = character
            newCharacterViewModel.currentCharacter = character
            newCharacterViewModel.characterName.value = character.characterName
            newCharacterViewModel.characterAge.value = character.characterAge
            newCharacterViewModel.characterGender.value = character.characterGender
            newCharacterViewModel.characterBiography.value = character.characterBiography
            newCharacterViewModel.characterHeight.value = character.characterHeight
            newCharacterViewModel.characterWeight.value = character.characterWeight
            pointsToSpendViewModel.observableOccupationSpentPoints.value =
                character.characterSpentOccupationPoints

            //  Gets the picture URI
            val pictureURI = character?.characterPictureUri
            Log.d("DEBUG$TAG", "Picture uri : $pictureURI")
            charactersPictureViewModel.pictureUri.value = pictureURI

            // Gets the breeds
            val characterBreeds = mutableListOf<DomainDisplayedBreed?>()
            character.characterBreeds?.forEach {
                characterBreeds.add(displayedBreedsViewModel.findBreedWithId(it))
            }
            val breedsToLoad: MutableList<DomainDisplayedBreed> = mutableListOf()
            observeRepositoryBreeds(characterBreeds, breedsToLoad)

            // Gets the occupation
            val occupation = character.characterOccupation
            initializeSelectedOccupation(occupation)

            //  Gets the occupation skills ids
            val occupationSkillsIds = character.characterSelectedOccupationSkill
            //  Sets the occupation skills ids
            occupationsViewModel.selectedCharacterOccupationsSkills.value =
                occupationSkillsIds?.toList()

            observeOccupationsSkills()
            //  Gets the observed occupations skills
            var occupationsSkills = occupationsViewModel.observedOccupationsSkills?.value
            // get the character's occupation skills
            val occupationSkills: List<DomainSkillToFill>? =
                newCharacterViewModel.getCharacterSkills(character.characterId, 0)
            // sets the character's skills
            if (occupationSkillsViewModel.checkedOccupationSkills.value == null) {
                occupationSkillsViewModel.checkedOccupationSkills.value = occupationSkills
            }

            //  Get character selected hobbies skills
            val hobbiesSkillsIds = character.characterSelectedHobbiesSkill
            //  Get character hobbies skill to check
            val characterHobbiesSkillToCheck: MutableList<DomainSkillToCheck?>? =
                getCharacterHobbiesSkillToCheck(hobbiesSkillsIds)
            //  Set character hobbies skills to check
            skillsViewModel.hobbiesSkills.value = characterHobbiesSkillToCheck

            Log.d("DEBUG$TAG", "hobbiesSkillsIds : $hobbiesSkillsIds")
            hobbiesViewModel.selectedCharacterSkills.value = hobbiesSkillsIds
            //  Observe hobbies skills
            observeHobbiesSkills()
            //  Observe hobbies skills
            observeSkillsForHobbies()

            //  Hobby
            val characterId: Long? = if (character.characterId != null) {
                character.characterId
            } else {
                null
            }

            if (characterId != null) {
                Log.d("DEBUG$TAG", "Character ID : $characterId")
                val hobbySkills: List<DomainSkillToFill>? =
                    getCharacterHobbySkills(characterId)
                skillsViewModel.characterHobbySkills = hobbySkills

                val occupationSkills: List<DomainSkillToFill>? =
                    getCharacterOccupationSkills(characterId)
                skillsViewModel.characterOccupationSkills = occupationSkills
            }
        }
    }

    /**
     * Get character hobbies' skills to check
     */
    private fun getCharacterHobbiesSkillToCheck(hobbiesSkillsIds: MutableList<Long?>?): MutableList<DomainSkillToCheck?>? {
        // get tge mutable hobbies skills
        val list: MutableList<DomainSkillToCheck?>? =
            skillsViewModel.hobbiesSkills.value?.toMutableList()
        // Check the skills if they are in the character selected skills
        list?.forEach { skill ->
            kotlin.run {
                hobbiesSkillsIds?.forEach { id ->
                    run {
                        if (skill != null && id != null) {
                            skill.skillIsChecked = true
                            val index = list.indexOfFirst { s -> s?.skillId == skill.skillId }
                            list[index] = skill
                        }
                    }
                }
            }
        }
        return list
    }

    /**
     * Get the character's occupation skills
     */
    private fun getCharacterOccupationSkills(
        characterId: Long?
    ): List<DomainSkillToFill>? {
        val occupationSkills: List<DomainSkillToFill>? =
            newCharacterViewModel.getCharacterSkills(characterId, 0)
        Log.d("DEBUG$TAG", "occupationSkills count : ${occupationSkills?.count()}")
        occupationSkills?.forEach {
            Log.d(
                "DEBUG$TAG",
                "occupationSkills value : ${it.skillName}  is ${it.filledSkillTensValue}${it.filledSkillUnitsValue}"
            )
        }
        return occupationSkills
    }

    /**
     * Get the character's hobby skills
     */
    private fun getCharacterHobbySkills(characterId: Long?): List<DomainSkillToFill>? {
        return newCharacterViewModel.getCharacterSkills(characterId, 1)
    }

    /**
     * get the hobbies skills
     */
    private fun observeSkillsForHobbies() {
        skillsViewModel.hobbiesSkills.observe(this, Observer {
            val list = it?.toMutableList()
            val selected = hobbiesViewModel.selectedCharacterSkills.value

            list?.forEach { skill ->
                selected?.forEach { id ->
                    if (skill?.skillId == id) {
                        if (!skill?.skillIsChecked!!) {
                            skill.skillIsChecked = true
                            val index = list.indexOfFirst { s -> s?.skillId == id }
                            list[index] = skill
                        }
                    }
                }
            }

            if (skillsViewModel.hobbiesSkills.value.toString() != list.toString()) {
                skillsViewModel.hobbiesSkills.value = list
            }


        })
    }

    /**
     * Observe hobbies skills
     */
    private fun observeHobbiesSkills() {
        hobbiesViewModel.observedHobbiesSkills?.observe(this, Observer {
            val list = it.toMutableList()
            val selected = hobbiesViewModel.selectedCharacterSkills.value
            list.forEach { skill ->
                selected?.forEach { selectedId ->
                    kotlin.run {
                        if (skill.skillId == selectedId) {
                            val index = list.indexOfFirst { s -> s.skillId == skill.skillId }
                            skill.skillIsChecked = true
                            list[index] = skill
                        }
                    }
                }
            }
            skillsViewModel.hobbiesSkills.value = list
        })
    }

    /**
     * Observe occupations skills
     */
    private fun observeOccupationsSkills() {
        //  Observe the observed occupations skills
        occupationsViewModel.observedOccupationsSkills?.observe(
            this,
            Observer { skillsToCheck ->
                //  "Let" the skills to check
                skillsToCheck.let { skillsToCheckList ->

                    //  Gets a "mutable" list
                    var mutableSkillsToCheckList = skillsToCheckList?.toMutableList()

                    //  Observe the selected character occupations skills
                    occupationsViewModel.selectedCharacterOccupationsSkills.observe(
                        this,
                        Observer { characterCheckedSkills ->
                            //  "Let" the character selected skills
                            characterCheckedSkills.let { characterCheckedSkillsList ->
                                //  Foreach skills to check
                                mutableSkillsToCheckList?.forEach { skill ->
                                    if (characterCheckedSkillsList.any { characterCheckedSkill -> characterCheckedSkill!! == skill?.skillId!! }) {
                                        val index =
                                            mutableSkillsToCheckList.indexOfFirst { s -> s.skillId == skill.skillId }
                                        skill.skillIsChecked = true
                                        mutableSkillsToCheckList[index] = skill
                                    }
                                }
                            }
                        }
                    )
                }
            }
        )
    }

    /**
     * Observe repository's breeds.
     */
    private fun observeRepositoryBreeds(
        characterBreeds: MutableList<DomainDisplayedBreed?>,
        breedsToLoad: MutableList<DomainDisplayedBreed>
    ) {
        displayedBreedsViewModel.observedRepositoryBreeds?.observe(
            this,
            Observer { repositoryBreeds ->
                repositoryBreeds?.forEach { breed ->
                    if (characterBreeds.any { b -> b?.breedId == breed.breedId }) {
                        breed.breedIsChecked = true
                    }
                    breedsToLoad.add(breed)
                }
                displayedBreedsViewModel.observedMutableBreeds.value = breedsToLoad.toList()

            })
    }

    /**
     * Initialize selected occupation
     */
    private fun initializeSelectedOccupation(occupation: DomainOccupation?) {
        if (occupation != null) {
            occupationsViewModel.selectedOccupation?.value = occupation
        }
    }

    /**
     * Called when the user clicks on the ok button into the characteristics alert dialog
     */
    private val okButtonClick = { _: DialogInterface, _: Int -> }

    /**
     * Displays alert dialog for characteristics
     */
    fun characteristicsAlert() {
        val builder = AlertDialog.Builder(this)
        val title = getString(R.string.before_continuing)
        builder.setTitle(title)
        val content = getString(R.string.necessary_characteristics)
        builder.setMessage(content)
        builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = okButtonClick))
        builder.show()
    }

    /**
     * load the viewModels
     */
    private fun loadViewModels() {
        //  Get the ViewModels by DI
        newCharacterViewModel = getViewModel()
        progressionBarViewModel = getViewModel()
        characteristicsViewModel = getViewModel()
        occupationViewModel = getViewModel()
        occupationsViewModel = getViewModel()
        hobbiesViewModel = getViewModel()
        displayedBreedsViewModel = getViewModel()
        charactersPictureViewModel = getViewModel()
        occupationSkillsViewModel = getViewModel()
        hobbySkillsViewModel = getViewModel()
        pointsToSpendViewModel = getViewModel()
        skillsViewModel = getViewModel()

    }


    /** Set character page adapter  **/
    private fun setCharacterPagerAdapter() {
        Log.d(TAG, "setCharacterPageAdapter")
        //  Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.activityEditCharacter_viewPager2)
        fragmentAdapter =
            FragmentAdapter(this)
        viewPager?.adapter = fragmentAdapter

        //  Register on Page change callback
        viewPager?.registerOnPageChangeCallback(onPageChangeCallback())

    }

    /**
     * Callback for swiping
     */
    private fun onPageChangeCallback(): ViewPager2.OnPageChangeCallback {
        return object : ViewPager2.OnPageChangeCallback() {

            /**
             * This method will be invoked when the current page is scrolled, either as part
             * of a programmatically initiated smooth scroll or a user initiated touch scroll.
             *
             * @param position Position index of the first page currently being displayed.
             * Page position+1 will be visible if positionOffset is nonzero.
             * @param positionOffset Value from [0, 1) indicating the offset from the page at position.
             * @param positionOffsetPixels Value in pixels indicating the offset from position.
             */
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (isOnCharacteristicFragment(position)) {
                    var areCharacteristicsRolled = true

                    val characteristics = characteristicsViewModel.getAllCharacteristics()
                    var isOneCharacteristicToZero = false
                    characteristics?.forEach { characteristic ->
                        if(characteristic?.characteristicTotal == 0){
                            isOneCharacteristicToZero = true
                        }
                    }
                    if (characteristics == null || (characteristics.isEmpty()) || isOneCharacteristicToZero) {
                        characteristicsAlert()
                    } else {
                        characteristics.forEach {
                            if (it?.characteristicRoll == 0) {
                                areCharacteristicsRolled = false
                            }
                        }
                        if (!areCharacteristicsRolled) {
                            characteristicsAlert()
                        } else {
                            addEndFragments()
                        }
                    }
                }

                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            /**
             * This method will be invoked when a new page becomes selected. Animation is not
             * necessarily complete.
             *
             * @param position Position index of the new selected page.
             */
            override fun onPageSelected(position: Int) {
                progressionBarViewModel.progression.value = position * 10
                super.onPageSelected(position)
            }
        }
    }

    /**
     * Checks if the characteristics fragment is displayed.
     */
    private fun isOnCharacteristicFragment(position: Int) =
        position == 4 && fragmentAdapter?.fragmentList?.size == 5 && viewPager?.scrollState == 1


    /** Load the progress bar fragment
     * @param progression the progression to display **/
    private fun initializeProgressBarFragment() {
        Log.d(TAG, "updateProgressBarFragment")
        this.replaceFragment(
            R.id.activityEditCharacter_layout_progressBar,
            ProgressBarFragment.newInstance(this)
        )
    }

    /**
     * Load the navigation bar fragment.
     */
    private fun loadNavigationBarFragment() {
        Log.d(TAG, "loadNavigationBarFragment")
        this.replaceFragment(
            R.id.activityEditBreed_layout_navigationBar,
            NavigationBarFragment.newInstance(this)
        )
    }

    companion object {

        private const val TAG = "CharacterActivity"
    }

    /**
     * Add the end of the fragment list
     */
    override fun addEndFragments() {
        Log.d("DEBUG$TAG", "Add end fragments")
        if (fragmentAdapter?.itemCount != 10) {
            fragmentAdapter?.fragmentList?.add(
                DerivedValues1Fragment.newInstance(
                    this
                )
            )

            fragmentAdapter?.fragmentList?.add(
                DerivedValues2Fragment.newInstance(
                    this
                )
            )
            fragmentAdapter?.fragmentList?.add(
                OccupationsFragment.newInstance(
                    this
                )
            )
            fragmentAdapter?.fragmentList?.add(
                OccupationFragment.newInstance(
                    this
                )
            )
            fragmentAdapter?.fragmentList?.add(
                HobbiesFragment.newInstance(
                    this
                )
            )
            fragmentAdapter?.fragmentList?.add(
                HobbyFragment.newInstance(
                    this
                )
            )
        }
    }

    /**
     * Add the end of the fragment list and update adapter
     */
    override fun addEndFragmentsAndUpdateAdapter() {
        if (fragmentAdapter?.itemCount != 10) {
            fragmentAdapter?.fragmentList?.add(
                DerivedValues1Fragment.newInstance(
                    this
                )
            )

            fragmentAdapter?.fragmentList?.add(
                DerivedValues2Fragment.newInstance(
                    this
                )
            )
            fragmentAdapter?.fragmentList?.add(
                OccupationsFragment.newInstance(
                    this
                )
            )
            fragmentAdapter?.fragmentList?.add(
                OccupationFragment.newInstance(
                    this
                )
            )
            fragmentAdapter?.fragmentList?.add(
                HobbiesFragment.newInstance(
                    this
                )
            )
            fragmentAdapter?.fragmentList?.add(
                HobbyFragment.newInstance(
                    this
                )
            )
        }

    }
}