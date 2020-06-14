// File DbBreedWithCharacteristics.kt
// @Author pierre.antoine - 30/03/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds

import androidx.room.Embedded
import androidx.room.Relation
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_BREED_ID
import com.uldskull.rolegameassistant.infrastructure.IdFieldName.FIELD_CHARACTERISTIC_BREED_ID
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbBreedCharacteristic

/**
 *   Class "DbBreedWithCharacteristics" :
 *   TODO: Fill class use.
 **/
data class DbDisplayedBreedWithCharacteristics(
    @Embedded
    val displayedBreed: DbDisplayedBreed,
    @Relation(
        parentColumn = FIELD_BREED_ID,
        entityColumn = FIELD_CHARACTERISTIC_BREED_ID
    )
    val characteristics: List<DbBreedCharacteristic>
) {
    override fun toString(): String {
        return "DbDisplayedBreedWithCharacteristics(displayedBreed=$displayedBreed, characteristics=$characteristics)"
    }
}