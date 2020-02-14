// File DbBackground.kt
// @Author pierre.antoine - 14/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.background

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.TABLE_NAME_BACKGROUND

/**
 *   Class "DbBackground" :
 *   Database model for background
 **/
@Entity(tableName = TABLE_NAME_BACKGROUND)
data class DbBackground(
    @PrimaryKey(autoGenerate = true) val dbBackgroundId: Long?

)