// HealthRepository.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.contracts.repository.health

import com.uldskull.rolegameassistant.models.health.DomainHealth
<<<<<<< HEAD
import GenericRepository
=======
import com.uldskull.rolegameassistant.repository.GenericRepository
>>>>>>> e7ef618d5525040e5ea05d137966ac1ecc64df65

/**
    Interface "HealthRepository"

    The com.uldskull.rolegameassistant.com.uldskull.rolegameassistant.contracts.repository class will be responsible for interacting with the Room database on behalf
    of the ViewModel and will need to provide methods that use the DAO to insert, delete and
    query basic info records.
 **/
interface HealthRepository<T> :
    GenericRepository<T, DomainHealth>