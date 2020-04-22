// File JobsViewModel.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.models.character.DomainOccupation
import com.uldskull.rolegameassistant.repository.occupations.OccupationsRepository

/**
 *   Class "JobsViewModel" :
 *   TODO: Fill class use.
 **/
class JobsViewModel(
    application: Application,
    private val occupationsRepositoryImpl: OccupationsRepository<MutableLiveData<List<DomainOccupation>>>
) : AndroidViewModel(application) {
    companion object{
        private const val TAG = "JobsViewModel"
    }
    var observedJobs = occupationsRepositoryImpl.getAll()
// TODO : Fill class.
}