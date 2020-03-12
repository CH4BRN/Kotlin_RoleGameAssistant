// File IdealsViewModel.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.ideals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.models.ideal.DomainIdeal

/**
 *   Class "IdealsViewModel" :
 *   ViewModel class for ideals.
 **/
class IdealsViewModel(application: Application) : AndroidViewModel(application) {
    /** Ideals to display   **/
    var ideals = MutableLiveData<List<DomainIdeal>>()


    init {

        ideals.value = listOf(
            DomainIdeal(
                value = application.getString(R.string.lorem_ipsum_short),
                evilPoints = 10,
                goodPoints = 100
            ),
            DomainIdeal(
                value = application.getString(R.string.lorem_ipsum_short),
                evilPoints = 100,
                goodPoints = 10
            )

        )


    }
}