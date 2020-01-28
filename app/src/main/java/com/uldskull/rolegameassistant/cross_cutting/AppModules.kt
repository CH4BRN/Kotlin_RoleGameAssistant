/** File AppModules.kt
 *   @Author pierre.antoine - 27/01/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.cross_cutting

import com.uldskull.rolegameassistant.ui.new_character.NewCharacterViewModel
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
}
