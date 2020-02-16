// HealthRepository.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.contracts.repository.health

import com.uldskull.rolegameassistant.models.health.DomainHealth
import com.uldskull.rolegameassistant.contracts.repository.GenericRepository

/**
    Interface "HealthRepository"

    The com.uldskull.rolegameassistant.com.uldskull.rolegameassistant.contracts.repository class will be responsible for interacting with the Room database on behalf
    of the ViewModel and will need to provide methods that use the DAO to insert, delete and
    query basic info records.
 **/
interface HealthRepository<T>: GenericRepository<T, DomainHealth>