/** File KoinModule_repositories.kt
 *   @Author pierre.antoine - 24/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.di.koin_modules

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.infrastructure.repositories.characteristic.CharacteristicRepositoryImpl
import com.uldskull.rolegameassistant.models.character.characteristic.DomainCharacteristic
import com.uldskull.rolegameassistant.repository.characteristic.CharacteristicRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<CharacteristicRepository<LiveData<List<DomainCharacteristic>>>> {
        CharacteristicRepositoryImpl(
            get()
        )
    }
}
