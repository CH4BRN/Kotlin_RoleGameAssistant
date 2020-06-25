// GetAnHobbySkill.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.useCases.skills

import com.uldskull.rolegameassistant.models.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.useCases.UseCase

/**
Class "GetAnHobbySkill"

Get an hobby skill with the character id
 */
class GetAnHobbySkillWithCharacterIdUseCase : UseCase<Long,  DomainSkillToFill>{
    override fun execute(request: Long): DomainSkillToFill {
        return DomainSkillToFill(
            filledSkillCharacterId = request,
            filledSkillType = 1
        )
    }

}