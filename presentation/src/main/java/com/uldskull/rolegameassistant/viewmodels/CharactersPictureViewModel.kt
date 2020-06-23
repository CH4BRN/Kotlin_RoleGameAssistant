// CharactersPictureViewModel.kt created by UldSkull - 12/06/2020

package com.uldskull.rolegameassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
Class "CharactersPictureViewModel"

ViewModel for character picture
 */
class CharactersPictureViewModel(
    application: Application
) : AndroidViewModel(application) {

    /**
     * Observable picture uri
     */
    val pictureUri: MutableLiveData<String> = MutableLiveData()

}