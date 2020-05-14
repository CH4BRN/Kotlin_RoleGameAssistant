// File NewCharacterActivity.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.newCharacter

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
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
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.ProgressionBarViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewCharacterActivity" :
 *   Used to handle new character creation
 **/
class CharacterActivity :
    AddEndFragment,
    AppCompatActivity() {


    private var viewPager: ViewPager2? = null
    private var pagerAdapter: FragmentAdapter? = null

    /** ViewModel for new character activity    **/
    private lateinit var newCharacterViewModel: NewCharacterViewModel

    private lateinit var progressionBarViewModel: ProgressionBarViewModel

    /** SupportFragmentManager  **/
    private val fragmentManager = supportFragmentManager

    /** Activity life cycle
     * @param savedInstanceState the transmitted bundle**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_new_character)

        loadViewModels()

        //  Observe the progression
        this.observeProgression()
        //  Set the character page adapter
        this.setCharacterPagerAdapter()
        //  Load the navigation bar fragment
        this.loadNavigationBarFragment()
        //  Update the progress bar
        this.updateProgressBarFragment(0)
    }

    /**
     * Called when the user clicks on the ok button into the characteristics alert dialog
     */
    val okButtonClick = { dialog: DialogInterface, which: Int -> }

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
    }

    /** Set character page adapter  **/
    private fun setCharacterPagerAdapter() {
        Log.d(TAG, "setCharacterPageAdapter")
        //  setCharacterLockableViewPager()

        //  Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById<ViewPager2>(R.id.activityNewCharacter_viewPager)
        pagerAdapter = FragmentAdapter(this)
        viewPager?.adapter = pagerAdapter



        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            /**
             * Called when the scroll state changes. Useful for discovering when the user begins
             * dragging, when a fake drag is started, when the pager is automatically settling to the
             * current page, or when it is fully stopped/idle. `state` can be one of [ ][.SCROLL_STATE_IDLE], [.SCROLL_STATE_DRAGGING] or [.SCROLL_STATE_SETTLING].
             */
            override fun onPageScrollStateChanged(state: Int) {
                Log.d(
                    "DEBUG",
                    "onPageScrollStateChanged \n" +
                            "\t state : $state"
                )
                Log.d(
                    "DEBUG",
                    " fragmentList size = ${pagerAdapter?.fragmentList?.size.toString()}"
                )

                super.onPageScrollStateChanged(state)

            }

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
                Log.d(
                    "DEBUG", "onPageScrolled : \n" +
                            "\tposition : $position\n" +
                            "\tpositionO : $positionOffset\n" +
                            "\tpositionOP : $positionOffsetPixels"
                )
                if (position == 3 && pagerAdapter?.fragmentList?.size == 4 && viewPager?.scrollState == 1) {
                    Log.d("DEBUG", "Situation")
                    characteristicsAlert()

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
                progressionBarViewModel.progression.value = position

                super.onPageSelected(position)
            }
        })

    }

    /** Observe view pager progression  **/
    private fun observeProgression() {
        Log.d(TAG, "observeProgression")
        progressionBarViewModel.progression.observe(
            this, Observer { progression ->
                kotlin.run {
                    updateProgressBarFragment(progression)
                }
            }
        )
    }


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
    }

    /**
     * Add the end of the fragment list
     */
    override fun addEndFragment() {
        pagerAdapter?.fragmentList?.add(
            DerivedValues1Fragment.newInstance(
                this
            )
        )
        pagerAdapter?.fragmentList?.add(
            DerivedValues2Fragment.newInstance(
                this
            )
        )
        pagerAdapter?.fragmentList?.add(
            OccupationsFragment.newInstance(
                this
            )
        )
        pagerAdapter?.fragmentList?.add(
            OccupationFragment.newInstance(
                this
            )
        )
        pagerAdapter?.fragmentList?.add(
            HobbiesFragment.newInstance(
                this
            )
        )
        pagerAdapter?.fragmentList?.add(
            HobbyFragment.newInstance(
                this
            )
        )
    }
}