// File BasicInfoFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NEW_RACE_ACTIVITY
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_basic_info.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "BasicInfoFragment" :
 *   Fragment to fill information concerning basic info.
 **/
class BasicInfoFragment(val activity: Activity) : Fragment() {


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
    private var btnAddRace: Button? = null
    /** Initialize the view corresponding to this fragment class    **/
    private fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_basic_info, container, false
        )
        return initialRootView
    }

    /** Fragment Lifecycle  **/
    override fun onResume() {
        super.onResume()
        NewCharacterActivity.progression.value = 0
    }


    /** Fragment Lifecycle  **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAddRace = view.findViewById(R.id.btn_addRace)

        btnAddRace?.setOnClickListener {
            val intent = Intent(activity, NEW_RACE_ACTIVITY)
            startActivityForResult(intent, 45)
        }
        setFocusListeners()
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

    /**
     * Initial root view
     */
    private lateinit var initialRootView: View

    companion object {
        /**
         * Get a new instance of class BasicInfoFragment
         */
        @JvmStatic
        fun newInstance(activity: Activity, position: Int): BasicInfoFragment {
            val fragment =
                BasicInfoFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_picture,
                PictureFragment.newInstance(activity, 42)
            )
            return fragment
        }


    }
}