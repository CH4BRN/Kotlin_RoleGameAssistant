// File DbRaceWithCharacteristics.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_race

import androidx.room.Embedded
import androidx.room.Relation
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRaceCharacteristic

/**
 *   Class "DbRaceWithCharacteristics" :
 *   TODO: Fill class use.
 **/
data class DbRaceWithCharacteristics(
    @Embedded
    val race: DbRace,
    @Relation(
        parentColumn = "raceId",
        entityColumn = "characteristicId"
    )
    val characteristics: List<DbRaceCharacteristic>
)