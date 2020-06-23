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
 *   Holds a breed with all its characteristics
 **/
data class DbDisplayedBreedWithCharacteristics(
    /**
     * The breed
     */
    @Embedded
    val displayedBreed: DbDisplayedBreed,
    /**
     * The characteristics
     */
    @Relation(
        parentColumn = FIELD_BREED_ID,
        entityColumn = FIELD_CHARACTERISTIC_BREED_ID
    )
    val characteristics: List<DbBreedCharacteristic>
) {
    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "DbDisplayedBreedWithCharacteristics(displayedBreed=$displayedBreed, characteristics=$characteristics)"
    }
}