// SkillRepository.kt created by UldSkull - 11/02/2020

package com.uldskull.rolegameassistant.contracts.repository.skill

import com.uldskull.rolegameassistant.models.skill.DomainSkill
import com.uldskull.rolegameassistant.contracts.repository.GenericRepository

/**
Interface "SkillRepository"

The repository class will be
responsible for interacting with the Room database on behalf
of the ViewModel and will need to provide methods that use the DAO to insert, delete and
query basic info records.
 **/
interface SkillRepository<T> : GenericRepository<T, DomainSkill>