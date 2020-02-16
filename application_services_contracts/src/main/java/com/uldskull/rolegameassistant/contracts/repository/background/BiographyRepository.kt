// BiographyRepository.kt created by UldSkull - 13/02/2020

package com.uldskull.rolegameassistant.contracts.repository.background

import com.uldskull.rolegameassistant.models.background.DomainBiography
import com.uldskull.rolegameassistant.contracts.repository.GenericRepository

/**
Interface "BiographyRepository"

The repository class will be responsible for interacting with the Room database on behalf
of the ViewModel and will need to provide methods that use the DAO to insert, delete and
query basic info records.
 **/
interface BiographyRepository<T> : GenericRepository<T, DomainBiography>