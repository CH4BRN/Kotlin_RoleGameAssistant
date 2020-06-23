// File RaceCharacteristicRepository.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.repository.characteristic

import com.uldskull.rolegameassistant.models.characteristic.DomainBreedsCharacteristic
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
 *   Interface "RaceCharacteristicRepository" :
 *   Class to manage breed's characteristic persistence.
 **/
interface BreedsCharacteristicRepository<T> :
    GenericRepository<T, DomainBreedsCharacteristic>