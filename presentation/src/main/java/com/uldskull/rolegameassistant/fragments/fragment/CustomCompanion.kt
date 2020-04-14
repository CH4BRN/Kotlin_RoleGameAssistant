// CustomCompanion.kt created by UldSkull - 15/03/2020
package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity

/**
Class "CustomCompanion"

Custom companion class
 */
abstract class CustomCompanion {
    abstract fun newInstance(activity: Activity): CustomFragment
}