// File CharacterRecycerViewFragment.kt
// @Author pierre.antoine - 28/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.characterSearch

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.adapter.CHARACTERS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.viewmodels.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "CharacterRecycerViewFragment" :
 *   TODO: Fill class use.
 **/
class CharacterRecyclerViewFragment(activity: Activity) :
    CustomRecyclerViewFragment(activity),
    AdapterButtonListener<DomainCharacter> {

    /**
     * Character recycler view
     */
    private var characterRecyclerView: RecyclerView? = null

    /**
     * Viewmodel that manage characters.
     */
    private val charactersViewModel: CharactersViewModel by sharedViewModel()

    /**
     * Adapter for character's recycler view.
     */
    private var charactersAdapter: CharactersAdapter? = null

    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        characterRecyclerView = activity.findViewById<RecyclerView>(R.id.recycler_view_characters)

        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        this.charactersViewModel.characters?.observe(this, Observer { characters ->
            kotlin.run {
                characters.let {
                    charactersAdapter?.setCharacters(it)
                }
            }
        })
    }

    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {
        charactersAdapter = CharactersAdapter(activity as Context, this)
        characterRecyclerView?.adapter = charactersAdapter
    }

    /**
     * Set the RecyclerView's layout manager.
     */
    override fun setRecyclerViewLayoutManager() {
        characterRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_characters, container, false
        )
        return initialRootView
    }


    companion object : CustomCompanion() {
        override fun newInstance(activity: Activity): CharacterRecyclerViewFragment {
            Log.d("CharacterRecyclerViewFragent", "newInstance")
            val fragment = CharacterRecyclerViewFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, CHARACTERS_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainCharacter: DomainCharacter?) {
        Log.d("TEST PRESS", "Button pressed for ${domainCharacter?.characterName}")
        this.charactersViewModel.selectedCharacter = domainCharacter
    }
}