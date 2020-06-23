// File SkillDao.kt
// @Author pierre.antoine - 13/02/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.dao.skill

import androidx.room.Dao
import com.uldskull.rolegameassistant.infrastructure.dao.GenericDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbSkill

/**
 *   Interface "SkillDao" :
 *   Allows interactions with database.
 **/
@Dao
abstract class DbSkillDao : GenericDao<DbSkill>