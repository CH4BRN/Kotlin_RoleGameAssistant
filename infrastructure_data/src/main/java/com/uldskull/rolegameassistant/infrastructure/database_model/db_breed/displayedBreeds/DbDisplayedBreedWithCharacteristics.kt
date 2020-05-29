// File DbBreedWithCharacteristics.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds

import androidx.room.Embedded
import androidx.room.Relation
import com.uldskull.rolegameassistant.infrastructure.database_model.DbBreedWithCharacteristics_entityColumn
import com.uldskull.rolegameassistant.infrastructure.database_model.DbBreedWithCharacteristics_parentColumn
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds.DbDisplayedBreed
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbBreedCharacteristic

/**
 *   Class "DbBreedWithCharacteristics" :
 *   TODO: Fill class use.
 **/
data class DbDisplayedBreedWithCharacteristics(
    @Embedded
    val displayedBreed: DbDisplayedBreed,
    @Relation(
        parentColumn = DbBreedWithCharacteristics_parentColumn,
        entityColumn = DbBreedWithCharacteristics_entityColumn
    )
    val characteristics: List<DbBreedCharacteristic>
) {
    override fun toString(): String {
        return "\nDbBreedWithCharacteristics breed : $displayedBreed \n" +
                "DbBreedWithCharacteristics characteristics size : ${characteristics.size}"
    }
}