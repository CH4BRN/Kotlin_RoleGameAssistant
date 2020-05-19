// File ImageUtil.kt
// @Author pierre.antoine - 20/04/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.graphics.Bitmap

/**
 *   Class "ImageUtil" :
 *   TODO: Fill class use.
 **/
class ImageUtil {
    companion object{
        fun resizeImage(bitmap: Bitmap, width: Int, height: Int): Bitmap {
            return Bitmap.createScaledBitmap(bitmap, width, height, false)
        }
    }
}