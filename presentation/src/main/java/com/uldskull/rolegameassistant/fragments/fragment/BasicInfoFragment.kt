// File BasicInfoFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NEW_RACE_ACTIVITY
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.BASIC_INFO_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_basic_info.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "BasicInfoFragment" :
 *   Fragment to fill information concerning basic info.
 **/
class BasicInfoFragment(activity: Activity) : CustomFragment(activity) {


    /** Fragment Lifecycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newCharacterViewModel = getViewModel()
        return initializeView(inflater, container)
    }


    /**
     * ViewModel for new character
     */
    private lateinit var newCharacterViewModel: NewCharacterViewModel
    /** Initialize the view corresponding to this fragment class    **/
    override fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_basic_info, container, false
        )
        return initialRootView
    }

    /** Fragment Lifecycle  **/
    override fun onResume() {
        super.onResume()
        Log.i("BasicInfoFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = BASIC_INFO_FRAGMENT_POSITION
        Log.i("BasicInfoFragment_2", NewCharacterActivity.progression.value.toString())

    }


    /** Fragment Lifecycle  **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonAddRace()
        setFocusListeners()
    }

    /**
     * Set the add race button.
     */
    private fun setButtonAddRace() {
        if (btn_addRace != null) {
            btn_addRace?.setOnClickListener {
                val intent = Intent(activity, NEW_RACE_ACTIVITY)
                startActivityForResult(intent, REQUEST_CODE_BASIC_INFO_NEW_RACE)
            }
        }
    }

    /** Set focus listeners **/
    private fun setFocusListeners() {
        setNameFocusListener()
    }

    /** Set name focus listener **/
    private fun setNameFocusListener() {
        et_name.setOnFocusChangeListener { _, _
            ->
            newCharacterViewModel.saveName(et_name?.text.toString())
        }
    }


    companion object : CustomCompanion() {
        /**
         * Get a new instance of class BasicInfoFragment
         */
        @JvmStatic
        override fun newInstance(activity: Activity): BasicInfoFragment {
            val fragment =
                BasicInfoFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, BASIC_INFO_FRAGMENT_POSITION)
            fragment.arguments = args

            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_picture,
                PictureFragment.newInstance(activity)
            )
            return fragment
        }
    }
}