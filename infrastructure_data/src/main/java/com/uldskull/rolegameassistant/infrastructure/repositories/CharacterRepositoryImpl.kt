// CharacterRepositoryImpl.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.infrastructure.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.DbCharacterDao
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCharacter
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.repository.character.CharacterRepository

/**
Class "CharacterRepositoryImpl"

Insert and get Character from database.
 */
class CharacterRepositoryImpl(
    private val dbCharacterDao: DbCharacterDao
) :
    CharacterRepository<LiveData<List<DomainCharacter>>> {
    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainCharacter>>? {
        try {
            return Transformations.map(dbCharacterDao.getCharacters()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    private fun List<DbCharacter>.asDomainModel(): List<DomainCharacter> {
        return map {
            DomainCharacter(
                characterId = it.characterId,
                characterName = it.characterName,
                characterAge = it.characterAge,
                characterGender = it.characterGender,
                characterBiography = it.characterBiography,
                characterBreed = it.characterBreed?.toDomain(),
                characterHealthPoints = it.characterHealthPoints,
                characterIdeaPoints = it.characterIdeaPoints,
                characterAlignment = it.characterAlignment,
                characterEnergyPoints = it.characterEnergyPoints,
                characterHeight = it.characterHeight,
                characterPictureUri = it.characterPictureUri
            )
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainCharacter? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert a list of entity */
    override fun insertAll(all: List<DomainCharacter>?): List<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Insert one entity   */
    override fun insertOne(one: DomainCharacter?): Long {
        Log.d("CharacterRepositoryImpl", "insertOne")
        return if (one != null) {
            try {
                val result = dbCharacterDao.insertCharacter(DbCharacter.from(one))
                Log.d("CharacteristicRepositoryImpl", "insertOne RESULT = $result")
                result
            } catch (e: Exception) {
                throw e
            }
        } else {
            -1
        }
    }

    /** Delete all entities **/
    override fun deleteAll(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}