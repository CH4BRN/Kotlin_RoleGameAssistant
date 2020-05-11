// File NewCharacterActivity.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.newCharacter

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.fragment.bars.NavigationBarFragment
import com.uldskull.rolegameassistant.fragments.fragment.bars.ProgressBarFragment
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewCharacterActivity" :
 *   Used to handle new character creation
 **/
class NewCharacterActivity :
    SwipeActivator,
    AppCompatActivity() {


    private var viewPager: ViewPager2? = null

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


        //  Observe the progression
        this.observeProgression()
        //  Set the character page adapter
        this.setCharacterPagerAdapter()
        //  Load the navigation bar fragment
        this.loadNavigationBarFragment()
        //  Update the progress bar
        this.updateProgressBarFragment(0)
    }

    /** Set character page adapter  **/
    private fun setCharacterPagerAdapter() {
        Log.d(TAG, "setCharacterPageAdapter")
        //  setCharacterLockableViewPager()

        //  Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById<ViewPager2>(R.id.activityNewCharacter_viewPager)
        val pagerAdapter = FragmentAdapter(this)
        viewPager?.adapter = pagerAdapter

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


    //  [LOCKABLE VIEWPAGER]
    override fun lockViewPager(swipeIsEnabled: Boolean) {
        Log.d(TAG, "lockViewPager = $swipeIsEnabled")
        this.isSwipeEnabled = swipeIsEnabled
    }

    private var isSwipeEnabled: Boolean = true

    /*   private fun setCharacterLockableViewPager() {
       //  Serialize the view pager.
       lockableViewPager = findViewById<LockableViewPager>(R.id.activityNewCharacter_viewPager)

       //  Set the viewPager adapter.
       lockableViewPager.adapter = CharacterPagerAdapter(supportFragmentManager, this)
   }*/


    //  [OVERLAY]
    /**
     * Initialize overlay view
     */
    /*  private fun initializeOverlay() {
          var touchLayer =
              deserializeOverlayView()

          setOverlayOnTouchListener(touchLayer)
          initializeGestureDetector()
      }*/

    /*  private fun initializeGestureDetector() {
          gestureDetector = GestureDetector(this, object : GestureDetector.OnGestureListener {
              */
    /**
     * The user has performed a down [MotionEvent] and not performed
     * a move or up yet. This event is commonly used to provide visual
     * feedback to the user to let them know that their action has been
     * recognized i.e. highlight an element.
     *
     * @param e The down motion event
     *//*
            override fun onShowPress(e: MotionEvent?) {
                Log.d(TAG, "onShowPress")
            }

            */
    /**
     * Notified when a tap occurs with the up [MotionEvent]
     * that triggered it.
     *
     * @param e The up motion event that completed the first tap
     * @return true if the event is consumed, else false
     *//*
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                Log.d(TAG, "onSingleTapUp")
                return true
            }

            */
    /**
     * Notified when a tap occurs with the down [MotionEvent]
     * that triggered it. This will be triggered immediately for
     * every down event. All other events should be preceded by this.
     *
     * @param e The down motion event.
     *//*
            override fun onDown(e: MotionEvent?): Boolean {
                Log.d(TAG, "onDown")
                return true
            }

            */
    /**
     * Notified of a fling event when it occurs with the initial on down [MotionEvent]
     * and the matching up [MotionEvent]. The calculated velocity is supplied along
     * the x and y axis in pixels per second.
     *
     * @param e1 The first down motion event that started the fling.
     * @param e2 The move motion event that triggered the current onFling.
     * @param velocityX The velocity of this fling measured in pixels per second
     * along the x axis.
     * @param velocityY The velocity of this fling measured in pixels per second
     * along the y axis.
     * @return true if the event is consumed, else false
     *//*
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                Log.d("DEBUG", "onFling = ${e1?.x} and ${e2?.x}")
                if (e1 != null && e2 != null) {
                    Log.d("DEBUG", "Swipe")
                    if (e1.x < e2.x) {
                        var fragmentPosition = lockableViewPager?.currentItem
                        lockableViewPager.setCurrentItem(fragmentPosition - 1, true)
                        isSwipeEnabled = true
                    }
                }

                return true
            }

            */
    /**
     * Notified when a scroll occurs with the initial on down [MotionEvent] and the
     * current move [MotionEvent]. The distance in x and y is also supplied for
     * convenience.
     *
     * @param e1 The first down motion event that started the scrolling.
     * @param e2 The move motion event that triggered the current onScroll.
     * @param distanceX The distance along the X axis that has been scrolled since the last
     * call to onScroll. This is NOT the distance between `e1`
     * and `e2`.
     * @param distanceY The distance along the Y axis that has been scrolled since the last
     * call to onScroll. This is NOT the distance between `e1`
     * and `e2`.
     * @return true if the event is consumed, else false
     *//*
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                Log.d(TAG, "onScroll")
                return true
            }

            */
    /**
     * Notified when a long press occurs with the initial on down [MotionEvent]
     * that trigged it.
     *
     * @param e The initial on down motion event that started the longpress.
     *//*
            override fun onLongPress(e: MotionEvent?) {
                Log.d(TAG, "onLongPress")

            }

        })
    }*/

    // var originalX = 2000f

    // var gestureDetector: GestureDetector? = null

    /**
     * Sets overlay view onTouchListener
     */
    /* private fun setOverlayOnTouchListener(touchLayer: View?) {
         touchLayer?.setOnTouchListener(object : OverlayTouchListener() {
             */
    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     * the event.
     * @return True if the listener has consumed the event, false otherwise.
     *//*
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                Log.d(TAG, "onTouch")
                if (event != null) {
                    Log.d(TAG, "event is not null")
                    //  Check the swiping direction and ViewPager.isSwipeEnabled
                    if (isSwipeEnabled) {
                        Log.d(TAG, "isSwipeEnabled == true")
                        lockableViewPager?.onTouchEvent(event)
                    } else {
                        Log.d("DEBUG", "Handled by the gesture detector")
                        gestureDetector?.onTouchEvent(event)
                    }
                }
                return true
            }
        })
    }*/


    /*   private fun deserializeOverlayView(): View? {
           var touchLayer =
               this.findViewById<View>(R.id.touch_layer)
           Log.d(TAG, "is touch layer null ? :${touchLayer == null}")
           return touchLayer
       }*/

}