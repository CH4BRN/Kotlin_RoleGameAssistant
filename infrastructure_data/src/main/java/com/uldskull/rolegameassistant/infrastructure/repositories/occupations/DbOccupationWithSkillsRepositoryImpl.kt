// DbOccupationWithSkillsRepositoryImpl.kt created by UldSkull - 29/06/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.occupations

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.occupation.DbOccupationDbSkillDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupation
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupationAndDbSkillCrossRef
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupationWithDbSkills
import com.uldskull.rolegameassistant.models.occupation.DomainOccupationWithSkills
import com.uldskull.rolegameassistant.repository.occupations.OccupationWithSkillsRepository

/**
Class "DbOccupationWithSkillsRepositoryImpl"

Repository for dbOccupationWithSkills
 */
class DbOccupationWithSkillsRepositoryImpl(
    /**
     * Occupation skill dao
     */
    private val dbOccupationDbSkillDao: DbOccupationDbSkillDao
) : OccupationWithSkillsRepository<LiveData<List<DomainOccupationWithSkills>>> {

    companion object {
        private const val TAG = "dbOccupationDbSkillDao"
    }

    /**
     * Inserts a relation between occupation and skills
     */
    override suspend fun insertOccupationAndSkillCross(occupationId: Long?, skillId: Long): Long {
        Log.d(TAG, "insertOccupationAndSkillCross")
        return try {
            if (occupationId != null && skillId != null) {
                dbOccupationDbSkillDao.insertCross(
                    DbOccupationAndDbSkillCrossRef(
                        occupationId = occupationId,
                        skillId = skillId
                    )
                )
            } else {
                -1
            }

        } catch (e: Exception) {
            Log.e(TAG, "insertOccupationAndSkillCross FAILED")
            e.printStackTrace()
            throw e
        }
    }

    private fun asDomainModel(list: List<DbOccupationWithDbSkills>): List<DomainOccupationWithSkills> {
        var domainModelList: MutableList<DomainOccupationWithSkills> = mutableListOf()
        list.forEach {
            var occupation: DbOccupation = it?.occupation
            var skills = it.skills
            domainModelList.add(DomainOccupationWithSkills(
                occupation = occupation.toDomain(),
                skills = skills.map { s -> s.toDomain() }
            ))
        }
        return domainModelList
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainOccupationWithSkills>>? {
        return Transformations.map(dbOccupationDbSkillDao.getOccupationsWithSkills()) {
            asDomainModel(it)
        }
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override suspend fun insertAll(all: List<DomainOccupationWithSkills>?): List<Long>? {
        TODO("Not yet implemented")
    }

    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    override suspend fun insertOne(one: DomainOccupationWithSkills?): Long? {
        TODO("Not yet implemented")
    }

    /** Delete all entities **/
    override suspend fun deleteAll(): Int? {
        TODO("Not yet implemented")
    }

    /**  Update one entity  **/
    override suspend fun updateOne(one: DomainOccupationWithSkills?): Int? {
        TODO("Not yet implemented")
    }
}