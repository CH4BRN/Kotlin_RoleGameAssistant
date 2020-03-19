package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.adapter.CHARACTER_SEARCH_FRAGMENT_POSITION

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterSearchFragment(activity: Activity) : CustomFragment(activity) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_search, container, false)
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

    companion object : CustomCompanion(){
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @return A new instance of fragment CharacterSearchFragment.
         */
        @JvmStatic
        override fun newInstance(activity: Activity): CharacterSearchFragment {
            val fragment =
                CharacterSearchFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, CHARACTER_SEARCH_FRAGMENT_POSITION)
            fragment.arguments = args


            return fragment
        }
    }
}
