/** File KoinModule_repositories.kt
 *   @Author pierre.antoine - 24/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.di.koin_modules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uldskull.rolegameassistant.infrastructure.repositories.CharacterRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.breed.DbBreedsRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.characteristic.DbBreedsCharacteristicRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.characteristic.DbCharacteristicRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.characteristic.DbRollCharacteristicRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.ideal.DbIdealsRepositoryImpl
import com.uldskull.rolegameassistant.infrastructure.repositories.occupations.DbOccupationsRepositoryImpl
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.models.character.occupation.DomainOccupation
import com.uldskull.rolegameassistant.models.character.breed.DomainBreed
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter
import com.uldskull.rolegameassistant.models.character.characteristic.DomainBreedCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.DomainRollCharacteristic
import com.uldskull.rolegameassistant.repository.breed.BreedsRepository
import com.uldskull.rolegameassistant.repository.character.CharacterRepository
import com.uldskull.rolegameassistant.repository.characteristic.BreedCharacteristicRepository
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository
import com.uldskull.rolegameassistant.repository.characteristic.RollCharacteristicRepository
import com.uldskull.rolegameassistant.repository.ideal.IdealsRepository
import com.uldskull.rolegameassistant.repository.occupations.OccupationsRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<CharacteristicRepository<MutableLiveData<List<DomainCharacteristic>>>> {
        DbCharacteristicRepositoryImpl(
            get()
        )
    }

    single<BreedCharacteristicRepository<LiveData<List<DomainBreedCharacteristic>>>> {
        DbBreedsCharacteristicRepositoryImpl(
            dbBreedCharacteristicDao = get()
        )
    }

    single<RollCharacteristicRepository<LiveData<List<DomainRollCharacteristic>>>> {
        DbRollCharacteristicRepositoryImpl(
            dbRollCharacteristicsDao = get()
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

    single<IdealsRepository<LiveData<List<DomainIdeal>>>> {
        DbIdealsRepositoryImpl(
            get()
        )
    }

    single<OccupationsRepository<LiveData<List<DomainOccupation>>>> {
        DbOccupationsRepositoryImpl(
            get(),
            get()
        )
    }
}
