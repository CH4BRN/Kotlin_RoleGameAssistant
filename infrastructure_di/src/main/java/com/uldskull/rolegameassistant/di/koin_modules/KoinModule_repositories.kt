/** File KoinModule_repositories.kt
 *   @Author pierre.antoine - 24/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.di.koin_modules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.infrastructure.repositories.CharacterRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.breed.DbDisplayedBreedsRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.characteristic.DbBreedsCharacteristicRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.characteristic.DbCharacteristicRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.characteristic.DbRollsCharacteristicRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.ideal.DbIdealsRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.occupations.DbOccupationsRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.skill.DbFilledSkillRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.skill.DbOccupationSkillRepositoryImpl
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.character.breed.displayedBreed.DomainDisplayedBreed
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedsCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollsCharacteristic
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToFill
import com.uldskull.rolegameassistant.models.character.skill.DomainSkillToCheck
import com.uldskull.rolegameassistant.repository.breed.DisplayedBreedsRepository
import com.uldskull.rolegameassistant.repository.character.CharacterRepository
import com.uldskull.rolegameassistant.repository.characteristic.BreedsCharacteristicRepository
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository
import com.uldskull.rolegameassistant.repository.characteristic.RollsCharacteristicRepository
import com.uldskull.rolegameassistant.repository.ideal.IdealsRepository
import com.uldskull.rolegameassistant.repository.occupations.OccupationsRepository
import com.uldskull.rolegameassistant.repository.skill.FilledOccupationSkillRepository
import com.uldskull.rolegameassistant.repository.skill.OccupationSkillRepository
import org.koin.dsl.module

val repositoriesModule = module {
    //  Repository for Characteristics
    single<CharacteristicRepository<MutableLiveData<List<DomainCharacteristic>>>> {
        DbCharacteristicRepositoryImpl(
            dbCharacteristicDao = get()
        )
    }
    //  Repository for breed's characteristics
    single<BreedsCharacteristicRepository<LiveData<List<DomainBreedsCharacteristic>>>> {
        DbBreedsCharacteristicRepositoryImpl(
            dbBreedCharacteristicDao = get()
        )
    }
    //  Repository for roll's characteristics.
    single<RollsCharacteristicRepository<LiveData<List<DomainRollsCharacteristic>>>> {
        DbRollsCharacteristicRepositoryImpl(
            dbRollCharacteristicsDao = get()
        )
    }
    //  Repository for displayed breed's characteristics
    single<DisplayedBreedsRepository<LiveData<List<DomainDisplayedBreed?>>>> {
        DbDisplayedBreedsRepositoryImpl(
            dbBreedWithDbCharacteristicsDao = get(),
            dbDisplayedBreedDao = get()
        )
    }


    //  Repository for character
    single<CharacterRepository<LiveData<List<DomainCharacter?>?>>> {
        CharacterRepositoryImpl(
            dbCharacterDao = get(),
            dbCharacterWithDbFilledSkillsDao = get()
        )
    }

    //  Repository for ideals
    single<IdealsRepository<LiveData<List<DomainIdeal>>>> {
        DbIdealsRepositoryImpl(
            dbIdealDao = get()
        )
    }

    //  Repository for occupations
    single<OccupationsRepository<LiveData<List<DomainOccupation>>>> {
        DbOccupationsRepositoryImpl(
            dbOccupationDao = get(),
            dbOccupationDbSkillDao = get()
        )
    }

    //  Repository for occupation skills
    single<OccupationSkillRepository<LiveData<List<DomainSkillToCheck>>>> {
        DbOccupationSkillRepositoryImpl(
            dbOccupationSkillDao = get()
        )
    }

    //  Repository for filled skills
    single<FilledOccupationSkillRepository<LiveData<List<DomainSkillToFill>>>> {
        DbFilledSkillRepositoryImpl(
            dbFilledSkillsDao = get()
        )
    }
}
