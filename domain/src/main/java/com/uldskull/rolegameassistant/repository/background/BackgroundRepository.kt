// BackgroundRepository.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.repository.background

import com.uldskull.rolegameassistant.models.background.DomainBackground
import com.uldskull.rolegameassistant.repository.Repository

/**
    Interface "BackgroundRepository"

    The com.uldskull.rolegameassistant.com.uldskull.rolegameassistant.repository class will be responsible for interacting with the Room database on behalf
    of the ViewModel and will need to provide methods that use the DAO to insert, delete and
    query basic info records.
 **/
interface BackgroundRepository<T> :
Repository<T, DomainBackground>