/** File AppModules.kt
 *   @Author pierre.antoine - 27/01/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.cross_cutting

import com.uldskull.rolegameassistant.ui.new_character.fragments.abilities.AbilitiesViewModel
import com.uldskull.rolegameassistant.ui.new_character.view_model.NewCharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/** ViewModels injection    **/
val viewModelModule = module {
    viewModel {
        //  New Character ViewModel
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
}
