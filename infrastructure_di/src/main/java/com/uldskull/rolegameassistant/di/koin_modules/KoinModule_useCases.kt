/** File KoinModule_useCases.kt
 *   @Author pierre.antoine - 18/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.di.koin_modules

import com.uldskull.rolegameassistant.useCases.alignment.GetAlignmentScoreUseCase
import com.uldskull.rolegameassistant.useCases.damageBonus.GetDamageBonusScoreUseCase
import com.uldskull.rolegameassistant.useCases.damageBonus.GetDamageBonusUseCase
import com.uldskull.rolegameassistant.useCases.damageBonus.GetSizePlusStrengthUseCase
import com.uldskull.rolegameassistant.useCases.diceRoll.GetOneDiceRollUseCase
import com.uldskull.rolegameassistant.useCases.energyPoints.GetEnergyPointsUseCase
import com.uldskull.rolegameassistant.useCases.health.GetBaseHealthUseCase
import com.uldskull.rolegameassistant.useCases.health.GetBreedsHealthBonusUseCase
import com.uldskull.rolegameassistant.useCases.health.GetTotalHealthUseCase
import com.uldskull.rolegameassistant.useCases.ideaScore.GetIdeaScoreUseCase
import com.uldskull.rolegameassistant.useCases.know.GetKnowScoreUseCase
import com.uldskull.rolegameassistant.useCases.luck.GetLuckScoreUseCase
import com.uldskull.rolegameassistant.useCases.sanityScore.GetSanityScoreUseCase
import com.uldskull.rolegameassistant.useCases.skills.GetAnHobbySkillWithCharacterIdUseCase
import org.koin.dsl.module

/**
 * Use cases module
 */
val useCasesModule = module {
    /**
     * Dice roll use case di
     */
    factory { GetOneDiceRollUseCase() }
    /**
     * Base health use case di
     */
    factory { GetBaseHealthUseCase() }
    /**
     * Alignment score use case di
     */
    factory { GetAlignmentScoreUseCase() }
    /**
     * Size plus strength use cas di
     */
    factory { GetSizePlusStrengthUseCase() }
    /**
     * Damage bonus score di
     */
    factory { GetDamageBonusScoreUseCase() }
    /**
     * Damage bonus use case di
     */
    factory { GetDamageBonusUseCase() }
    /**
     * Idea score use case di
     */
    factory { GetIdeaScoreUseCase() }
    /**
     * Energy points use case di
     */
    factory { GetEnergyPointsUseCase() }
    /**
     * Sanity score use case di
     */
    factory { GetSanityScoreUseCase() }
    /**
     * Luck score use case di
     */
    factory { GetLuckScoreUseCase() }
    /**
     * Know score use case
     */
    factory { GetKnowScoreUseCase() }
    /**
     * Breed health bonus use case
     */
    factory { GetBreedsHealthBonusUseCase() }
    /**
     * Get total health use case
     */
    factory { GetTotalHealthUseCase() }
    /**
     * Get an hobby skill use case
     */
    factory { GetAnHobbySkillWithCharacterIdUseCase() }
}