package com.uldskull.rolegameassistant

import kotlinx.coroutines.runBlocking
import org.koin.core.inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class CharacteristicRepositoryImplUnitTest : RepositoryUnitTest() {


    /**
     * Test the getAll function.
     */
    override fun `test getAll`() {

    }

    /**
     * Test the getOne function.
     */
    override fun `test getOne`() {

    }

    /**
     * Test the "insertAll" function.
     */
    override fun `test insertAll`() {

    }

    /**
     * Test the "insertOne" function.
     */
    override fun `test insertOne`() {

        val repositoryImpl: CharacteristicRepositoryImpl by inject()
        runBlocking {
            repositoryImpl.insertOne(null)
        }

    }

}



