// File GenericRepository.kt
// @Author pierre.antoine - 12/02/2020 - No copyright.

package com.uldskull.rolegameassistant.repository

/**
 *   Interface "GenericRepository" :
 *   Interface for generic repository.
 *   The repository class will be responsible for interacting with
 *   the Room database on behalf of the ViewModel and will need to provide methods that use
 *   the DAO to insert, delete and query basic info records.
 *   With the exception of the getAllBasicInfo() DAO method
 *   (which returns a LiveData object)
 *   these database operations will need to be performed on separate threads from the main
 *   thread using the AsyncTask class.
 **/
interface GenericRepository<T, U> {
    /** Get all entities    */
    fun getAll(): T?

    /** Get one entity by its id    */
    fun findOneById(id: Long?): U?

    /** Insert a list of entity - it should return long[] or List<Long>.*/
    fun insertAll(all: List<U>?): List<Long>?

    /** Insert one entity  -  it can return a long, which is the new rowId for the inserted item.*/
    fun insertOne(one: U?): Long?

    /** Delete all entities **/
    fun deleteAll(): Int

    /**  Update one entity  **/
    fun updateOne(one: U?): Int?
}