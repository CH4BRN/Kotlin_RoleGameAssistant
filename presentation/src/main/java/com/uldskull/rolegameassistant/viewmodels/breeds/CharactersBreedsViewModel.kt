// CharactersBreedsViewModel.kt created by UldSkull - 29/05/2020

package com.uldskull.rolegameassistant.viewmodels.breeds

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.breed.charactersBreed.DomainCharactersBreed
import com.uldskull.rolegameassistant.repository.breed.CharactersBreedsRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
Class "CharactersBreedsViewModel"

ViewModel for character's breeds.
 */
class CharactersBreedsViewModel(
    application: Application,
    private val charactersBreedRepositoryImpl: CharactersBreedsRepository<LiveData<List<DomainCharactersBreed>>>
) : AndroidViewModel(application) {
    var observedCharactersBreeds: LiveData<List<DomainCharactersBreed>>? =
        charactersBreedRepositoryImpl.getAll()



    companion object {
        private const val TAG = "CharactersBreedsViewModel"
    }

    init {
        refreshDataFromRepository()
    }

    /**
     * Refresh the data from the repository
     */
    private fun refreshDataFromRepository() {
        Log.d("DEBUG$TAG", "refreshDataFromRepository")
        viewModelScope.launch {
            try {
                //  Get the data
                observedCharactersBreeds = findAll()
            } catch (e: Exception) {
                Log.e("ERROR", "refreshDataFromRepository failed")
                e.printStackTrace()
                throw e
            }
        }
    }

    fun deleteById(characterId:Long?){
        var result = charactersBreedRepositoryImpl?.deleteById(characterId)
    }

    /**
     * Find the data into the repository
     */
    private fun findAll(): LiveData<List<DomainCharactersBreed>>? {
        Log.d("DEBUG$TAG", "findAll")
        thread(start = true) {
            observedCharactersBreeds = charactersBreedRepositoryImpl.getAll()
        }
        return observedCharactersBreeds
    }

    /**
     * Save one character's breed
     */
    fun saveOne(domainCharactersBreed: DomainCharactersBreed): Long? {
        Log.d("DEBUG$TAG", "saveOne")
        var result: Long? = charactersBreedRepositoryImpl.insertOne(domainCharactersBreed)
        Log.d("DEBUG$TAG", "INSERTED $result")
        return result
    }

    private val lock = java.lang.Object()

    /**
     * Save all character's breeds
     */
    fun saveAll(domainCharactersBreeds: List<DomainCharactersBreed>?): List<Long>? =
        synchronized(lock) {
            Log.d(TAG, "saveAll")


            var result: List<Long>? =
                charactersBreedRepositoryImpl.insertAll(domainCharactersBreeds)
            Log.d(TAG, "INSERTED $result")
            lock.notifyAll()
            return result
        }


    /**
     * Find one breed by its ID
     */
    fun findBreedWithId(breedId: Long?): DomainCharactersBreed? {
        Log.d(TAG, "findBreedWithId with id : $breedId")

        var result = charactersBreedRepositoryImpl.findOneById(breedId)
        Log.d(TAG, "findBreedWithId result" + result?.charactersBreedId)

        return result
    }

    fun updateBreed(breed: DomainCharactersBreed): Int? {
        Log.d("DEBUG$TAG", "updateBreed")
        if (breed != null) {
            return charactersBreedRepositoryImpl.updateOne(breed)
        } else {
            return 0
        }
    }

}