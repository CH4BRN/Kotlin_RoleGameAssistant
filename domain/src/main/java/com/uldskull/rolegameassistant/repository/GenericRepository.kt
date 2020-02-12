// File GenericRepository.kt
// @Author pierre.antoine - 12/02/2020 - No copyright.

package com.uldskull.rolegameassistant.repository

/**
 *   Interface "GenericRepository" :
 *   Interface for generic repository.
 **/
interface GenericRepository<T, U> {
    fun getAll(): T
    fun getOne(id: Long?): U
    fun insertAll(all: List<U>)
    fun insertOne(one: U): Long
}