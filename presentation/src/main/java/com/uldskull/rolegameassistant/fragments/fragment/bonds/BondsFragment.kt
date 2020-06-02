// File BondFragment.kt
// @Author pierre.antoine - 02/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.bonds

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.CustomTextWatcher
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.BONDS_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.DomainBond
import com.uldskull.rolegameassistant.viewmodels.BondsViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import com.uldskull.rolegameassistant.viewmodels.ProgressionBarViewModel
import kotlinx.android.synthetic.main.fragment_bonds.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "BondFragment" :
 *   TODO: Fill class use.
 **/
class BondsFragment() : CustomFragment() {
    /**
     * Activity lifecycle
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return initializeView(inflater, container)
    }

    /**
     * Bond's value max characters
     */
    private val bondValueMaxCharacters = 380

    /**
     * Bond's ViewModel
     */
    private val bondsViewModel: BondsViewModel by sharedViewModel()

    /**
     * New character's ViewModel
     */
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /**
     * Progression bar's ViewModel
     */
    private val progressionBarViewModel: ProgressionBarViewModel by sharedViewModel()

    /**
     * Add bond button
     */
    private var buttonAddBond: ImageButton? = null


    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_bonds, container, false
        )
        return initialRootView
    }

    /**
     * Fragment lifecycle
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        initializeAddBondButton(view)
        tv_remainingCharactersValue.text = bondValueMaxCharacters.toString()


        setTextChangedListeners()
        setAddBondButtonListener()

        resetBondDisplay()

        loadBondsRecyclerView()

    }

    /**
     * Initialize the "add bond" button
     */
    private fun initializeAddBondButton(view: View) {
        buttonAddBond = view.findViewById(R.id.btn_addBond)
        btn_addBond?.isEnabled = false
    }

    /**
     * Set the text changed listener
     */
    private fun setTextChangedListeners() {
        setBondTitleTextChangedListener()
        setBondValueTextChangedListener()
    }

    private fun resetBondDisplay() {
        etBondTitle.setText("")
        etBondValue.setText("")
    }

    /**
     * Sets the bonds recycler view.
     */
    private fun loadBondsRecyclerView() {
        if (activity != null) {
            var transaction = childFragmentManager.beginTransaction()
            transaction.replace(
                R.id.fragmentBonds_container_bonds,
                BondsRecyclerViewFragment.newInstance(activity!!)
            ).commit()
        }

    }


    /**
     * Sets the "add bond" button listener.
     */
    private fun setAddBondButtonListener() {
        btn_addBond.setOnClickListener {
            Log.d(TAG, "setOnClickListener")
            var titleText = etBondTitle.text
            var valueText = etBondValue.text
            when {
                titleText.isNullOrEmpty() -> {
                    etBondTitle.error = "Title is required."
                }
                valueText.isNullOrEmpty() -> {
                    etBondValue.error = "Value is required."
                }
                else -> {
                    try {
                        var bonds = bondsViewModel?.bonds?.value
                        if(bonds == null){
                            bonds = mutableListOf()
                        }
                        Log.d("DEBUG$TAG", "bonds size = ${bonds?.size}")
                        bonds?.add(DomainBond(null, titleText.toString(), valueText?.toString()))
                        bondsViewModel?.bonds?.value = bonds
                    } catch (e: Exception) {
                        Log.e("ERROR", "Adding failed")
                        e.printStackTrace()
                        throw e
                    }

                }
            }
        }
    }

    /**
     * Sets the Bond's value text changed listener
     */
    private fun setBondValueTextChangedListener() {
        etBondValue.addTextChangedListener(object : CustomTextWatcher() {
            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * have just replaced old text that had length `before`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                bondsViewModel.currentBondValue = s.toString()
                if (bondsViewModel.bondValueIsInitialized() && bondsViewModel.bondTitleIsInitialized()) {
                    btn_addBond.isEnabled = true
                }
                if (!s.isNullOrBlank()) {
                    Log.d(TAG, "onTextChanged s.length = ${s.length}")
                    var remaining = bondValueMaxCharacters - s.length
                    tv_remainingCharactersValue.text = remaining.toString()
                } else {
                    tv_remainingCharactersValue.text = bondValueMaxCharacters.toString()
                }
            }

        })
    }

    private fun setBondTitleTextChangedListener() {
        etBondTitle?.addTextChangedListener(object : CustomTextWatcher() {
            /**
             * This method is called to notify you that, somewhere within
             * `s`, the text has been changed.
             * It is legitimate to make further changes to `s` from
             * this callback, but be careful not to get yourself into an infinite
             * loop, because any changes you make will cause this method to be
             * called again recursively.
             * (You are not told where the change took place because other
             * afterTextChanged() methods may already have made other changes
             * and invalidated the offsets.  But if you need to know here,
             * you can use [Spannable.setSpan] in [.onTextChanged]
             * to mark your place and then look up from here where the span
             * ended up.
             */
            override fun afterTextChanged(s: Editable?) {
                bondsViewModel.currentBondTitle = s.toString()
                if (bondsViewModel.bondValueIsInitialized() && bondsViewModel.bondTitleIsInitialized()) {
                    btn_addBond.isEnabled = true
                }
            }
        })
    }

    companion object : CustomCompanion() {
        private const val TAG = "BondsFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): BondsFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                BondsFragment(
                )
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, BONDS_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }


}