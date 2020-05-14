/** File Activities.kt
 *   @Author pierre.antoine - 04/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.activities

import com.uldskull.rolegameassistant.activities.newCharacter.CharacterActivity

val NEW_IDEAL_ACTIVITY = NewIdealActivity::class.java
val NEW_BREED_ACTIVITY = NewBreedActivity::class.java
val CHARACTER_SEARCH_ACTIVTY = CharacterSearchActivity::class.java
val MAIN_ACTIVITY = MainActivity::class.java
val NEW_SKILL_ACTIVTY = NewSkillActivity::class.java
val NEW_JOB_ACTIVITY = NewOccupationActivity::class.java

/** DomainCharacter search activity java class    **/
val CHARACTER_SEARCH_ACTIVITY: Class<CharacterSearchActivity> =
    CharacterSearchActivity::class.java
/** New character activity java class   **/
val CHARACTER_ACTIVITY: Class<CharacterActivity> =
    CharacterActivity::class.java
