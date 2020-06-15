// KoinModule_viewModels.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.di.koin_modules

import com.uldskull.rolegameassistant.viewmodels.*
import com.uldskull.rolegameassistant.viewmodels.breeds.BreedCharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.breeds.DisplayedBreedsViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationSkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.occupations.OccupationsViewModel
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
            displayedBreedsRepository = get(),
            characteristicRepository = get(),
            idealsRepository = get(),
            filledOccupationSkillRepository = get()
        )
    }
    //  Characteristics ViewModel
    viewModel {
        CharacteristicsViewModel(
            application = get(),
            characteristicRepositoryImpl = get(),
            rollsCharacteristicRepositoryImpl = get(),
            diceServiceImpl = get(),
            displayedBreedsRepository = get()
        )
    }
    //  Breed Characteristics ViewModel
    viewModel {
        BreedCharacteristicsViewModel(
            application = get(),
            breedsCharacteristicRepositoryImpl = get()
        )
    }
    //  Skills ViewModel
    viewModel {
        SkillsViewModel(
            application = get(),
            skillToCheckRepository = get()
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
    //  Displayed breeds ViewModel
    viewModel {
        DisplayedBreedsViewModel(
            application = get(),
            displayedBreedRepositoryImpl = get()
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

    //  Progression bar ViewModel
    viewModel {
        ProgressionBarViewModel(
            application = get()
        )
    }

    //  Points to spend ViewModel
    viewModel {
        PointsToSpendViewModel(
            application = get()
        )
    }

    //  Picture ViewModel
    viewModel {
        CharactersPictureViewModel(
            application = get()
        )
    }

    //  Hobby skill ViewModel
    viewModel {
        HobbySkillsViewModel(
            application = get()
        )
    }
}