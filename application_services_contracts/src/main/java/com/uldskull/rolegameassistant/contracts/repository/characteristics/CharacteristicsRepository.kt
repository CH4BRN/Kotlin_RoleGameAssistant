// CharacteristicsRepository.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.contracts.repository.characteristics

import com.uldskull.rolegameassistant.models.characteristics.DomainCharacteristics
<<<<<<< HEAD
import GenericRepository
=======
import com.uldskull.rolegameassistant.repository.GenericRepository
>>>>>>> e7ef618d5525040e5ea05d137966ac1ecc64df65

/**
    Interface "CharacteristicsRepository"

    Contract to allow characteristics persistence.
 **/
interface CharacteristicsRepository<T> :
    GenericRepository<T, DomainCharacteristics>