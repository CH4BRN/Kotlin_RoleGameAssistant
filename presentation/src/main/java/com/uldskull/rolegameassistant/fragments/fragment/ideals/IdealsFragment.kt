// File IdealsFragment.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.ideals

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.NewIdealActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.IDEALS_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_IDEALS_NEW_IDEAL
import kotlinx.android.synthetic.main.fragment_ideals.*

/**
 *   Class "IdealsFragment" :
 *   Fragment to manage "Ideals" view.
 **/
class IdealsFragment(activity: Activity) : CustomFragment(activity) {


    /**
     * Called when the view is created
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    /**
     * Initialize the view
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_ideals, container, false
        )
        return initialRootView
    }


    override fun onResume() {
        super.onResume()
        Log.d("IdealsFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = IDEALS_FRAGMENT_POSITION
        Log.d("IdealsFragment_2", NewCharacterActivity.progression.value.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonAddIdeal()
    }

    private fun setButtonAddIdeal() {
        if (btn_addIdeal != null) {
            btn_addIdeal?.setOnClickListener {
                val intent = Intent(activity, NewIdealActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE_IDEALS_NEW_IDEAL)
            }
        }
    }

    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity): IdealsFragment {
            val fragment =
                IdealsFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, IDEALS_FRAGMENT_POSITION)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.fragmentIdeals_container_ideals,
                IdealsRecyclerViewFragment.newInstance(
                    activity
                )
            )

            return fragment
        }
    }
}