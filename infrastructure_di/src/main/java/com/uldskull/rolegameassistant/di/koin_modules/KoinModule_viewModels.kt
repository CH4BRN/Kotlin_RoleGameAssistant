// KoinModule_viewModels.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.di.koin_modules

import com.uldskull.rolegameassistant.fragments.fragment.bonds.BondsViewModel
import com.uldskull.rolegameassistant.fragments.fragment.ideals.IdealsViewModel
import com.uldskull.rolegameassistant.fragments.fragment.skills.SkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.BreedsViewModel
import com.uldskull.rolegameassistant.viewmodels.CharacteristicViewModel
import com.uldskull.rolegameassistant.viewmodels.CharactersViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
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
            characteristicRepository = get()
        )
    }
    //  Characteristics ViewModel
    viewModel {
        CharacteristicViewModel(
            application = get(),
            breedCharacteristicRepositoryImpl = get(),
            characteristicRepositoryImpl = get()
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
            application = get()
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
}