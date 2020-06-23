// File OccupationSkillRepository.kt
// @Author pierre.antoine - 23/04/2020 - No copyright.

package com.uldskull.rolegameassistant.repository.skill

import com.uldskull.rolegameassistant.models.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.repository.GenericRepository

/**
 *   Class "OccupationSkillRepository" :
 *   Class to manage DomainSkillToCheck persistence.
 **/
interface SkillToCheckRepository<T> :
    GenericRepository<T, DomainSkillToCheck>