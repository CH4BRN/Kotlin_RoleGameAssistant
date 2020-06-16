// File NewCharacterActivity.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.newCharacter

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.fragment.bars.NavigationBarFragment
import com.uldskull.rolegameassistant.fragments.fragment.bars.ProgressBarFragment
import com.uldskull.rolegameassistant.fragments.fragment.derivedValues.DerivedValues1Fragment
import com.uldskull.rolegameassistant.fragments.fragment.derivedValues.DerivedValues2Fragment
import com.uldskull.rolegameassistant.fragments.fragment.hobbies.HobbiesFragment
import com.uldskull.rolegameassistant.fragments.fragment.hobby.HobbyFragment
import com.uldskull.rolegameassistant.fragments.fragment.occupation.OccupationFragment
import com.uldskull.rolegameassistant.fragments.fragment.occupations.OccupationsFragment
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.viewmodels.*
import com.uldskull.rolegameassistant.viewmodels.breeds.DisplayedBreedsViewModel
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

    private lateinit var characteristicsViewModel: CharacteristicsViewModel

    private lateinit var progressionBarViewModel: ProgressionBarViewModel

    private lateinit var occupationsViewModel: OccupationsViewModel

    private lateinit var hobbiesViewModel: HobbiesViewModel

    private lateinit var occupationViewModel: OccupationViewModel

    private lateinit var displayedBreedsViewModel: DisplayedBreedsViewModel

    private lateinit var charactersPictureViewModel: CharactersPictureViewModel

    private lateinit var occupationSkillsViewModel: OccupationSkillsViewModel

    private lateinit var hobbySkillsViewModel: HobbySkillsViewModel

    private lateinit var pointsToSpendViewModel: PointsToSpendViewModel

    private lateinit var skillsViewModel: SkillsViewModel

    /** SupportFragmentManager  **/
    private val fragmentManager = supportFragmentManager

    /** Activity life cycle
     * @param savedInstanceState the transmitted bundle**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        //  Set the view
        setContentView(R.layout.activity_new_character)
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
                            domainOccupations.map { o -> o.occupationName }

                        Log.d("DEBUG$TAG", "Occupations : $it")
                    }
                }
            })
    }

    private fun observeRepositorySkills() {
        this.skillsViewModel.repositorySkillsToCheck?.observe(
            this,
            Observer { domainSkillToCheck: List<DomainSkillToCheck> ->
                kotlin.run {
                    domainSkillToCheck?.let { list ->
                        Log.d("DEBUG$TAG", "ObservedRepository skills has changed")
                        Log.d("DEBUG$TAG", "Repository Skills : $list")
                        list?.forEach {
                            Log.d(
                                "DEBUG$TAG",
                                "Repository Skill :${it.skillName} base : ${it.skillBase}"
                            )
                        }


                        skillsViewModel.hobbiesSkills.value =
                            domainSkillToCheck

                        Log.d("DEBUG$TAG", "Repository Skills : $list")
                    }
                }
            }
        )
    }


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

            newCharacterViewModel.selectedCharacter.value = character
            newCharacterViewModel.currentCharacter.value = character
            newCharacterViewModel.characterName.value = character?.characterName
            newCharacterViewModel.characterAge.value = character?.characterAge
            newCharacterViewModel.characterGender.value = character?.characterGender
            newCharacterViewModel.characterBiography.value = character?.characterBiography
            newCharacterViewModel.characterHeight.value = character?.characterHeight
            newCharacterViewModel.characterWeight.value = character?.characterWeight
            pointsToSpendViewModel?.observableOccupationSpentPoints.value =
                character?.characterSpentOccupationPoints


            var characterBreeds: MutableList<DomainDisplayedBreed?> = mutableListOf()
            character?.characterBreeds?.forEach {
                characterBreeds?.add(displayedBreedsViewModel.findBreedWithId(it))
            }

            var breedsToLoad: MutableList<DomainDisplayedBreed> = mutableListOf()

            observeRepositoryBreeds(characterBreeds, breedsToLoad)

            var occupation = character?.characterOccupation

            initializeSelectedOccupation(occupation)

            //  Occupation
            var occupationSkillsIds = character?.characterSelectedOccupationSkill
            Log.d("DEBUG$TAG", "occupationSkillsIds : $occupationSkillsIds")
            occupationsViewModel?.selectedCharacterSkills?.value = occupationSkillsIds?.toList()
            Log.d("DEBUG$TAG", "occupationSkillsIds : ${occupationSkillsIds}")
            observeOccupationsSkills()
            var occupationsSkills = occupationsViewModel?.observedOccupationsSkills?.value
            Log.d("DEBUG$TAG", "occupationsSkills : ${occupationsSkills}")
            Log.d("DEBUG$TAG", "Occupation = ${occupation}")
            var occupationSkills: List<DomainSkillToFill>? =
                newCharacterViewModel?.getCharacterSkills(character?.characterId, 0)
            Log.d("DEBUG$TAG", "Character occupation skills from activity : $occupationSkills")

            if (occupationSkillsViewModel.checkedOccupationSkills.value == null) {
                occupationSkillsViewModel.checkedOccupationSkills.value = occupationSkills
            }


            var hobbiesSkillsIds = character?.characterSelectedHobbiesSkill
            var list = skillsViewModel?.hobbiesSkills?.value?.toMutableList()
            Log.d("DEBUG$TAG", "List : ${list?.size}")

            list?.forEach { skill ->
                kotlin.run {
                    hobbiesSkillsIds?.forEach { id ->
                        run {
                            if (skill != null && id != null) {
                                skill.skillIsChecked = true

                                Log.d("DEBUG$TAG", "Skill :${skill?.skillName} is checked : ${skill?.skillIsChecked}")
                                var index = list.indexOfFirst { s ->s?.skillId == skill.skillId }
                                list.set(index,skill)
                            }
                        }
                    }
                }
            }

            var checkeds = list?.count{ s -> s?.skillIsChecked!!}
            Log.d("DEBUG$TAG","Checkeds : $checkeds")
            skillsViewModel?.hobbiesSkills?.value = list


            Log.d("DEBUG$TAG", "hobbiesSkillsIds : $occupationSkillsIds")
            hobbiesViewModel?.selectedCharacterSkills?.value = hobbiesSkillsIds
            observeHobbiesSkills()
            observeSkillsForHobbies()

            //  Hobby
            var hobbySkills: List<DomainSkillToFill>? =
                newCharacterViewModel?.getCharacterSkills(character?.characterId, 1)






            Log.d("DEBUG$TAG", "Character hobby skills from activity : $hobbySkills")

            if (hobbySkillsViewModel.checkedHobbySkills.value == null) {
                hobbySkillsViewModel.checkedHobbySkills.value = hobbySkills
            }

            if (skillsViewModel.hobbySkills.value == null) {
                skillsViewModel.hobbySkills.value = hobbySkills
            }


        }

    }

    private fun observeSkillsForHobbies(){
        skillsViewModel?.hobbiesSkills?.observe(this, Observer {
            var list = it?.toMutableList()
            Log.d("DEBUG$TAG", "observedHobbiesSkills : ${list?.size}")
            var selected = hobbiesViewModel?.selectedCharacterSkills?.value
            Log.d("DEBUG$TAG", "selectedCharacterSkills : ${selected?.size}")

            list?.forEach { skill ->
                selected?.forEach { id ->
                    if(skill?.skillId == id){
                        if(!skill?.skillIsChecked!!){
                            skill?.skillIsChecked = true
                            var index = list?.indexOfFirst { s -> s?.skillId == id }
                            list.set(index, skill)
                        }
                    }
                }
            }

            if(skillsViewModel?.hobbiesSkills?.value.toString() != list.toString()){
                skillsViewModel?.hobbiesSkills.value = list
            }


        })
    }

    private fun observeHobbiesSkills() {
        hobbiesViewModel?.observedHobbiesSkills?.observe(this, Observer {
            var list = it.toMutableList()
            Log.d("DEBUG$TAG", "observedHobbiesSkills : ${list.size}")
            var selected = hobbiesViewModel?.selectedCharacterSkills?.value
            list.forEach { skill ->
                selected?.forEach { selectedId ->
                    kotlin.run {
                        if (skill.skillId == selectedId) {
                            var index = list.indexOfFirst { s -> s.skillId == skill.skillId }
                            skill.skillIsChecked = true
                            list[index] = skill
                        }
                    }
                }
            }
            skillsViewModel?.hobbiesSkills?.value = list
        })
    }

    private fun observeOccupationsSkills() {
        occupationsViewModel?.observedOccupationsSkills?.observe(this, Observer {
            var list = it.toMutableList()
            var selected: List<Long?>? = occupationsViewModel?.selectedCharacterSkills?.value


            Log.d("DEBUG$TAG", "observedOccupationsSkills : ${list}")
            list.forEach { skill ->
                selected?.forEach { selectedId ->
                    kotlin.run {
                        if (skill.skillId == selectedId) {
                            var index = list.indexOfFirst { s -> s.skillId == skill.skillId }
                            skill.skillIsChecked = true
                            Log.d(
                                "DEBUG$TAG",
                                "Skill : ${skill?.skillName} is checked ${skill?.skillIsChecked}"
                            )
                            list[index] = skill
                        }
                    }
                }
            }
        })
    }

    private fun observeRepositoryBreeds(
        characterBreeds: MutableList<DomainDisplayedBreed?>,
        breedsToLoad: MutableList<DomainDisplayedBreed>
    ) {
        displayedBreedsViewModel?.observedRepositoryBreeds?.observe(
            this,
            Observer { repositoryBreeds ->
                Log.d("DEBUG$TAG", "repositoryBreeds size = ${repositoryBreeds?.size}")

                repositoryBreeds?.forEach { breed ->
                    if (characterBreeds?.any { b -> b?.breedId == breed.breedId }) {
                        Log.d("DEBUG$TAG", "Corresponding")
                        breed.breedChecked = true
                    }
                    Log.d(
                        "DEBUG$TAG",
                        "Breed ${breed.breedName} is checked : ${breed.breedChecked}"
                    )
                    breedsToLoad?.add(breed)
                }
                displayedBreedsViewModel?.observedMutableBreeds?.value = breedsToLoad.toList()

            })
    }

    private fun initializeSelectedOccupation(occupation: DomainOccupation?) {
        if (occupation != null) {
            var displayedIndex =
                occupationsViewModel?.displayedOccupations?.value?.indexOfFirst { o -> o == occupation?.occupationName }
            Log.d("DEBUG$TAG", "displayedIndex : $displayedIndex")

            var observedIndex =
                occupationsViewModel?.repositoryOccupations?.value?.indexOfFirst { o -> o.occupationId == occupation?.occupationId }
            Log.d("DEBUG$TAG", "observedIndex : $observedIndex")

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
        builder.setTitle("Before continuing ...")
        builder.setMessage("Characteristics are necessary for the following steps.")
        builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = okButtonClick))
        builder.show()
    }

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
        viewPager = findViewById<ViewPager2>(R.id.activityNewCharacter_viewPager)
        fragmentAdapter = FragmentAdapter(this)
        viewPager?.adapter = fragmentAdapter

        //  Register on Page change callback
        viewPager?.registerOnPageChangeCallback(onPageChangeCallback())

    }

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
                if (position == 3 && fragmentAdapter?.fragmentList?.size == 4 && viewPager?.scrollState == 1) {

                    Log.d(
                        "DEBUG$TAG",
                        "Characteristics ${characteristicsViewModel.getAllCharacteristics()}"
                    )
                    var areCharacteristicsRolled = true

                    var characteristics = characteristicsViewModel.getAllCharacteristics()
                    if (characteristics == null || (characteristics.isEmpty())) {
                        characteristicsAlert()
                    } else {
                        characteristics.forEach {
                            if (it?.characteristicRoll == 0) {
                                areCharacteristicsRolled = false
                            }
                        }

                        Log.d("DEBUG$TAG", "areCharacteristicsRolled : $areCharacteristicsRolled")


                        Log.d("DEBUG", "Situation")
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
                Log.d(
                    "DEBUG", "onPageSelected\n" +
                            "\tposition : $position"
                )
                progressionBarViewModel.progression.value = position * 10

                super.onPageSelected(position)
            }
        }
    }


    /** Load the progress bar fragment
     * @param progression the progression to display **/
    private fun initializeProgressBarFragment() {
        Log.d(TAG, "updateProgressBarFragment")
        this.replaceFragment(
            R.id.activityNewCharacter_container_progressBar,
            ProgressBarFragment.newInstance(this)
        )
    }

    /**
     * Load the navigation bar fragment.
     */
    private fun loadNavigationBarFragment() {
        Log.d(TAG, "loadNavigationBarFragment")
        this.replaceFragment(
            R.id.activityNewIdeal_container_navigationBar,
            NavigationBarFragment.newInstance(this)
        )
    }

    companion object {

        private const val TAG = "CharacterActivity"
        /** ViewPager progression   **/
    }

    /**
     * Add the end of the fragment list
     */
    override fun addEndFragments() {
        Log.d("DEBUG$TAG", "Add end fragments")

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

    override fun addEndFragmentsAndUpdateAdapter() {
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