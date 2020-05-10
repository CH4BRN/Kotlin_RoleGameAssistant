// File NewCharacterActivity.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.activities

import android.gesture.GestureOverlayView
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewOverlay
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.bars.NavigationBarFragment
import com.uldskull.rolegameassistant.fragments.fragment.bars.ProgressBarFragment
import com.uldskull.rolegameassistant.fragments.viewPager.LockableViewPager
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.CharacterPagerAdapter
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewCharacterActivity" :
 *   Used to handle new character creation
 **/
class NewCharacterActivity :
    SwipeActivator,
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

        initializeOverlay()


        //  Get the ViewModels by DI
        newCharacterViewModel = getViewModel()
        //  Observe the progression
        this.observeProgression()
        //  Set the character page adapter
        this.setCharacterPageAdapter()
        //  Load the navigation bar fragment
        this.loadNavigationBarFragment()
        //  Update the progress bar
        this.updateProgressBarFragment(0)
    }

    private fun initializeOverlay() {
        var touchLayer =
            deserializeOverlayView()

        setOverlayOnTouchListener(touchLayer)
    }

    private fun setOverlayOnTouchListener(touchLayer: View?) {
        touchLayer?.setOnTouchListener(object : OverlayTouchListener() {
            /**
             * Called when a touch event is dispatched to a view. This allows listeners to
             * get a chance to respond before the target view.
             *
             * @param v The view the touch event has been dispatched to.
             * @param event The MotionEvent object containing full information about
             * the event.
             * @return True if the listener has consumed the event, false otherwise.
             */
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                Log.d(TAG, "onTouch")
                if(event != null){
                    //  Check the swiping direction and ViewPager.isSwipeEnabled
                    viewPager?.setSwipeEnabled(true)
                    viewPager?.onTouchEvent(event)
                }

                return true
            }

        })
    }

    private fun deserializeOverlayView(): View? {
        var touchLayer =
            this.findViewById<View>(R.id.touch_layer)
        Log.d(TAG, "is touch layer null ? :${touchLayer == null}")
        return touchLayer
    }


    /** Set character page adapter  **/
    private fun setCharacterPageAdapter() {
        Log.d(TAG, "setCharacterPageAdapter")
        //  Serialize the view pager.
        viewPager = findViewById<LockableViewPager>(R.id.activityNewCharacter_viewPager)

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
    private lateinit var viewPager: LockableViewPager

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

    override fun lockViewPager(swipeIsEnabled: Boolean) {
        viewPager?.setSwipeEnabled(swipeIsEnabled)
    }

}