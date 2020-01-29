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

        progression.observe(
            this, Observer { prog ->
                kotlin.run {
                    loadProgressBarFragment(prog)
                }
            })

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
        loadProgressBarFragment(0)

    }

    /** Load the progress bar fragment  **/
    private fun loadProgressBarFragment(progression: Int) {
        val progressBarTransaction = fragmentManager.beginTransaction()
        progressBarTransaction.replace(
            R.id.container_progressBar,
            ProgressBarFragment.newInstance(this, progression)
        )
            .commit()
    }


    companion object {
        var progression = MutableLiveData<Int>()

    }

    class CharacterPagerAdapter(fm: FragmentManager, val activity: Activity) :
        FragmentPagerAdapter(fm) {
        override fun getCount(): Int = 6

        override fun getItemPosition(`object`: Any): Int {
            var position = super.getItemPosition(`object`)
            Toast.makeText(activity, position.toString(), Toast.LENGTH_SHORT).show()
            return position

        }
        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> {
                    return BasicInfoFragment.newInstance(activity, position)
                }
                1 -> {
                    return CharacteristicsFragment.newInstance(activity, position)
                }
                2 -> {
                    return BackgroundFragment.newInstance(activity, position)
                }
                3 -> {
                    return AbilitiesRecyclerViewFragment.newInstance(activity, position)
                }
                4 -> {
                    return HealthFragment.newInstance(activity, position)
                }
                5 -> {
                    return SkillsRecyclerViewFragment.newInstance(activity, position)
                }
                else -> {
                    return BasicInfoFragment.newInstance(activity, position)
                }
            }
        }
    }

}