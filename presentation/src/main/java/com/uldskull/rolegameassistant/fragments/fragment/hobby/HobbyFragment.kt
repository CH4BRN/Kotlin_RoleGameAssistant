// File HobbyFragment.kt
// @Author pierre.antoine - 09/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.hobby

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.NewSkillActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.HOBBY_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION

/**
 *   Class "HobbyFragment" :
 *   TODO: Fill class use.
 **/
class HobbyFragment(activity: Activity) : CustomFragment(activity) {
    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_hobby, container, false
        )
        return initialRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var btnAddSkill = view.findViewById<ImageButton>(R.id.btn_hobby_add_skill)
        btnAddSkill.setOnClickListener {
            val intent = Intent(activity, NewSkillActivity::class.java)
            startActivity(intent)

        }

    }

    /**
     * Called when the focus return on this view
     */
    override fun onResume() {
        super.onResume()
        Log.d("HobbyFragment_1", NewCharacterActivity.progression.value.toString())
        NewCharacterActivity.progression.value = HOBBY_FRAGMENT_POSITION
        Log.d("HobbyFragment_2", NewCharacterActivity.progression.value.toString())
    }

    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity): HobbyFragment {
            val fragment =
                HobbyFragment(
                    activity
                )

            val args = Bundle()

            args.putInt(KEY_POSITION, HOBBY_FRAGMENT_POSITION)

            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.container_hobbySkills,
                HobbySkillsRecyclerViewFragment.newInstance(
                    activity
                )
            )

            return fragment
        }
    }

}