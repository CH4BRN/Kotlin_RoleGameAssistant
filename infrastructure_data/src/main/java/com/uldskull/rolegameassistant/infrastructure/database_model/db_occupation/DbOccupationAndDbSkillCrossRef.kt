// File OccupationAndSkillCrossRef.kt
// @Author pierre.antoine - 24/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation

import androidx.annotation.NonNull
import androidx.room.Entity
import com.uldskull.rolegameassistant.infrastructure.database_model.DbOccupationSkill_id
import com.uldskull.rolegameassistant.infrastructure.database_model.DbOccupation_id

/**
 *   Class "OccupationAndSkillCrossRef" :
 *   TODO: Fill class use.
 **/
@Entity(primaryKeys = [DbOccupation_id, DbOccupationSkill_id])
data class DbOccupationAndDbSkillCrossRef(
    @NonNull
    val occupationId: Long,
    @NonNull
    val skillId: Long
)