// File EditTextUtil.kt
// @Author pierre.antoine - 14/04/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.core.utils

import android.util.Log
import android.widget.EditText

/**
 *   Class "EditTextUtil" :
 *   Edit text utils to enable or disable edit text
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