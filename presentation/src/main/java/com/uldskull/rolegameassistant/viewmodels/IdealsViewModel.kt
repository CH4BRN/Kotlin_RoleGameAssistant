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
     * Get an ideal by its id
     */
    fun getIdealById(id:Long?): DomainIdeal?{
        return if(id != null){
            idealsRepositoryImpl.findOneById(id)
        }else{
            null
        }
    }

    /**
     * Delete an ideal
     */
    fun deleteIdeal(currentIdealToEdit: DomainIdeal):Int {
        if(currentIdealToEdit == null){
            throw Exception("Ideal is null")
        }

         return idealsRepositoryImpl.deleteOne(currentIdealToEdit)
    }

    /**
     * Insert an ideal
     */
     fun insertIdeal(domainIdeal: DomainIdeal):Long?{
         if(domainIdeal == null){
             Log.d("DEBUG$TAG","Ideal is null")
             return 0
         }else{
             Log.d("DEBUG$TAG","Ideal is $domainIdeal")
             return when {
                 domainIdeal.idealId == null -> {
                     Log.d("DEBUG$TAG","Ideal id is null")
                     idealsRepositoryImpl.insertOne(domainIdeal)
                 }
                 idealsRepositoryImpl.findOneById(domainIdeal.idealId) == null -> {
                     Log.d("DEBUG$TAG","Ideal not found")
                     idealsRepositoryImpl.insertOne(domainIdeal)
                 }
                 else -> {
                     Log.d("DEBUG$TAG", "Update ideal")
                     idealsRepositoryImpl.updateOne(domainIdeal)
                     return domainIdeal.idealId
                 }
             }
         }

    }

    /**
     * Get all ideals
     */
    fun getAll():List<DomainIdeal>?{
        return idealsRepositoryImpl.getIdeals()
    }

    /**
     * Find all repository ideals
     */
    private fun findAll(): LiveData<List<DomainIdeal>>? {
        Log.d(TAG, "findAll ideals")
        repositoryIdeals = idealsRepositoryImpl.getAll()

        return repositoryIdeals
    }


    /**
     * Current edited ideal
     */
    var currentIdealToEdit: DomainIdeal? = null

    /** Ideals from repository   **/
    var repositoryIdeals = idealsRepositoryImpl.getAll()

    /**
     * Ideals to display
     */
    var mutableIdeals: MutableLiveData<MutableList<DomainIdeal?>>? = MutableLiveData()



}