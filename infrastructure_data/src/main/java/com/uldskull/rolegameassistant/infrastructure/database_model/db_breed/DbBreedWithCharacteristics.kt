// File DbBreedWithCharacteristics.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_breed

import androidx.room.Embedded
import androidx.room.Relation
import com.uldskull.rolegameassistant.infrastructure.database_model.DbBreedWithCharacteristics_entityColumn
import com.uldskull.rolegameassistant.infrastructure.database_model.DbBreedWithCharacteristics_parentColumn
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbBreedCharacteristic

/**
 *   Class "DbBreedWithCharacteristics" :
 *   TODO: Fill class use.
 **/
data class DbBreedWithCharacteristics(
    @Embedded
    val breed: DbBreed,
    @Relation(
        parentColumn = DbBreedWithCharacteristics_parentColumn,
        entityColumn = DbBreedWithCharacteristics_entityColumn
    )
    val characteristics: List<DbBreedCharacteristic>
) {
    override fun toString(): String {
        return "\nDbBreedWithCharacteristics breed : $breed \n" +
                "DbBreedWithCharacteristics characteristics size : ${characteristics.size}"
    }
}