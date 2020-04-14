// File NavigationBarFragment.kt
// @Author pierre.antoine - 17/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bars

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.viewmodels.CharacteristicsViewModel
import com.uldskull.rolegameassistant.viewmodels.CharactersViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_navigation_bar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "NavigationBarFragment" :
 *   Fragment that displays the navigation bar.
 **/
class NavigationBarFragment(activity: Activity) : CustomFragment(activity) {

    /**
     * New character view model.
     */
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /**
     * Characteristics view model.
     */
    private val characteristicsViewModel: CharacteristicsViewModel by sharedViewModel()

    /**
     * Characters view model.
     */
    private val charactersViewModel: CharactersViewModel by sharedViewModel()


    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_navigation_bar, container, false
        )
        return initialRootView
    }

    private fun displayBackConfirmation() {
        Log.d(TAG, "displayBackConfirmation")
        val confirmDialog = AlertDialog.Builder(activity)

        confirmDialog.setTitle("Go back? : ")
        val confirmDialogItems = arrayOf(
            "Go back.",
            "No, continue."
        )

        confirmDialog.setItems(
            confirmDialogItems
        ) { _, which ->
            /**
             * This method will be invoked when a button in the dialog is clicked.
             *
             * @param which the button that was clicked (ex.
             * [DialogInterface.BUTTON_POSITIVE]) or the position
             * of the item clicked
             */
            when (which) {
                0 -> activity.onBackPressed()
            }

        }
        confirmDialog.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        setBackButton()
        setSaveButton()
    }

    private fun setBackButton() {
        Log.d(TAG, "setBackButton")
        btn_back.setOnClickListener {
            displayBackConfirmation()
        }
    }

    private fun setSaveButton() {
        Log.d(TAG, "setSaveButton")
        btn_save.setOnClickListener {
            doSave()
        }
    }

    private fun doSave() {
        Log.d(TAG, "doSave")
        var insertedId =
            newCharacterViewModel.saveCharacter(characteristicsViewModel.rollCharacteristics)
        var result = charactersViewModel.findOneById(insertedId)
        Log.d(TAG, "$result")

    }

    companion object : CustomCompanion() {
        private const val TAG = "NavigationBarFragment"
        override fun newInstance(activity: Activity): CustomFragment {
            Log.d(TAG, "newInstance")
            return NavigationBarFragment(
                activity
            )
        }

    }

}