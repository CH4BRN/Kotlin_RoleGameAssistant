/** File AppModules.kt
 *   @Author pierre.antoine - 27/01/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.framework.di

import com.uldskull.rolegameassistant.presentation.fragments.fragment.abilities.AbilitiesViewModel
import com.uldskull.rolegameassistant.presentation.fragments.fragment.skills.SkillsViewModel
import com.uldskull.rolegameassistant.presentation.view_model.NewCharacterViewModel
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
