// CustomCompanion.kt created by UldSkull - 15/03/2020
package com.uldskull.rolegameassistant.fragments.core

import android.app.Activity

/**
Class "CustomCompanion"

Custom companion class
 */
abstract class CustomCompanion {
    /**
     * Singleton
     */
    abstract fun newInstance(activity: Activity): CustomFragment
}