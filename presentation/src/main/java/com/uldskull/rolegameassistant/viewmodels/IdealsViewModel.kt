// File IdealsViewModel.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.repository.ideal.IdealsRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


/**
 *   Class "IdealsViewModel" :
 *   ViewModel class for ideals.
 **/
class IdealsViewModel(
    application: Application,
    private val idealsRepositoryImpl: IdealsRepository<MutableLiveData<List<DomainIdeal>>>
) : AndroidViewModel(application) {

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                ideals = findAll()
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        }
    }

    private fun findAll(): MutableLiveData<List<DomainIdeal>>? {
        thread(start = true) {
            ideals = idealsRepositoryImpl.getAll()
        }
        return ideals
    }
    /** Ideals to display   **/
    var ideals = idealsRepositoryImpl.getAll()


    init {
/*
        ideals?.value = listOf(
            DomainIdeal(
                idealId = null,
                idealName = "Mechant",
                idealEvilPoints = 100,
                idealGoodPoints = 0,
                isChecked = false
            )

        )
        */


    }
}