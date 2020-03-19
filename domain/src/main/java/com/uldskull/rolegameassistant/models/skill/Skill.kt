// File Skill.kt
// @Author pierre.antoine - 04/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.skill

/**
 *   Class "Skill" :
 *   TODO: Fill class use.
 **/
abstract class AbstractSkill(var name: String, var description: String)

class ChosenSkill(name: String, description: String, attributed: Int) :
    AbstractSkill(name, description)

class ShownSkill(name: String, description: String) : AbstractSkill(name, description)