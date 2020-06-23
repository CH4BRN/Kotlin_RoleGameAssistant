// File CharacteristicRepository.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant.repository.characteristic

import com.uldskull.rolegameassistant.models.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
 *   Class "CharacteristicRepository" :
 *   Class to manage characteristics persistence.
 **/
interface CharacteristicRepository<T> :
    GenericRepository<T, DomainCharacteristic>