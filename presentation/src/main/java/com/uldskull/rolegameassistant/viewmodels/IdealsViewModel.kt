// File IdealsViewModel.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.DomainIdeal
import com.uldskull.rolegameassistant.repository.ideal.IdealsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 *   Class "IdealsViewModel" :
 *   ViewModel class for ideals.
 **/
class IdealsViewModel(
    application: Application,
    private val idealsRepositoryImpl: IdealsRepository<LiveData<List<DomainIdeal>>>
) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "IdealsViewModel"
    }

    init {
        refreshDataFromRepository()
    }

    /**
     * Refresh data from repository
     */
    fun refreshDataFromRepository() {
        viewModelScope.launch {
            Log.d(TAG, "refreshDataFromRepository")
            try {
                repositoryIdeals = findAll()

            } catch (e: Exception) {
                Log.e(TAG, "refreshDataFromRepository FAILED")
                e.printStackTrace()
                throw e
            }
        }
    }



    /**
     * Delete an ideal
     */
    fun deleteIdeal(currentIdealToEdit: DomainIdeal): Int? {
        if (currentIdealToEdit == null) {
            throw Exception("Ideal is null")
        }

        var result: Int? = null
        viewModelScope.launch {
            result = idealsRepositoryImpl.deleteOne(currentIdealToEdit)
        }

        return result
    }

    /**
     * Insert an ideal
     */
    fun insertIdeal(domainIdeal: DomainIdeal): Long? {
        var result: Long? = -1
        if (domainIdeal == null) {
            Log.d("DEBUG$TAG", "Ideal is null")
            return result
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                Log.d("DEBUG$TAG", "Ideal is $domainIdeal")
                result = idealsRepositoryImpl.insertOne(domainIdeal)
            }
        }
        return result
    }

    /**
     * Update an ideal
     */
    fun updateIdeal(domainIdeal: DomainIdeal): Int? {
        var result: Int? = -1
        if (domainIdeal == null) {
            return result
        }
        viewModelScope.launch(Dispatchers.IO) {
            result = idealsRepositoryImpl.updateOne(domainIdeal)
        }
        return result
    }


    /**
     * Get all ideals
     */
    fun getAll(): List<DomainIdeal>? {
        return idealsRepositoryImpl.getIdeals()
    }

    /**
     * Find all repository ideals
     */
    private fun findAll(): LiveData<List<DomainIdeal>>? {
        Log.d(TAG, "findAll ideals")
        viewModelScope.launch {
            repositoryIdeals = idealsRepositoryImpl.getAll()
        }
        return repositoryIdeals
    }

    /**
     * Character's ideals
     */
    var characterIdeals = MutableLiveData<List<Long?>>()

    /**
     * Observable selected ideals
     */
    var observableSelectedIdeals = MutableLiveData<List<Long?>>()

    /**
     * Current edited ideal
     */
    var currentIdealToEdit: DomainIdeal? = null

    /** Ideals from repository   **/
    var repositoryIdeals = idealsRepositoryImpl.getAll()


}