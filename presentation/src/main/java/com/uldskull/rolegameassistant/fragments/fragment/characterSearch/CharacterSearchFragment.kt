package com.uldskull.rolegameassistant.fragments.fragment.characterSearch

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.CharacterSearchActivity
import com.uldskull.rolegameassistant.activities.CharacterTransmission
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.CHARACTER_SEARCH_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.character.DomainCharacter

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterSearchFragment : CustomFragment(), CharacterTransmission {

    private var characterTransmitter: CharacterTransmission? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            (activity as CharacterSearchActivity).replaceFragment(
                R.id.container_recycler_view_characters,
                CharacterRecyclerViewFragment.newInstance(
                    activity!!
                )
            )
        }

    }

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_character_search, container, false
        )
        return initialRootView
    }

    companion object : CustomCompanion() {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @return A new instance of fragment CharacterSearchFragment.
         */
        @JvmStatic
        override fun newInstance(activity: Activity): CharacterSearchFragment {
            val fragment =CharacterSearchFragment()
            fragment.activity = activity
            fragment.characterTransmitter = activity as CharacterTransmission
            val args = Bundle()

            args.putInt(KEY_POSITION, CHARACTER_SEARCH_FRAGMENT_POSITION)
            fragment.arguments = args
            return fragment
        }
    }

    override fun transmitCharacter(domainCharacter: DomainCharacter?) {
        Log.d("DEBUG", "Character $domainCharacter")
        characterTransmitter?.transmitCharacter(domainCharacter)
    }
}
