// File CharacterViewModel.kt
// @Author pierre.antoine - 28/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels.character

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.repository.character.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 *   Class "CharacterViewModel" :
 *   Class that handles the characters
 **/
class CharactersViewModel(
    application: Application,
    private val characterRepository: CharacterRepository<LiveData<List<DomainCharacter?>?>>
) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "CharactersViewModel"
    }

    init {
        refreshDataFromRepository()
    }

    /**
     * Current selected character.
     */
    var selectedCharacter: DomainCharacter? = null

    /**
     * List of characters
     */
    var repositoryCharacters: LiveData<List<DomainCharacter?>?>? = characterRepository.getAll()

    /**
     * Refresh the characters list
     */
    private fun refreshDataFromRepository() {
        Log.d("DEBUG$TAG", "refreshDataFromRepository")
        viewModelScope.launch {
            try {
                repositoryCharacters = findAll()
                Log.d(TAG, "characters size ${repositoryCharacters?.value?.size}")
            } catch (e: Exception) {
                Log.e("Error", "${e.stackTrace}")
                throw e
            }
        }
    }


    /**
     * Find all the characters in using the repository
     */
    private fun findAll(): LiveData<List<DomainCharacter?>?>? {
        Log.d(TAG, "findAll")
        thread(start = true) {
            repositoryCharacters = characterRepository.getAll()
        }
        return repositoryCharacters
    }


    /**
     * Delete one character
     */
    fun deleteOne(domainCharacter: DomainCharacter?): Int {
        var result = 0
        if (domainCharacter == null) {
            return result
        }
        viewModelScope.launch(Dispatchers.IO) {
            result = characterRepository.deleteOneCharacter(domainCharacter)
        }
        return result
    }

    /**
     * Update one character
     */
    fun updateOne(domainCharacter: DomainCharacter?): Int? {
        var result: Int? = 0
        if (domainCharacter == null) {
            return result
        }
        viewModelScope.launch(Dispatchers.IO) {
            result = characterRepository.updateOne(domainCharacter)
        }
        return result

    }
}