/** File KoinModule_repositories.kt
 *   @Author pierre.antoine - 24/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.di.koin_modules

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.infrastructure.repositories.CharacterRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.characteristic.DbCharacteristicRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.characteristic.DbRaceCharacteristicRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.race.DbRaceRepositoryImpl
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.DomainRace
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRaceCharacteristic
import com.uldskull.rolegameassistant.repository.character.CharacterRepository
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository
import com.uldskull.rolegameassistant.repository.characteristic.RaceCharacteristicRepository
import com.uldskull.rolegameassistant.repository.race.RaceRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<CharacteristicRepository<LiveData<List<DomainCharacteristic>>>> {
        DbCharacteristicRepositoryImpl(
            get()
        )
    }

    single<RaceCharacteristicRepository<LiveData<List<DomainRaceCharacteristic>>>> {
        DbRaceCharacteristicRepositoryImpl(
            dbRaceCharacteristicDao = get()
        )
    }
    single<RaceRepository<LiveData<List<DomainRace>>>> {
        DbRaceRepositoryImpl(
            get(),
            get()
        )
    }

    single<CharacterRepository<LiveData<List<DomainCharacter>>>> {
        CharacterRepositoryImpl(
            get()
        )
    }
}
