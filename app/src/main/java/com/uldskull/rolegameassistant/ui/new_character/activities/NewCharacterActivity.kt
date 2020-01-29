// File NewCharacterActivity.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.ui.new_character.fragments.*
import com.uldskull.rolegameassistant.ui.new_character.fragments.abilities.AbilitiesRecyclerViewFragment
import com.uldskull.rolegameassistant.ui.new_character.fragments.skills.SkillsRecyclerViewFragment
import com.uldskull.rolegameassistant.ui.new_character.view_model.NewCharacterViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewCharacterActivity" :
 *   Used to handle new character creation
 **/
class NewCharacterActivity :
    AppCompatActivity() {

    /** ViewModel for new character activity    **/
    private lateinit var newCharacterViewModel: NewCharacterViewModel

    /** SupportFragmentManager  **/
    private val fragmentManager = supportFragmentManager

    /** Activity life cycle **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_character)

        //  Get the ViewModels by DI
        newCharacterViewModel = getViewModel()

        progression.observe(this, Observer { prog -> kotlin.run { loadProgressBarFragment(prog) } })

        viewPager = findViewById(R.id.viewPager)

        characterPagerAdapter = CharacterPagerAdapter(supportFragmentManager, this)

        viewPager.adapter = characterPagerAdapter


        loadFragment()
    }

    /** View pager  **/
    private lateinit var viewPager: ViewPager

    private lateinit var characterPagerAdapter: CharacterPagerAdapter

    /** Call the methods that load the fragments    **/
    private fun loadFragment() {
        //    loadBasicInfoFragment()
        //  loadCharacteristicsFragment()
        loadProgressBarFragment(0)
        //  loadPictureFragment()
        //  loadBackgroundFragment()
        //  loadAbilitiesFragment()
        //  loadHealthFragment()
        //  loadSkillsFragment()
    }
/*
    /** Loads the picture fragment  **/
    private fun loadPictureFragment() {
        val pictureTransaction = fragmentManager.beginTransaction()

        pictureTransaction.replace(
            R.id.container_picture,
            PictureFragment.newInstance(this)
        )
            .commit()
    }

    /** Load the characteristics fragment   **/
    private fun loadCharacteristicsFragment() {
        val characteristicsTransaction = fragmentManager.beginTransaction()

        characteristicsTransaction.replace(
            R.id.container_characteristics,
            CharacteristicsFragment.newInstance(this)
        )
            .commit()
    }

    /** Load the BasicInfo fragment **/
    private fun loadBasicInfoFragment() {
        val basicInfoTransaction = fragmentManager.beginTransaction()
        basicInfoTransaction.replace(
            R.id.container_basicInfo,
            BasicInfoFragment.newInstance(this)
        )
            .commit()
    }

*/
    /** Load the progress bar fragment  **/
    private fun loadProgressBarFragment(progression: Int) {
        val progressBarTransaction = fragmentManager.beginTransaction()
        progressBarTransaction.replace(
            R.id.container_progressBar,
            ProgressBarFragment.newInstance(this, progression)
        )
            .commit()
    }

    /*
        /** Load background fragment into background container**/
        private fun loadBackgroundFragment() {
            val backgroundTransaction = fragmentManager.beginTransaction()
            backgroundTransaction.replace(
                R.id.container_background,
                BackgroundFragment.newInstance(this)
            )
                .commit()
        }

        /** Load abilities fragment into abilities container**/
        private fun loadAbilitiesFragment() {
            val abilitiesTransaction = fragmentManager.beginTransaction()
            abilitiesTransaction.replace(
                R.id.container_abilityScores,
                AbilitiesRecyclerViewFragment.newInstance(this)
            )
                .commit()
        }

        /** Load health fragment into health container **/
        private fun loadHealthFragment() {
            val healthTransaction = fragmentManager.beginTransaction()
            healthTransaction.replace(
                R.id.container_health,
                HealthFragment.newInstance(this)

            )
                .commit()
        }

        /** Load skills fragment into skills container  **/
        private fun loadSkillsFragment() {
            val skillsTransaction = fragmentManager.beginTransaction()
            skillsTransaction.replace(
                R.id.container_skills,
                SkillsRecyclerViewFragment.newInstance(this)
            )
                .commit()
        }
    */
    companion object {
        var progression = MutableLiveData<Int>()

    }

    class CharacterPagerAdapter(fm: FragmentManager, val activity: Activity) :
        FragmentPagerAdapter(fm) {
        override fun getCount(): Int = 6

        override fun getItem(position: Int): Fragment {
            Toast.makeText(activity, position.toString(), Toast.LENGTH_SHORT).show()

            when (position) {
                0 -> {
                    progression.value = 0
                    return BasicInfoFragment.newInstance(activity)
                }
                1 -> {
                    progression.value = 10
                    return CharacteristicsFragment.newInstance(activity)
                }
                2 -> {
                    progression.value = 20
                    return BackgroundFragment.newInstance(activity)
                }
                3 -> {
                    progression.value = 30
                    return AbilitiesRecyclerViewFragment.newInstance(activity)
                }
                4 -> {
                    progression.value = 40
                    return HealthFragment.newInstance(activity)
                }
                5 -> {
                    progression.value = 50
                    return SkillsRecyclerViewFragment.newInstance(activity)
                }
                else -> {
                    progression.value = 0
                    return BasicInfoFragment.newInstance(activity)
                }
            }
        }
    }

}