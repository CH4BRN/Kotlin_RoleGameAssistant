// File CustomGestureListener.kt
// @Author pierre.antoine - 09/05/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.viewPager

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

/**
 *   Class "CustomGestureListener" :
 *   TODO: Fill class use.
 **/
class CustomGestureListener : GestureDetector.SimpleOnGestureListener() {
    companion object{
        private const val TAG = "CustomGestureListener"
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        Log.d(TAG, "onScroll")
        return super.onScroll(e1, e2, distanceX, distanceY)
    }
}