// File IdealRepository.kt
// @Author pierre.antoine - 06/04/2020 - No copyright.

package com.uldskull.rolegameassistant.repository.ideal

import com.uldskull.rolegameassistant.models.DomainIdeal
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
 *   Interface "IdealRepository" :
 *   Class to manage DomainIdeal persistence.
**/
interface IdealsRepository<T> :
    GenericRepository<T, DomainIdeal> {

    fun getIdeals():List<DomainIdeal>

    fun deleteOne(ideal: DomainIdeal):Int

}