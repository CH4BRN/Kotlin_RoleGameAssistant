// File ProgressionBarViewModel.kt
// @Author pierre.antoine - 12/05/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 *   Class "ProgressionBarViewModel" :
 *   TODO: Fill class use.
 **/
class ProgressionBarViewModel(
    application: Application
) : AndroidViewModel(application) {

    var progression = MutableLiveData<Int>()
}