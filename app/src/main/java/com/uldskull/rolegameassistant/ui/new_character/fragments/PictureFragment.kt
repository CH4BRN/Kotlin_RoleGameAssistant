// File PictureFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.ui.new_character.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.ui.new_character.view_model.NewCharacterViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "PictureFragment" :
 *   Handle character's picture
 **/
class PictureFragment : Fragment() {

    private lateinit var newCharacterViewModel: NewCharacterViewModel
    /** Fragment Lifecycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newCharacterViewModel = getViewModel()
        newCharacterViewModel.progression.value = 6
        return initializeView(inflater, container)
    }

    /** Initialize the view corresponding to this fragment class    **/
    private fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_picture, container, false
        )

        return initialRootView

    }

    companion object {

        @JvmStatic
        fun newInstance(activity: NewCharacterActivity, position: Int): PictureFragment {
            val fragment = PictureFragment()
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

        private const val KEY_POSITION = "position"
        private lateinit var initialRootView: View

    }

}