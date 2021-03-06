// CharacterRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.character.DbCharacterDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_character.DbCharacter
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.character.DomainCharacterWithIdeals
import com.uldskull.rolegameassistant.repository.character.CharacterRepository

/**
Class "CharacterRepositoryImpl"

Insert and get Character from database.
 */
class CharacterRepositoryImpl(
    private val dbCharacterDao: DbCharacterDao
) :
    CharacterRepository<LiveData<List<DomainCharacter>>> {

    companion object {
        private const val TAG = "CharacterRepositoryImpl"
    }

    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainCharacter>>? {
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

    private fun List<DbCharacter>.asDomainModel(): List<DomainCharacter> {
        Log.d(TAG, "asDomainModel")
        return map {
            DomainCharacter(
                characterId = it.characterId,
                characterName = it.characterName,
                characterAge = it.characterAge,
                characterGender = it.characterGender,
                characterBiography = it.characterBiography,
                characterBreeds = it.characterBreeds?.map { dbBreed -> dbBreed?.toDomain() }?.toMutableList(),
                characterHealthPoints = it.characterHealthPoints,
                characterIdeaPoints = it.characterIdeaPoints,
                characterAlignment = it.characterAlignment,
                characterEnergyPoints = it.characterEnergyPoints,
                characterHeight = it.characterHeight,
                characterPictureUri = it.characterPictureUri,
                characterBonds = it.characterBonds?.map { dbBond -> dbBond?.toDomain() }?.toMutableList(),
                characterIdeals = it.characterIdeals?.map { dbIdeal -> dbIdeal?.toDomain() }?.toMutableList(),
                characterStrength = it.characterStrength?.toDomain(),
                characterSize = it.characterSize?.toDomain(),
                characterPower = it.characterPower?.toDomain(),
                characterIntelligence = it.characterIntelligence?.toDomain(),
                characterDexterity = it.characterDexterity?.toDomain(),
                characterConstitution = it.characterConstitution?.toDomain(),
                characterAppearance = it.characterAppearance?.toDomain(),
                characterEducation = it.characterEducation?.toDomain()
            )
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainCharacter? {
        Log.d(TAG, "findOneById")
        try {
            return dbCharacterDao.getCharacterById(id).toDomain()
        } catch (e: Exception) {
            Log.e(TAG, "findOneById FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainCharacter>?): List<Long> {
        Log.d(TAG, "insertAll")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainCharacter?): Int? {
        Log.d(TAG, "updateOne")

        try {
            return dbCharacterDao.updateCharacter(DbCharacter.from(one))
        } catch (e: Exception) {
            Log.e(TAG, "updateOne FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainCharacter?): Long {
        Log.d(TAG, "insertOne")

        return if (one != null) {
            try {
                val result = dbCharacterDao.insertCharacter(DbCharacter.from(one))
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
    override fun deleteAll(): Int {
        Log.d(TAG, "deleteAll")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Find the corresponding character with all its ideals
     */
    override fun findOneWithIdeals(id: Long?): DomainCharacterWithIdeals? {
        Log.d(TAG, "findOneWithIdeals")

        return null
    }


}