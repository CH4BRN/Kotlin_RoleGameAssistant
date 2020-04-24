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
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.adapter.BONDS_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.viewmodels.BondsViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_bonds.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "BondFragment" :
 *   TODO: Fill class use.
 **/
class BondsFragment(activity: Activity) : CustomFragment(activity) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return initializeView(inflater, container)
    }

    private val bondValueMaxCharacters = 380

    private val bondsViewModel: BondsViewModel by sharedViewModel()

    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        NewCharacterActivity.progression.value = BONDS_FRAGMENT_POSITION

    }

    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_bonds, container, false
        )
        return initialRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        btn_addBond?.isEnabled = false
        tv_remainingCharactersValue.text = bondValueMaxCharacters.toString()

        setBondTitleTextChangedListener()
        setBondValueTextChangedListener()
        setAddBondButtonListener()

        etBondTitle.setText("")
        etBondValue.setText("")

    }

     private fun setAddBondButtonListener() {
        btn_addBond.setOnClickListener {
            Log.d(TAG, "setOnClickListener")
            var titleText = etBondTitle.text
            var valueText = etBondValue.text
            if(titleText.isNullOrEmpty() ){
                etBondTitle.error = "Title is required."
            }else if(valueText.isNullOrEmpty()){
                etBondValue.error = "Value is required."
            }
            else{
                newCharacterViewModel.characterBonds = bondsViewModel.addBond()
            }


        }
    }

    private fun setBondValueTextChangedListener() {
        etBondValue.addTextChangedListener(object : TextWatcher {
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

            }

            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * are about to be replaced by new text with length `after`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

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
        etBondTitle?.addTextChangedListener(object : TextWatcher {
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

            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * are about to be replaced by new text with length `after`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * have just replaced old text that had length `before`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
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
                    activity
                )

            val args = Bundle()

            args.putInt(KEY_POSITION, BONDS_FRAGMENT_POSITION)
            fragment.arguments = args
            (activity as NewCharacterActivity).replaceFragment(
                R.id.fragmentBonds_container_bonds,
                BondsRecyclerViewFragment.newInstance(activity)
            )

            return fragment
        }
    }


}