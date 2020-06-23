// ProgressBarAnimation.kt created by UldSkull - 12/06/2020

package com.uldskull.rolegameassistant.fragments.fragment.bars

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar

/**
Class "ProgressBarAnimation"

Class to manage progress bar animation
 */
class ProgressBarAnimation(var progressBar: ProgressBar,var from:Float,var to:Float) : Animation(){

    /**
     * Helper for getTransformation. Subclasses should implement this to apply
     * their transforms given an interpolation value.  Implementations of this
     * method should always replace the specified Transformation or document
     * they are doing otherwise.
     *
     * @param interpolatedTime The value of the normalized time (0.0 to 1.0)
     * after it has been run through the interpolation function.
     * @param t The Transformation object to fill in with the current
     * transforms.
     */
    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        var value:Float = from + (to - from)*interpolatedTime
        progressBar.setProgress(value.toInt())
    }
}