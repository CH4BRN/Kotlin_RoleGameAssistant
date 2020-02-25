// File GenericRepository.kt
// @Author pierre.antoine - 12/02/2020 - No copyright.

<<<<<<< HEAD:domain/src/main/java/GenericRepository.kt
=======
package com.uldskull.rolegameassistant.repository

>>>>>>> e7ef618d5525040e5ea05d137966ac1ecc64df65:domain/src/main/java/com/uldskull/rolegameassistant/repository/GenericRepository.kt
/**
 *   Interface "GenericRepository" :
 *   Interface for generic repository.
 **/
interface GenericRepository<T, U> {
    /** Get all entities    */
    fun getAll(): T

    /** Get one entity by its id    */
    fun getOneById(id: Long?): U

    /** Insert a list of entity */
    fun insertAll(all: List<U>)

    /** Insert one entity   */
    fun insertOne(one: U): Long
}