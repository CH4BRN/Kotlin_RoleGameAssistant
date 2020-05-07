// File NewCharacterActivity.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.CharacterPagerAdapter
import com.uldskull.rolegameassistant.fragments.fragment.bars.NavigationBarFragment
import com.uldskull.rolegameassistant.fragments.fragment.bars.ProgressBarFragment
import com.uldskull.rolegameassistant.fragments.viewPager.LockableViewPager
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
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

    /** Activity life cycle
     * @param savedInstanceState the transmitted bundle**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_new_character)

        //  Get the ViewModels by DI
        newCharacterViewModel = getViewModel()

        this.observeProgression()

        this.setCharacterPageAdapter()

        this.loadNavigationBarFragment()
        this.updateProgressBarFragment(0)
    }

    /** Set character page adapter  **/
    private fun setCharacterPageAdapter() {
        Log.d(TAG, "setCharacterPageAdapter")
        //  Serialize the view pager.
        viewPager = findViewById(R.id.activityNewCharacter_viewPager) as LockableViewPager

        //  Set the viewPager adapter.
        viewPager.adapter = CharacterPagerAdapter(supportFragmentManager, this)
    }

    /** Observe view pager progression  **/
    private fun observeProgression() {
        Log.d(TAG, "observeProgression")
        progression.observe(
            this, Observer { progression ->
                kotlin.run {
                    updateProgressBarFragment(progression)
                }
            }
        )
    }

    /** View pager  **/
    private lateinit var viewPager: ViewPager

    /** Load the progress bar fragment
     * @param progression the progression to display **/
    private fun updateProgressBarFragment(progression: Int) {
        Log.d(TAG, "updateProgressBarFragment")
        this.replaceFragment(
            R.id.activityNewCharacter_container_progressBar,
            ProgressBarFragment.newInstance(this, progression)
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

        private const val TAG = "NewCharacterActivity"
        /** ViewPager progression   **/
        var progression = MutableLiveData<Int>()
    }
}