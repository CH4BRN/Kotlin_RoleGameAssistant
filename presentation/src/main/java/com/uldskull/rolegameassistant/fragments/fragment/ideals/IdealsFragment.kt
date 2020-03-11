// File IdealsFragment.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.ideals

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NEW_IDEAL_ACTIVITY
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import kotlinx.android.synthetic.main.fragment_ideals.*

/**
 *   Class "IdealsFragment" :
 *   Fragment to manage "Ideals" view.
 **/
class IdealsFragment(val activity: Activity) : Fragment() {
    /** Initial root view   **/
    private lateinit var initialRootView: View

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
    private fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_ideals, container, false
        )
        return initialRootView
    }

    override fun onResume() {
        super.onResume()
        NewCharacterActivity.progression.value = 1
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_addIdeal.setOnClickListener {

            val intent = Intent(activity, NEW_IDEAL_ACTIVITY)
            startActivityForResult(intent, 44)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(activity: Activity, position: Int): IdealsFragment {
            val fragment =
                IdealsFragment(
                    activity
                )
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_ideals,
                IdealsRecyclerViewFragment.newInstance(activity, 41)
            )

            return fragment
        }
    }
}