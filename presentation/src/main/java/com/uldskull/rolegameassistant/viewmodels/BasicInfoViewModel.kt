// BasicInfoViewModel.kt created by UldSkull - 22/06/2020

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed

/**
Class "BasicInfoViewModel"

ViewModel for basic info
 */
class BasicInfoViewModel(application: Application):AndroidViewModel(application){
    /**
     * Displayed breeds
     */
    var displayedBreeds: MutableList<DomainDisplayedBreed?>? = mutableListOf()
        set(value) {
            field = value
        }
}