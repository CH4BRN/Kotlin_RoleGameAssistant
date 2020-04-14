// File EditTextUtil.kt
// @Author pierre.antoine - 14/04/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.util.Log
import android.widget.EditText

/**
 *   Class "EditTextUtil" :
 *   TODO: Fill class use.
 **/
class EditTextUtil {
    companion object {
        private const val TAG = "EditTextUtil"
        /**
         * Disable or enable an EditText
         */
        fun editTextEnabling(editText: EditText) {
            Log.d(TAG, "disableEditText")
            editText.isEnabled = !editText.isEnabled
        }

    }
}