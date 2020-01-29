/** File AppModules.kt
 *   @Author pierre.antoine - 27/01/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.di

import com.uldskull.rolegameassistant.ui.new_character.fragments.abilities.AbilitiesViewModel
import com.uldskull.rolegameassistant.ui.new_character.fragments.skills.SkillsViewModel
import com.uldskull.rolegameassistant.ui.new_character.view_model.NewCharacterViewModel
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
