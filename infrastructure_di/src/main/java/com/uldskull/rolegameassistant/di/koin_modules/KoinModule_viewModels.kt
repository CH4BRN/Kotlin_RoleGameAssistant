// KoinModule_viewModels.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.di.koin_modules

import com.uldskull.rolegameassistant.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/** ViewModels injection    **/
val viewModelModule = module {
    //  New Character ViewModel
    viewModel {
        NewCharacterViewModel(
            application = get(),
            diceService = get(),
            characterRepository = get(),
            breedsRepository = get(),
            characteristicRepository = get(),
            idealsRepository = get()
        )
    }
    //  Characteristics ViewModel
    viewModel {
        CharacteristicsViewModel(
            application = get(),
            characteristicRepositoryImpl = get(),
            rollCharacteristicRepositoryImpl = get(),
            diceServiceImpl = get(),
            breedsRepository = get()
        )
    }
    //  Breed Characteristics ViewModel
    viewModel {
        BreedCharacteristicsViewModel(
            application = get(),
            breedCharacteristicRepositoryImpl = get()
        )
    }
    //  Skills ViewModel
    viewModel {
        SkillsViewModel(
            application = get()
        )
    }
    //  Bonds ViewModel
    viewModel {
        BondsViewModel(
            application = get()
        )
    }
    //  Ideals ViewModel
    viewModel {
        IdealsViewModel(
            application = get(),
            idealsRepositoryImpl = get()
        )
    }
    //  Breeds ViewModel
    viewModel {
        BreedsViewModel(
            application = get(),
            breedRepositoryImpl = get()
        )
    }
    //  Characters ViewModel
    viewModel {
        CharactersViewModel(
            application = get(),
            characterRepository = get()
        )
    }
    //  Derived values ViewModel
    viewModel {
        DerivedValuesViewModel(
            application = get()
        )
    }
    //  Occupations ViewModel
    viewModel {
        OccupationsViewModel(
            application = get(),
            occupationsRepositoryImpl = get()
        )
    }
    //  Occupation skills ViewModel
    viewModel {
        OccupationSkillsViewModel(
            application = get()
        )
    }
    //  Occupation ViewModel
    viewModel {
        OccupationViewModel(
            application = get()
        )
    }
}