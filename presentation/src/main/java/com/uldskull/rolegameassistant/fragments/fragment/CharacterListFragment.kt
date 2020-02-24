// CharacterListFragment.kt created by UldSkull - 24/02/2020

package com.uldskull.rolegameassistant.fragments.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.CharacterSearchActivity

/**
Class "CharacterListFragment"

TODO: Describe class utility.
 */
class CharacterListFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recyclerview_characters, container, false)
    }

    companion object{
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment fragment_list.
         */
        @JvmStatic
        fun newInstance(activity: CharacterSearchActivity) : CharacterListFragment{
            return CharacterListFragment()
        }


    }
}