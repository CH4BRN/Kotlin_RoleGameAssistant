/** File DatabaseModelContract.kt
 *   @Author pierre.antoine - 02/04/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.infrastructure.database_model

import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupation
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbOccupationSkill

const val DbBreedWithCharacteristics_parentColumn = "breedId"
const val DbBreedWithCharacteristics_entityColumn = "characteristicBreedId"
const val DbCharacterWithIdeals_parentColumn = "characterId"
const val DbCharacterWithIdeals_entityColumn = "idealCharacterId"
const val DbOccupation_id = "occupationId"
const val DbOccupationSkill_id = "skillId"