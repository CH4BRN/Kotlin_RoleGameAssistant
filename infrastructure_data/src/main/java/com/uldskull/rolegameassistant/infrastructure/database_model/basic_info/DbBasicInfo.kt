// DbBasicInfo.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.basic_info

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.TABLE_NAME_BASIC_INFO
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo

/**
Class "DbBasicInfo"

TODO: Describe class utility.
 */
@Entity(tableName = TABLE_NAME_BASIC_INFO)
class DbBasicInfo(
    @PrimaryKey(autoGenerate = true) val dbBasicInfoId: Long?,
    val dbBasicInfoCharacterName: String? = "name",
    val dbBasicInfoCharacterExperience: Int? = 0,
    val dbBasicInfoCharacterId: Long? = null,
    val dbBasicInfoLevelId: Long? = null
):DbEntity<DomainBasicInfo, DbBasicInfo> {
    companion object {
        /**
         * Converts to database model
         */
        fun from(domainBasicInfo: DomainBasicInfo): DbBasicInfo {
            return DbBasicInfo(
                dbBasicInfoCharacterId = domainBasicInfo.basicInfoCharacterId,
                dbBasicInfoCharacterExperience = domainBasicInfo.basicInfoCharacterExperience,
                dbBasicInfoCharacterName = domainBasicInfo.basicInfoCharacterName,
                dbBasicInfoId = domainBasicInfo.basicInfoId,
                dbBasicInfoLevelId = domainBasicInfo.basicInfoLevelId
            )
        }
    }

    /**
     * Converts to domain model
     */
   override fun toDomain(): DomainBasicInfo {
        return DomainBasicInfo(
            basicInfoId = dbBasicInfoId,
            basicInfoCharacterExperience = dbBasicInfoCharacterExperience,
            basicInfoCharacterId = dbBasicInfoCharacterId,
            basicInfoCharacterName = dbBasicInfoCharacterName,
            basicInfoLevelId = dbBasicInfoLevelId

        )
    }

    override fun List<DbBasicInfo>.asDomainModel(): List<DomainBasicInfo> {
        return map{
            toDomain()
        }
    }

}