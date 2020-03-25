// File RepositoryUnitTest.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant

import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

/**
 *   Class "RepositoryUnitTest" :
 *   Abstract class for repositories testing.
 **/
abstract class RepositoryUnitTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinRepositoriesTestRule()

    /**
     * Test the getAll function.
     */
    @Test(expected = NotImplementedError::class)
    abstract fun `test getAll`()

    /**
     * Test the getOne function.
     */
    @Test(expected = NotImplementedError::class)
    abstract fun `test getOne`()

    /**
     * Test the "insertAll" function.
     */
    @Test(expected = NotImplementedError::class)
    abstract fun `test insertAll`()

    /**
     * Test the "insertOne" function.
     */
    @Test(expected = NotImplementedError::class)
    abstract fun `test insertOne`()

}