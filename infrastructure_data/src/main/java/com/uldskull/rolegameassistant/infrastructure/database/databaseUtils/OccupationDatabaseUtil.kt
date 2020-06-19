// File OccupationDatabaseUtil.kt
// @Author pierre.antoine - 27/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database.databaseUtils

import android.util.Log
import com.uldskull.rolegameassistant.infrastructure.dao.occupation.DbOccupationDbSkillDao
import com.uldskull.rolegameassistant.infrastructure.dao.occupation.DbOccupationsDao
import com.uldskull.rolegameassistant.infrastructure.dao.skill.DbSkillToCheckDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupation
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupationAndDbSkillCrossRef
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupationWithDbSkills
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbSkillToCheck

/**
 *   Class "OccupationDatabaseUtil" :
 *   TODO: Fill class use.
 **/
class OccupationDatabaseUtil {
    companion object {

        private const val TAG = "OccupationDatabaseUtil"

        fun insertOccupations(
            occupationsDao: DbOccupationsDao,
            occupationSkillDao: DbSkillToCheckDao,
            occupationWithSkillDao: DbOccupationDbSkillDao
        ) {

            insertAccountant(occupationsDao, occupationSkillDao, occupationWithSkillDao)
            insertAcrobat(occupationsDao, occupationSkillDao, occupationWithSkillDao)
        }



        private fun insertAcrobat(
            occupationsDao: DbOccupationsDao,
            occupationSkillDao: DbSkillToCheckDao,
            occupationWithSkillDao: DbOccupationDbSkillDao
        ) {
            Log.d(TAG, "insertAcrobat")
            var occupationId = occupationsDao.insertOccupation(
                DbOccupation(
                    occupationName = "Acrobat",
                    occupationContacts = "Amateur athletic circles, sports writers, circuses, and carnivals",
                    occupationIncome = "Lower to Lower Middle class",
                    occupationSpecial = "+1 STR and +1 DEX, or +2 DEX"
                )
            )
            Log.d(TAG, "occupation id : ${occupationId}")
            var skillId = occupationSkillDao.insertSkillToCheck(
                DbSkillToCheck(
                    skillName = "Bargain  ",
                    skillDescription = "The skill of obtaining something for an agreeable price. \n" +
                            "The bargainer must state the price at which he or she wishes to \n" +
                            "purchase the item and, for each 2% difference between the price and\n" +
                            " the asking price, he or she must subtract 1 percentile from his \n" +
                            "Bargain skill. The seller will not take a loss, no matter how good\n" +
                            " the bargaining. The keeper usually determines the bottom-line secretly.",
                    skillBase = 5

                )
            )
            Log.d(TAG, "skill id : ${skillId}")


            var crossId = occupationWithSkillDao.insertCross(
                DbOccupationAndDbSkillCrossRef(
                    occupationId = occupationId,
                    skillId = skillId
                )
            )
            Log.d(TAG, "crossID : ${crossId}")

            var result = occupationWithSkillDao.getOccupationsWithSkills()

            result.forEach { dbOccupationWithDbSkills: DbOccupationWithDbSkills ->
                Log.d(TAG, "$dbOccupationWithDbSkills")
            }
        }

        private fun insertAccountant(
            occupationsDao: DbOccupationsDao,
            occupationSkillDao: DbSkillToCheckDao,
            occupationWithSkillDao: DbOccupationDbSkillDao
        ) {
            Log.d(TAG, "insertAccountant")

            var occupationId = occupationsDao.insertOccupation(
                DbOccupation(
                    occupationName = "Accountant",
                    occupationContacts = "Contacts: Other accountants",
                    occupationIncome = "Lower to Lower Middle class",
                    occupationSpecial = "+1 STR and +1 DEX, or +2 DEX"
                )
            )
            Log.d(TAG, "occupation id : ${occupationId}")

            var skillId = occupationSkillDao.insertSkillToCheck(
                DbSkillToCheck(
                    skillName = "Accounting ",
                    skillDescription = " Grants understanding of accountancy procedures, and reveals " +
                            "the financial functioning of a business or person. Inspecting the" +
                            "books, one might detect cheated employees, siphoned-off funds, payment" +
                            " of bribes or blackmail, and whether or not the financial condition is " +
                            "better or worse than claimed. Looking through old accounts, one could " +
                            "see how money was gained or lost in the past (grain, slave-trading," +
                            " whiskey-running, etc.) and to whom and for what payment was made.",
                    skillBase = 10
                )
            )
            Log.d(TAG, "skill id : ${skillId}")

            var crossId = occupationWithSkillDao.insertCross(
                DbOccupationAndDbSkillCrossRef(
                    occupationId = occupationId,
                    skillId = skillId
                )
            )
            Log.d(TAG, "crossID : ${crossId}")

            skillId = occupationSkillDao.insertSkillToCheck(
                DbSkillToCheck(
                    skillName = "Anthropology  ",
                    skillDescription = "Enables the user to identify and understand an individual’s" +
                            " way of life from his behavior. If the skill-user observes another " +
                            "culture from within for a time, or works from accurate records concerning " +
                            "an extinct culture, he or she may make simple predictions about that " +
                            "culture’s ways and morals, even though the evidence may be incomplete." +
                            " Studying the culture for a month or more, the anthropologist begins " +
                            "to understand how the culture functions and, in combination with " +
                            "Psychology, may predict the actions and beliefs of representatives." +
                            " Essentially useful only with existing human cultures.",
                    skillBase = 1
                )
            )
            Log.d(TAG, "skill id : ${skillId}")

            crossId = occupationWithSkillDao.insertCross(
                DbOccupationAndDbSkillCrossRef(
                    occupationId = occupationId,
                    skillId = skillId
                )
            )
            Log.d(TAG, "crossID : ${crossId}")

            var result = occupationWithSkillDao.getOccupationsWithSkills()

            result.forEach { dbOccupationWithDbSkills: DbOccupationWithDbSkills ->
                Log.d(TAG, "$dbOccupationWithDbSkills")
            }

        }
    }
// TODO : Fill class.
}