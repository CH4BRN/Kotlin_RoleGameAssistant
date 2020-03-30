// File CharacterViewModel.kt
// @Author pierre.antoine - 28/03/2020 - No copyright.

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.repository.character.CharacterRepository
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 *   Class "CharacterViewModel" :
 *   Class that handles the characters
 **/
class CharactersViewModel(
    application: Application,
    private val characterRepository: CharacterRepository<LiveData<List<DomainCharacter>>>
) : AndroidViewModel(application) {

    init {
        refreshDataFromRepository()
    }

    /**
     * List of characters
     */
    var characters = characterRepository.getAll()

    /**
     * Refresh the characters list
     */
    private fun refreshDataFromRepository() {
        Log.d("CharacterViewModel", "refreshDataFromRepository")
        viewModelScope.launch {
            try {
                characters = findAll()
                Log.d("CharacterViewModel", "characters size ${characters?.value?.size}")
            } catch (e: Exception) {
                throw e
            }
        }
    }

    /**
     * Find all the characters in using the repository
     */
    private fun findAll(): LiveData<List<DomainCharacter>>? {
        Log.d("CharacterViewModel", "findAll")
        thread(start = true) {
            characters = characterRepository.getAll()
        }
        return characters
    }
// TODO : Fill class.
}