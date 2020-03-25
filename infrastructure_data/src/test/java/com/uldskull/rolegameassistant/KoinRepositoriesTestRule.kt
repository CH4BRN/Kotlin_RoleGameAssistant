// File KoinRepositoriesTestRule.kt
// @Author pierre.antoine - 24/03/2020 - No copyright.

package com.uldskull.rolegameassistant

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

/**
 *   Class "KoinRepositoriesTestRule" :
 *   TODO: Fill class use.
 **/
class KoinRepositoriesTestRule : TestRule {

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                startKoin {
                    modules(listOf())
                }
                base.evaluate()
                stopKoin()
            }
        }
    }
}