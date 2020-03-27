// KoinModule_viewModels.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.di.koin_modules

import com.uldskull.rolegameassistant.fragments.fragment.bonds.BondsViewModel
import com.uldskull.rolegameassistant.fragments.fragment.ideals.IdealsViewModel
import com.uldskull.rolegameassistant.fragments.fragment.skills.SkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.CharacteristicViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.RacesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/** ViewModels injection    **/
val viewModelModule = module {
    //  New Character ViewModel
    viewModel {
        NewCharacterViewModel(
            application = get(),
            diceService = get()
        )
    }
    //  Characteristics ViewModel
    viewModel {
        CharacteristicViewModel(
            application = get(),
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
    //  Race ViewModel
    viewModel {
        RacesViewModel(
            application = get(),
            raceRepositoryImpl = get()
        )
    }
}