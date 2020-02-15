// KoinModule_viewModels.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.di

import com.uldskull.rolegameassistant.fragments.fragment.abilities.AbilitiesViewModel
import com.uldskull.rolegameassistant.fragments.fragment.skills.SkillsViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/** ViewModels injection    **/
val viewModelModule = module {
    //  New Character ViewModel
    viewModel {
        NewCharacterViewModel(
            application = get()
        )
    }
    //  Abilities ViewModel
    viewModel {
        AbilitiesViewModel(
            application = get()
        )
    }
    //  Skills ViewModel
    viewModel {
        SkillsViewModel(
            application = get()
        )
    }


}