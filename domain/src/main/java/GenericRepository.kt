// File GenericRepository.kt
// @Author pierre.antoine - 12/02/2020 - No copyright.

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