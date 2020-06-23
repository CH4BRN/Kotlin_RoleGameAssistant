// AbilityScoreRepository.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.repository.characteristic

import com.uldskull.rolegameassistant.models.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
    Interface "AbilityScoreRepository"
    Class to manage DomainRollsCharacteristic persistence.
 **/
interface RollsCharacteristicRepository<T> :
    GenericRepository<T, DomainRollsCharacteristic>