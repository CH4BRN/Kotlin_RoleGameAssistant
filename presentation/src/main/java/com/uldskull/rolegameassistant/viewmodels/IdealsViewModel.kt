// File IdealsViewModel.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.DomainIdeal
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


     fun insertIdeal(domainIdeal: DomainIdeal){
       if(idealsRepositoryImpl?.findOneById(domainIdeal.idealId) == null){
           idealsRepositoryImpl?.insertOne(domainIdeal)
       }else{
           idealsRepositoryImpl?.updateOne(domainIdeal)
       }
    }




    private fun findAll(): LiveData<List<DomainIdeal>>? {
        Log.d(TAG, "findAll ideals")
        repositoryIdeals = idealsRepositoryImpl.getAll()

        return repositoryIdeals
    }

    var currentIdealToEdit: DomainIdeal? = null

    /** Ideals to display   **/
    var repositoryIdeals = idealsRepositoryImpl.getAll()

    var mutableIdeals: MutableLiveData<MutableList<DomainIdeal?>>? = MutableLiveData()



}