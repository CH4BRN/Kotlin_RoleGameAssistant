// File ProgressBarViewModel.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 *   Class "ProgressBarViewModel" :
 *   Handle progress bar progression.
 **/
class ProgressBarViewModel(application: Application): AndroidViewModel(application) {


    var progressStatus = MutableLiveData<Int>()

    init {
        progressStatus.value = 0
    }
}