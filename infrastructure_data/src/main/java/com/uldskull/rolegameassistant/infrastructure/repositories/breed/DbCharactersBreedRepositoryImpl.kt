// DbCharactersBreedRepositoryImpl.kt created by UldSkull - 29/05/2020

package com.uldskull.rolegameassistant.infrastructure.repositories.breed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.uldskull.rolegameassistant.infrastructure.dao.breed.DbCharactersBreedDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.characterBreeds.DbCharactersBreed
import com.uldskull.rolegameassistant.models.character.breed.charactersBreed.DomainCharactersBreed
import com.uldskull.rolegameassistant.repository.breed.CharactersBreedsRepository

/**
Class "DbCharactersBreedRepositoryImpl"

TODO: Describe class utility.
 */
class DbCharactersBreedRepositoryImpl(
    private val dbCharactersBreedDao: DbCharactersBreedDao
) :
    CharactersBreedsRepository<LiveData<List<DomainCharactersBreed>>> {

    companion object {
        private const val TAG = "DbCharactersBreedRepositoryImpl"
    }

    /**
     * Extension function that converts DB models into domain models.
     */
    private fun List<DbCharactersBreed>.asDomainModel(): List<DomainCharactersBreed> {
        Log.d("DEBUG$TAG", "asDomainModel")
        return map {
            DomainCharactersBreed(
                charactersBreedId = it.characterBreedId,
                displayedBreedId = it.displayedBreedId,
                characterId = it.characterId
            )
        }
    }


    /** Get all entities    */
    override fun getAll(): LiveData<List<DomainCharactersBreed>>? {
        Log.d("DEBUG$TAG", "getAll")
        try {
            //  Transform the dbDisplayedBreeds into domain displayed breeds
            return Transformations.map(dbCharactersBreedDao.getCharactersBreeds()) {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /** Get one entity by its id    */
    override fun findOneById(id: Long?): DomainCharactersBreed? {
        Log.d("DEBUG$TAG", "findOneById")
        var result: DbCharactersBreed
        try {
            result = dbCharactersBreedDao.getCharactersBreedById(id)
        } catch (e: Exception) {
            Log.e(TAG, "findOneById FAILED")
            e.printStackTrace()
            throw e
        }
        return result.toDomain()
    }

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    override fun insertAll(all: List<DomainCharactersBreed>?): List<Long>? {
        Log.d("DEBUG$TAG", "insertAll")
        if ((all != null) && (all.isNotEmpty())) {
            try {
                val result = dbCharactersBreedDao.insertCharactersBreeds(all.map { result ->
                    DbCharactersBreed.from(
                        result
                    )
                })
                Log.d(TAG, "insertAll RESULT = ${result.size}")
                return result
            } catch (e: Exception) {
                Log.e(TAG, "insertAll FAILED")
                e.printStackTrace()
                throw e
            }
        } else {
            Log.d(TAG, "insertAll RESULT = 0")
            return emptyList()
        }
    }

    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    override fun insertOne(one: DomainCharactersBreed?): Long? {
        Log.d("DEBUG$TAG", "insertOne")
        return if (one != null) {
            try {
                val result = dbCharactersBreedDao.insertCharactersBreed(DbCharactersBreed.from(one))
                Log.d(TAG, "insertOne RESULT = $result")
                result
            } catch (e: Exception) {
                Log.e(TAG, "insertOne FAILED")
                e.printStackTrace()
                throw  e
            }
        } else {
            -1
        }
    }

    /** Delete all entities **/
    override fun deleteAll(): Int {
        Log.d("DEBUG$TAG", "deleteAll")
        try {
            return dbCharactersBreedDao.deleteAllCharactersBreeds()
        } catch (e: Exception) {
            Log.e(TAG, "deleteAll FAILED")
            e.printStackTrace()
            throw e
        }
    }

    /**  Update one entity  **/
    override fun updateOne(one: DomainCharactersBreed?): Int? {
        Log.d("DEBUG$TAG", "updateOne")
        try {
            return dbCharactersBreedDao.updateCharactersBreed(DbCharactersBreed.from(one))
        } catch (e: Exception) {
            Log.e("ERROR", "updateOne failed")
            e.printStackTrace()
            throw e
        }
    }

    /**
     * Deletes all the breeds corresponding to  a character.
     */
    override fun deleteById(characterId: Long?) {
        Log.d("DEBUG$TAG", "deleteById")
        try{
            return dbCharactersBreedDao.deleteCharacterBreedsById(characterId)
        }catch (e:Exception){
            Log.e("ERROR", "deleteById failed")
            e.printStackTrace()
            throw e
        }
    }

}