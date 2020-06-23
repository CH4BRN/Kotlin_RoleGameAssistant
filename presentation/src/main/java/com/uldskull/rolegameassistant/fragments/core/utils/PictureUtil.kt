// File ImageUtil.kt
// @Author pierre.antoine - 20/04/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.core.utils

import android.graphics.Bitmap

/**
 *   Class "PictureUtil" :
 *   Allow to scale a picture into a bitmap.
 **/
class PictureUtil {
    companion object{
        /**
         * resize picture
         */
        fun resizePicture(bitmap: Bitmap, width: Int, height: Int): Bitmap {
            return Bitmap.createScaledBitmap(bitmap, width, height, false)
        }
    }
}