// File CharacterRecycerViewFragment.kt
// @Author pierre.antoine - 28/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.characterSearch

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.core.CharacterTransmission
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomRecyclerViewFragment
import com.uldskull.rolegameassistant.fragments.core.listeners.*
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.CHARACTERS_RECYCLER_VIEW_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.DomainCharacter
import com.uldskull.rolegameassistant.viewmodels.DerivedValuesViewModel
import com.uldskull.rolegameassistant.viewmodels.LifePointsViewModel
import com.uldskull.rolegameassistant.viewmodels.character.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters_recyclerview.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "CharacterRecyclerViewFragment" :
 **/
class CharacterRecyclerViewFragment :
    CustomRecyclerViewFragment(),
    CustomAdapterButtonListener<DomainCharacter>,
    DeleteCharacterButtonListener,
    EditLifePointsButtonListener,
    EditSanityButtonListener {

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
     * Adapter for character's recycler view.
     */
    private var charactersAdapter: CharactersAdapter? = null

    /**
     * Edit text for character search
     */
    private var editTextCharacterSearch: EditText? = null

    /**
     * Sorted character array.
     */
    private var arraySort: ArrayList<DomainCharacter> = ArrayList()

    /**
     * Lifepoints dialog
     */
    private var lifePointsDialog: Dialog? = null

    /**
     * Lifepoints view model
     */
    private val lifePointsViewModel: LifePointsViewModel by sharedViewModel()

    /**
     * TextView for health points
     */
    private var textViewHealthPoints: TextView? = null

    /**
     * TextView for sanity points
     */
    private var textViewSanityPoints: TextView? = null

    /**
     * Sanity dialog
     */
    private var sanityPointsDialog: Dialog? = null

    /**
     * Derived values view model
     */
    private val derivedValuesViewModel: DerivedValuesViewModel by sharedViewModel()

    /**
     * Fragment life-cycle : Called once the view is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            editTextCharacterSearch = activity!!.findViewById(R.id.et_characterSearch)


            editTextCharacterSearch?.addTextChangedListener(object : CustomTextWatcher() {
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
                    charactersAdapter =
                        CharactersAdapter(
                            activity as Context,
                            this@CharacterRecyclerViewFragment,
                            this@CharacterRecyclerViewFragment,
                            this@CharacterRecyclerViewFragment,
                            this@CharacterRecyclerViewFragment
                        )

                    //  Update the cached copy
                    charactersViewModel?.repositoryCharacters?.observe(
                        viewLifecycleOwner,
                        Observer { characters ->
                            characters?.let {
                                Log.d("DEBUG$TAG", "characters size : ${it?.size}")
                                charactersAdapter?.setItems(it as List<DomainCharacter>)
                            }
                        })

                    //  Checks the text length
                    textLength = if (editTextCharacterSearch?.text?.length != null) {
                        editTextCharacterSearch?.text?.length
                    } else {
                        0
                    }
                    Log.d("DEBUG$TAG", "TextLength : $textLength")
                    //  Clear the array sort
                    arraySort.clear()

                    if (editTextCharacterSearch?.text != null) {
                        //  For each character value
                        for (i in charactersValuesArray.indices) {
                            Log.d("DEBUG$TAG", "Indice = $i")
                            Log.d(
                                "DEBUG$TAG",
                                "textLength!! <= charactersValuesArray[i].characterName!!.length = ${textLength!! <= charactersValuesArray[i].characterName!!.length}"
                            )
                            if (textLength!! <= charactersValuesArray[i].characterName!!.length) {
                                if (charactersValuesArray[i]?.characterName!!.toLowerCase()
                                        .trim()
                                        .contains(
                                            editTextCharacterSearch?.text!!.toString()
                                                .toLowerCase()
                                                .trim {
                                                    it <= ' '
                                                })
                                ) {
                                    Log.d("DEBUG$TAG", "Add")
                                    arraySort.add(charactersValuesArray[i])
                                } else {
                                    Log.d("DEBUG$TAG", "Not added")
                                }
                            }
                        }
                        Log.d("DEBUG$TAG", "Array sort length : ${arraySort?.size}")
                        arraySort.let { charactersAdapter?.setItems(it) }
                        Log.d(
                            "DEBUG$TAG",
                            "charactersAdapter?.itemCount: ${charactersAdapter?.getItemCount()}"
                        )
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

        var charactersValuesArray: ArrayList<DomainCharacter> = ArrayList()

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
        observeCharacters()
        observeLifePointsValue()
        observeSanityScoreValue()
    }


    /**
     * Observe characters
     */
    private fun observeCharacters() {
        this.charactersViewModel.repositoryCharacters?.observe(
            this,
            Observer {
                kotlin.run {
                    it?.let { it ->
                        if (it != null && it.isNotEmpty()) {
                            charactersAdapter?.setItems(it as List<DomainCharacter>)
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
            charactersAdapter = CharactersAdapter(activity as Context, this, this, this, this)
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

    /**
     * Called when the "delete" button is pushed
     */
    override fun deleteCharacter(domainCharacter: DomainCharacter) {
        Log.d("DEBUG$TAG", "Character : ${domainCharacter.characterName}")
        var result: Int? = -1
        if (activity != null) {
            val confirmDialog = AlertDialog.Builder(activity)

            confirmDialog.setTitle(resources.getString(R.string.delete_character_confirm))
            val confirmDialogItems = arrayOf(
                resources.getString(R.string.no_cancel),
                resources.getString(R.string.yes_delete)
            )

            confirmDialog.setItems(
                confirmDialogItems
            ) { _, which ->
                Log.d("DEBUG$TAG", "Which : $which")
                when (which) {
                    1 -> kotlin.run {
                        result = charactersViewModel.deleteOne(domainCharacter)

                        var index =
                            charactersAdapter?.itemList?.indexOfFirst { c -> c.characterId == domainCharacter?.characterId }
                        Log.d("DEBUG$TAG", "Index : $index")
                        if (index != null) {
                            var adapterItems = charactersAdapter?.itemList
                            if (adapterItems != null) {
                                Log.d(
                                    "DEBUG$TAG",
                                    "Adapter items size before : ${adapterItems?.size}"
                                )
                                adapterItems?.remove(domainCharacter)
                                Log.d(
                                    "DEBUG$TAG",
                                    "Adapter items size after : ${adapterItems?.size}"
                                )
                                charactersAdapter?.setItems(adapterItems?.toList())
                            }
                        }
                    }
                }
            }
            confirmDialog.show()
        }
    }

    /**
     * Observe life points value
     */
    private fun observeLifePointsValue() {
        lifePointsViewModel.lifePoints.observe(this, Observer { healthPoints ->
            Log.d("DEBUG$TAG", "LifePoints : $healthPoints")
            if (healthPoints != null && textViewHealthPoints != null) {
                textViewHealthPoints?.text = healthPoints.toString()
            }
        })
    }

    /**
     * Observe sanity score value
     */
    private fun observeSanityScoreValue() {
        derivedValuesViewModel.sanityScore.observe(this, Observer { sanity ->
            Log.d("DEBUG$TAG", "sanity : $sanity")
            if (sanity != null && textViewSanityPoints != null) {
                textViewSanityPoints?.text = sanity.toString()
            }
        })
    }


    /**
     * Called to display the character life points edition pop up window
     */
    private fun showLifePointsPopUp(domainCharacter: DomainCharacter) {
        Log.d("DEBUG$TAG", "showLifePointsPopUp")
        lifePointsDialog = Dialog(activity!!)
        if (lifePointsDialog != null) {
            lifePointsDialog?.setContentView(R.layout.popup_character)

            //  Displays base health points
            textViewHealthPoints =
                lifePointsDialog?.findViewById(R.id.popupCharacter_textView_healthPoints)

            // Declares buttons
            var imageButtonMinus: ImageButton? = null
            var imageButtonPlus: ImageButton? = null
            var imageButtonCancel: ImageButton? = null
            var imageButtonSave: ImageButton? = null

            //  Deserializes buttons
            imageButtonMinus =
                lifePointsDialog?.findViewById(R.id.popupCharacter_imageButton_minus)
            imageButtonPlus =
                lifePointsDialog?.findViewById(R.id.popupCharacter_imageButton_plus)
            imageButtonCancel =
                lifePointsDialog?.findViewById(R.id.popupCharacter_imageButton_cancel)
            imageButtonSave =
                lifePointsDialog?.findViewById(R.id.popupCharacter_imageButton_save)

            //  Checks if buttons are deserialized
            if (imageButtonMinus == null || imageButtonPlus == null || imageButtonCancel == null || imageButtonSave == null) {
                throw Exception("Button is null.")
            }

            //  Gets character health points
            if (domainCharacter.characterHealthPoints != null) {
                lifePointsViewModel.lifePoints.value = domainCharacter.characterHealthPoints
            } else {
                lifePointsViewModel.lifePoints.value = 0
            }

            //  Sets the minus button onClickListener
            imageButtonMinus.setOnClickListener {
                Log.d("DEBUG$TAG", "Minus")
                var initialLifePoints: Int? = 0
                lifePointsViewModel.lifePoints.observe(this, Observer { lifePoints ->
                    initialLifePoints = lifePoints
                })
                if (initialLifePoints!! >= 1) {
                    var newLifePoints = initialLifePoints!! - 1
                    lifePointsViewModel.lifePoints.value = newLifePoints
                }
            }

            //  Sets the plus button onClickListener
            imageButtonPlus.setOnClickListener {
                Log.d("DEBUG$TAG", "Plus")
                var initialLifePoints: Int? = 0
                lifePointsViewModel.lifePoints.observe(this, Observer { lifePoints ->
                    initialLifePoints = lifePoints
                })
                var newLifePoints = initialLifePoints!! + 1
                lifePointsViewModel.lifePoints.value = newLifePoints
            }

            //  Sets the cancel button onClickListener
            imageButtonCancel.setOnClickListener {
                lifePointsDialog!!.dismiss()
            }

            //  Sets the save button onClickListener
            imageButtonSave.setOnClickListener {
                var oldCharacter: DomainCharacter? = null
                charactersViewModel?.repositoryCharacters?.observe(this, Observer { list ->
                    Log.d("DEBUG$TAG", "Character id : ${domainCharacter.characterId}")
                    oldCharacter =
                        list?.firstOrNull { c -> c?.characterId == domainCharacter.characterId }
                })
                if (oldCharacter == null) {
                    Log.d("DEBUG$TAG", "Old Character is null")
                    //  Do nothing
                } else {
                    Log.d("DEBUG$TAG", "Old Character is not null")
                    // Gets the life points
                    lifePointsViewModel.lifePoints.observe(this, Observer { healthPoints ->
                        oldCharacter?.characterHealthPoints = healthPoints
                    })
                    //  Update the character
                    charactersViewModel.updateOne(oldCharacter)
                }
            }

            //  Shows the dialog
            lifePointsDialog?.show()
        }
    }

    /**
     * Called when the "edit life points" button is pushed
     */
    override fun editLifePoints(domainCharacter: DomainCharacter) {
        Log.d("DEBUG$TAG", "Edit life points")
        if (activity != null) {
            showLifePointsPopUp(domainCharacter)
        }
    }

    /**
     * Called when the "edit cthulhu myth" button is pushed
     */
    override fun editSanityScore(domainCharacter: DomainCharacter) {
        Log.d("DEBUG$TAG", "Edit cthulhu myth")
        if (activity != null) {
            showSanityPopUp(domainCharacter)
        }
    }

    /**
     * Called to display the character sanity edition pop up windows
     */
    private fun showSanityPopUp(domainCharacter: DomainCharacter) {
        Log.d("DEBUG$TAG", "showSanityPopUp")
        sanityPointsDialog = Dialog(activity!!)
        if (sanityPointsDialog != null) {
            sanityPointsDialog?.setContentView(R.layout.popup_sanity)

            //  Displays base sanity
            textViewSanityPoints =
                sanityPointsDialog?.findViewById(R.id.popupSanity_textView_sanityPoints)

            //  Declares buttons
            var imageButtonMinus: ImageButton? = null
            var imageButtonPlus: ImageButton? = null
            var imageButtonCancel: ImageButton? = null
            var imageButtonSave: ImageButton? = null

            //  Deserializes buttons
            imageButtonMinus =
                sanityPointsDialog?.findViewById(R.id.popupSanity_imageButton_minus)
            imageButtonPlus =
                sanityPointsDialog?.findViewById(R.id.popupSanity_imageButton_plus)
            imageButtonCancel =
                sanityPointsDialog?.findViewById(R.id.popupSanity_imageButton_cancel)
            imageButtonSave =
                sanityPointsDialog?.findViewById(R.id.popupSanity_imageButton_save)

            //  Checks if buttons are deserialized
            if (imageButtonMinus == null || imageButtonPlus == null || imageButtonCancel == null || imageButtonSave == null) {
                throw Exception("Button is null.")
            }

            //  Gets character sanity points
            if (domainCharacter.characterSanity != null) {
                derivedValuesViewModel.sanityScore.value = domainCharacter.characterSanity
            } else {
                derivedValuesViewModel.sanityScore.value = 99
            }

            //  Sets the minus button onClickListener
            imageButtonMinus.setOnClickListener {
                Log.d("DEBUG$TAG", "Minus")
                var initialSanity: Int? = 0
                derivedValuesViewModel.sanityScore.observe(this, Observer { sanity ->
                    initialSanity = sanity
                })
                var newSanityPoints = initialSanity!! - 1
                derivedValuesViewModel.sanityScore.value = newSanityPoints
            }

            //  Sets the plus button onClickListener
            imageButtonPlus.setOnClickListener {
                Log.d("DEBUG$TAG", "Minus")
                var initialSanity: Int? = 0
                derivedValuesViewModel.sanityScore.observe(this, Observer { sanity ->
                    initialSanity = sanity
                })
                var newSanityPoints = initialSanity!! + 1
                derivedValuesViewModel.sanityScore.value = newSanityPoints
            }

            //  Sets the cancel button onClickListener
            imageButtonCancel.setOnClickListener {
                sanityPointsDialog!!.dismiss()
            }

            //  Sets the save button onClickListener
            imageButtonSave.setOnClickListener {
                var oldCharacter: DomainCharacter? = null
                charactersViewModel.repositoryCharacters?.observe(this, Observer { list ->
                    oldCharacter =
                        list?.firstOrNull { c -> c?.characterId == domainCharacter.characterId }
                })
                if (oldCharacter == null) {
                    // Do nothing
                } else {
                    derivedValuesViewModel.sanityScore.observe(this, Observer { sanity ->
                        Log.d("DEBUG$TAG"," Sanity observed = $sanity")
                        oldCharacter?.characterSanity = sanity
                    })
                    //Update the character
                    charactersViewModel.updateOne(oldCharacter)
                }
            }
            //  Shows the dialog
            sanityPointsDialog?.show()
        }
    }
}