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
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.character.CharacterTransmission
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.CHARACTERS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomTextWatcher
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.viewmodels.character.CharactersViewModel
import com.uldskull.rolegameassistant.viewmodels.character.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_characters_recyclerview.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "CharacterRecyclerViewFragment" :
 **/
class CharacterRecyclerViewFragment :
    CustomRecyclerViewFragment(),
    CustomAdapterButtonListener<DomainCharacter> {

    private var textLength: Int? = 0

    /**
     * Character transmitter
     */
    private var characterTransmitter: CharacterTransmission? = null

    /**
     * Character recycler view
     */
    private var characterRecyclerView: RecyclerView? = null

    /**
     * Viewmodel that manages characters.
     */
    private val charactersViewModel: CharactersViewModel by sharedViewModel()

    /**
     * ViewModel for character creation/edition
     */
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /**
     * Adapter for character's recycler view.
     */
    private var charactersAdapter: CharactersAdapter? = null

    /**
     * Edit text for character search
     */
    private var editTextCharacterSearch:EditText? = null

    private var arraySort:ArrayList<DomainCharacter> = ArrayList()

    /**
     * Fragment life-cycle : Called once the view is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            editTextCharacterSearch = activity!!.findViewById(R.id.et_characterSearch)


            editTextCharacterSearch?.addTextChangedListener(object :CustomTextWatcher(){
                /**
                 * This method is called to notify you that, within `s`,
                 * the `count` characters beginning at `start`
                 * have just replaced old text that had length `before`.
                 * It is an error to attempt to make changes to `s` from
                 * this callback.
                 */
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    super.onTextChanged(s, start, before, count)
                    // Instantiate the adapter
                    charactersAdapter = CharactersAdapter(activity as Context, this@CharacterRecyclerViewFragment)

                    //  Update the cached copy
                    charactersViewModel?.characters?.observe(viewLifecycleOwner, Observer { characters ->
                        characters?.let {
                            Log.d("DEBUG$TAG", "characters size : ${it?.size}")
                            charactersAdapter?.setCharacters(it) }
                    })

                    //  Checks the text length
                    textLength = if (editTextCharacterSearch?.text?.length != null){
                        editTextCharacterSearch?.text?.length
                    }else {
                        0
                    }
                    Log.d("DEBUG$TAG", "TextLength : $textLength")
                    //  Clear the array sort
                    arraySort.clear()

                    if(editTextCharacterSearch?.text != null){
                        //  For each character value
                        for (i in charactersValuesArray.indices){
                            Log.d("DEBUG$TAG", "Indice = $i")
                            Log.d("DEBUG$TAG", "textLength!! <= charactersValuesArray[i].characterName!!.length = ${textLength!! <= charactersValuesArray[i].characterName!!.length}")
                            if(textLength!! <= charactersValuesArray[i].characterName!!.length){
                                if(charactersValuesArray[i]?.characterName!!.toLowerCase()
                                        .trim()
                                        .contains(
                                            editTextCharacterSearch?.text!!.toString()
                                                .toLowerCase()
                                                .trim{
                                                    it <= ' '
                                                })
                                ){
                                    Log.d("DEBUG$TAG", "Add")
                                    arraySort.add(charactersValuesArray[i])
                                }else{
                                    Log.d("DEBUG$TAG", "Not added")
                                }
                            }
                        }
                        Log.d("DEBUG$TAG", "Array sort length : ${arraySort?.size}")
                        arraySort.let { charactersAdapter?.setCharacters(it) }
                        Log.d("DEBUG$TAG", "charactersAdapter?.itemCount: ${charactersAdapter?.itemCount}")
                        recycler_view_characters?.adapter = charactersAdapter
                        recycler_view_characters?.layoutManager =
                            LinearLayoutManager(
                                activity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                    }


                }
            })
        }

    }

    /**
     * Companion object
     */
    companion object : CustomCompanion() {
        override fun newInstance(activity: Activity): CharacterRecyclerViewFragment {
            Log.d("CharacterRecyclerViewFragment", "newInstance")
            val fragment = CharacterRecyclerViewFragment()
            val args = Bundle()
            fragment.activity = activity
            fragment.characterTransmitter = activity as CharacterTransmission

            args.putInt(KEY_POSITION, CHARACTERS_RECYCLER_VIEW_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }

        var charactersValuesArray:ArrayList<DomainCharacter> = ArrayList()

        const val TAG = "CharacterRecyclerViewFragment"
    }

    /**
     * Initialize the recycler view.
     */
    override fun initializeRecyclerView() {
        characterRecyclerView = activity?.findViewById(R.id.recycler_view_characters)

        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }

    /**
     * Start ViewModel's collection observation.
     */
    override fun startObservation() {
        this.charactersViewModel.characters?.observe(
            this,
            Observer {
                kotlin.run {
                    it?.let { it ->
                        if (it != null && it.isNotEmpty()) {
                            charactersAdapter?.setCharacters(it)
                            arraySort.clear()
                            arraySort.addAll(it as Collection<DomainCharacter>)
                            Log.d("DEBUG$TAG", "Array sort length : ${arraySort?.size}")
                            charactersValuesArray = ArrayList(it)
                        }

                    }
                }
            })
    }

    /**
     * Set the recycler view adapter.
     */
    override fun setRecyclerViewAdapter() {
        if (activity != null) {
            charactersAdapter = CharactersAdapter(activity as Context, this)
            characterRecyclerView?.adapter = charactersAdapter
        }

    }

    /**
     * Set the RecyclerView's layout manager.
     */
    override fun setRecyclerViewLayoutManager() {
        if (activity != null) {
            characterRecyclerView?.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }

    }

    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_characters_recyclerview, container, false
        )
        return initialRootView
    }


    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainCharacter?, position: Int?) {
        Log.d("DEBUG", "Button pressed for $domainModel")
        characterTransmitter?.transmitCharacter(domainModel)
    }
}