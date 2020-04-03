/** File KoinModule_repositories.kt
 *   @Author pierre.antoine - 24/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.di.koin_modules

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.infrastructure.repositories.CharacterRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.breed.DbBreedsRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.characteristic.DbBreedsCharacteristicRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.characteristic.DbCharacteristicRepositoryImpl
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.repository.breed.BreedsRepository
import com.uldskull.rolegameassistant.repository.character.CharacterRepository
import com.uldskull.rolegameassistant.repository.characteristic.BreedCharacteristicRepository
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<CharacteristicRepository<LiveData<List<DomainCharacteristic>>>> {
        DbCharacteristicRepositoryImpl(
            get()
        )
    }

    single<BreedCharacteristicRepository<LiveData<List<DomainBreedCharacteristic>>>> {
        DbBreedsCharacteristicRepositoryImpl(
            dbBreedCharacteristicDao = get()
        )
    }
    single<BreedsRepository<LiveData<List<DomainBreed>>>> {
        DbBreedsRepositoryImpl(
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
