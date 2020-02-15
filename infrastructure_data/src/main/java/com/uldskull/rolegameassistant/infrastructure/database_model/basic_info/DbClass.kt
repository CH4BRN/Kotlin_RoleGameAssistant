// DbClass.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.basic_info

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.TABLE_NAME_CLASS
import com.uldskull.rolegameassistant.infrastructure.database_model.DbEntity
import com.uldskull.rolegameassistant.models.basic_info.DomainClass

/**
Class "DbClass"

Database model for class.
 */
@Entity(tableName = TABLE_NAME_CLASS)
class DbClass(
    @PrimaryKey(autoGenerate = true)val dbClassId: Long? = null,
    val dbClassName: String? = "class") : DbEntity<DomainClass> {
    /**
     * Converts into domain model.
     */
    override fun toDomain(): DomainClass {
        return DomainClass(
            classId = dbClassId,
            className = dbClassName
        )
    }

    companion object{
        /**
         * Converts into database model
         */
        fun from(domainClass: DomainClass):DbClass{
            return DbClass(
                dbClassId = domainClass.classId,
                dbClassName = domainClass.className
            )
        }
    }
}