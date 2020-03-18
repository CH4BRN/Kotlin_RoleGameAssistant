// File NavigationBarFragment.kt
// @Author pierre.antoine - 17/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.uldskull.rolegameassistant.R
import kotlinx.android.synthetic.main.fragment_navigation_bar.*

/**
 *   Class "NavigationBarFragment" :
 *   Fragment that displays the navigation bar.
 **/
class NavigationBarFragment(activity: Activity) : CustomFragment(activity) {
    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_navigation_bar, container, false
        )
        return initialRootView
    }

    private fun displayBackConfirmation() {
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

        setBackButton()
    }

    private fun setBackButton() {
        btn_back.setOnClickListener {
            displayBackConfirmation()
        }
    }

    companion object : CustomCompanion() {
        override fun newInstance(activity: Activity): CustomFragment {
            return NavigationBarFragment(activity)
        }

    }

}