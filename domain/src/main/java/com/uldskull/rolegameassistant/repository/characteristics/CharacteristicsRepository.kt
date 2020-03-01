// CharacteristicsRepository.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.repository.characteristics

import com.uldskull.rolegameassistant.models.characteristics.DomainCharacteristics
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
    Interface "CharacteristicsRepository"

    Contract to allow characteristics persistence.
 **/
interface CharacteristicsRepository<T> : GenericRepository<T, DomainCharacteristics>