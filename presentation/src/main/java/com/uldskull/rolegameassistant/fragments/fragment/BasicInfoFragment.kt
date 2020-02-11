// File BasicInfoFragment.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_basic_info.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "BasicInfoFragment" :
 *   Fragment to fill information concerning basic info.
 **/
class BasicInfoFragment : Fragment() {


    /** Fragment Lifecycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newCharacterViewModel = getViewModel()
        return initializeView(inflater, container)
    }

    private lateinit var newCharacterViewModel: NewCharacterViewModel


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
        setFocusListeners()
    }

    /** Set focus listeners **/
    private fun setFocusListeners() {
        setNameFocusListener()
        setLevelFocusListener()
        setExperienceFocusListener()
        setRaceFocusListener()
    }

    /** Set race focus listener **/
    private fun setRaceFocusListener() {
        et_race.setOnFocusChangeListener { _, _
            ->
            newCharacterViewModel.saveRace(et_race?.text.toString())
        }
    }

    /** Set experience focus listener   **/
    private fun setExperienceFocusListener() {
        et_experience.setOnFocusChangeListener { _, _
            ->
            newCharacterViewModel.saveExperience(et_experience?.text.toString())
        }
    }

    /** Set level focus listener    **/
    private fun setLevelFocusListener() {
        et_level.setOnFocusChangeListener { _, _
            ->
            newCharacterViewModel.saveLevel(et_level?.text.toString())
        }
    }

    /** Set name focus listener **/
    private fun setNameFocusListener() {
        et_name.setOnFocusChangeListener { _, _
            ->
            newCharacterViewModel.saveName(et_name?.text.toString())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(activity: Activity, position: Int): BasicInfoFragment {
            val fragment =
                BasicInfoFragment()
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

        private const val KEY_POSITION = "position"
        private lateinit var initialRootView: View


    }
}