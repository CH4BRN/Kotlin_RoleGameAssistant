// AbilityScoreRepository.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.contracts.repository.ability_score

import com.uldskull.rolegameassistant.models.ability_score.DomainAbilityScore
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
    Interface "AbilityScoreRepository"

    The repository class will be responsible for interacting with
    the Room database on behalf of the ViewModel and will need to provide methods that use
    the DAO to insert, delete and query basic info records.
    With the exception of the getAllBasicInfo() DAO method
    (which returns a LiveData object)
    these database operations will need to be performed on separate threads from the main
    thread using the AsyncTask class.
 **/
interface AbilityScoreRepository<T> :
    GenericRepository<T, DomainAbilityScore>