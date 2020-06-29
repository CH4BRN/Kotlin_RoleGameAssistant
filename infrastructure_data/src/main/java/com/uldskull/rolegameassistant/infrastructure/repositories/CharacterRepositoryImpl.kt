// CharacterRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.character.DbCharacterDao
import com.uldskull.rolegameassistant.infrastructure.dao.character.DbCharacterWithDbFilledSkillsDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_character.DbCharacter
import com.uldskull.rolegameassistant.infrastructure.database_model.db_character.DbCharacterWithDbSkills
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.DomainCharacterWithIdeals
import com.uldskull.rolegameassistant.models.character.DomainCharacterWithSkills
import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.repository.character.CharacterRepository

/**
Class "CharacterRepositoryImpl"

Insert and get Character from database.
 */
class CharacterRepositoryImpl(
    /**
     * Character dao
     */
    private val dbCharacterDao: DbCharacterDao,
    /**
     * Character with skills dao
     */
    private val dbCharacterWithDbFilledSkillsDao: DbCharacterWithDbFilledSkillsDao
) :
//CharacterRepository<LiveData<List<DomainCharacter?>?>>
    CharacterRepository<LiveData<List<DomainCharacter>>> {

    companion object {
        private const val TAG = "CharacterRepositoryImpl"
    }

    /** Get all domain characters   */
    override fun getAll(): LiveData<List<DomainCharacter>> {
        Log.d(TAG, "getAll")
        return try {
            Transformations.map(dbCharacterDao.getCharacters()) {
                return@map it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /**
     * Converts a list of database entities into domain entities
     */
    private fun List<DbCharacter?>.asDomainModel(): List<DomainCharacter> {
        Log.d(TAG, "asDomainModel")
        var character = map {
            DomainCharacter(
                characterId = it?.characterId,
                characterName = it?.characterName,
                characterAge = it?.characterAge,
                characterGender = it?.characterGender,
                characterBiography = it?.characterBiography,
                characterHealthPoints = it?.characterHealthPoints,
                characterIdeaPoints = it?.characterIdeaPoints,
                characterAlignment = it?.characterAlignment,
                characterEnergyPoints = it?.characterEnergyPoints,
                characterHeight = it?.characterHeight,
                characterPictureUri = it?.characterPictureUri,
                characterBonds = it?.characterBonds?.map { dbBond -> dbBond?.toDomain() }
                    ?.toMutableList(),
                characterIdeals = it?.characterSelectedIdeals?.toMutableList(),
                characterStrength = it?.characterStrength?.toDomain(),
                characterSize = it?.characterSize?.toDomain(),
                characterPower = it?.characterPower?.toDomain(),
                characterIntelligence = it?.characterIntelligence?.toDomain(),
                characterDexterity = it?.characterDexterity?.toDomain(),
                characterConstitution = it?.characterConstitution?.toDomain(),
                characterAppearance = it?.characterAppearance?.toDomain(),
                characterEducation = it?.characterEducation?.toDomain(),
                characterWeight = it?.characterWeight,
                characterKnow = it?.characterKnow,
                characterLuck = it?.characterLuck,
                characterSanity = it?.characterSanity,
                characterBaseHealthPoints = it?.characterBaseHealth,
                characterBreedBonus = it?.characterBreedBonus,
                characterSelectedOccupationSkill = it?.characterSelectedOccupationSkill?.toMutableList(),
                characterOccupation = it?.characterOccupation?.toDomain(),
                characterBreeds = it?.characterSelectedBreeds?.toMutableList(),
                characterSelectedHobbiesSkill = it?.characterSelectedHobbiesSkill?.toMutableList()
            )
        }
        if (character != null) {
            return character!!
        } else {
            throw Exception("ERROR : Character is null")
        }
    }

    /** Get one entity by its id    */
    override suspend fun findOneById(id: Long?): DomainCharacter? {
        Log.d(TAG, "findOneById")

        var model = dbCharacterDao.getCharacterById(id)
        return if (model == null) {
            model
        } else
            model.toDomain()
    }


    /** Insert a list of entity */
    override suspend fun insertAll(all: List<DomainCharacter>?): List<Long> {
        Log.d(TAG, "insertAll")
        if ((all != null) && (all.isNotEmpty())) {

            return dbCharacterDao.insert(all.map { c ->
                DbCharacter.from(c)
            })
        }
        return emptyList()

    }

    /**  Update one entity  **/
    override suspend fun updateOne(one: DomainCharacter?): Int? {
        Log.d(TAG, "updateOne")

        try {
            return dbCharacterDao.update(DbCharacter.from(one))
        } catch (e: Exception) {
            Log.e(TAG, "updateOne FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /** Insert one entity   */
    override suspend fun insertOne(one: DomainCharacter?): Long {
        Log.d(TAG, "insertOne")

        return if (one != null) {
            try {
                val result = dbCharacterDao.insert(DbCharacter.from(one))
                Log.d(TAG, "insertOne RESULT = $result")
                result
            } catch (e: Exception) {
                Log.e(TAG, "insertOne FAILED")
                throw e
            }
        } else {
            -1
        }
    }

    /** Delete all entities **/
    override suspend fun deleteAll(): Int {
        Log.d(TAG, "deleteAll")
        return dbCharacterDao.deleteAll()
    }

    /**
     * Find the corresponding character with all its ideals
     */
    override suspend fun findOneWithIdeals(id: Long?): DomainCharacterWithIdeals? {
        Log.d(TAG, "findOneWithIdeals")

        return null
    }

    /**
     * Find one with it's occupation skills
     */
    override suspend fun findOneWithOccupationSkills(id: Long?): DomainCharacterWithSkills? {
        val entities: List<DbCharacterWithDbSkills> =
            dbCharacterWithDbFilledSkillsDao.getCharacterWithSkills()

        val theOne =
            entities.find { characterWithDbSkills -> characterWithDbSkills.character.characterId == id }

        if (theOne != null) {
            val character = theOne.character

            val skills = theOne.skills

            if (character != null) {
                val list: MutableList<DomainSkillToFill> = mutableListOf()
                Log.d("DEBUG$TAG", "Skills : ${skills.size}")
                skills.forEach {
                    Log.d("DEBUG$TAG", "Skill : ${it.filledSkillName}")
                    list.add(it.toDomain())
                }
                Log.d("DEBUG$TAG", "Skills : ${list.size}")

                return DomainCharacterWithSkills(
                    character = character.toDomain(),
                    skills = list
                )
            }
            return null
        }
        return null
    }

    /**
     * Delete one character
     */
    override suspend fun deleteOneCharacter(domainCharacter: DomainCharacter): Int {
        if (domainCharacter == null) {
            return 0
        }
        return dbCharacterDao?.delete(DbCharacter.from(domainCharacter))
    }


}