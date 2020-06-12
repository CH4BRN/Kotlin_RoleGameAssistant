// File HobbyFragment.kt
// @Author pierre.antoine - 09/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.hobby

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewSkillActivity
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.HOBBY_FRAGMENT_POSITION

/**
 *   Class "HobbyFragment" :
 *   TODO: Fill class use.
 **/
class HobbyFragment : CustomFragment() {
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


        loadHobbySkillsRecyclerViewFragment()
        val btnAddSkill = view.findViewById<ImageButton>(R.id.btn_hobby_add_skill)
        btnAddSkill.setOnClickListener {
            val intent = Intent(activity, NewSkillActivity::class.java)
            startActivity(intent)

        }

    }

    private fun loadHobbySkillsRecyclerViewFragment() {
        if (activity != null) {
            var transaction = childFragmentManager.beginTransaction()
            transaction.replace(
                R.id.container_hobbySkills,
                HobbySkillsRecyclerViewFragment.newInstance(activity!!)
            ).commit()
        }

    }


    companion object : CustomCompanion() {
        @JvmStatic
        override fun newInstance(activity: Activity): HobbyFragment {
            val fragment =
                HobbyFragment()

            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, HOBBY_FRAGMENT_POSITION)

            fragment.arguments = args


            return fragment
        }
    }

}