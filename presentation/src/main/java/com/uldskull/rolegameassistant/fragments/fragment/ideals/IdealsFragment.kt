// File IdealsFragment.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.ideals

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewIdealActivity
import com.uldskull.rolegameassistant.fragments.fragment.CustomCompanion
import com.uldskull.rolegameassistant.fragments.fragment.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_IDEALS_NEW_IDEAL
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.IDEALS_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.viewmodels.IdealsViewModel
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_ideals.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "IdealsFragment" :
 *   Fragment to manage "Ideals" view.
 **/
class IdealsFragment() : CustomFragment() {

    val idealsViewModel:IdealsViewModel by sharedViewModel()
    val newCharacterViewModel:NewCharacterViewModel by sharedViewModel()

    /**
     * Called when the view is created
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeView(inflater, container)
    }

    /**
     * Initialize the view
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_ideals, container, false
        )
        return initialRootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadIdealsRecyclerView()
        setButtonAddIdeal()

        var gotIdeals = mutableListOf<DomainIdeal>()


        idealsViewModel?.characterIdeals?.observe(this, Observer {domainIdeals:List<DomainIdeal?>? ->
            if(domainIdeals != null){


                var mutableIdeals = idealsViewModel?.mutableIdeals?.value
                if(mutableIdeals == null){
                    mutableIdeals = mutableListOf()
                }
                domainIdeals?.forEach { characterIdeal ->
                    Log.d("DEBUG$TAG", "characterIdeal ${characterIdeal?.idealName} is checked : ${characterIdeal?.isChecked}")
                    mutableIdeals?.add(characterIdeal)
                }


                idealsViewModel?.mutableIdeals?.value = mutableIdeals

            }
        })

        idealsViewModel.repositoryIdeals?.observe(this, Observer { domainIdeals ->
            domainIdeals?.forEach {
                Log.d("DEBUG$TAG", "repositoryIdeals ${it?.idealName} is checked : ${it?.isChecked}")
            }

            idealsViewModel?.mutableIdeals?.value = domainIdeals.toMutableList()


        })
    }

    private fun loadIdealsRecyclerView() {
        if (activity != null) {
            var transaction = childFragmentManager.beginTransaction()
            transaction.replace(
                R.id.fragmentIdeals_container_ideals,
                IdealsRecyclerViewFragment.newInstance(
                    activity!!
                )
            ).commit()
        }

    }

    private fun setButtonAddIdeal() {
        if (btn_addIdeal != null) {
            btn_addIdeal?.setOnClickListener {
                val intent = Intent(activity, NewIdealActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE_IDEALS_NEW_IDEAL)
            }
        }
    }

    companion object : CustomCompanion() {

        private const val TAG = "IdealsFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): IdealsFragment {
            val fragment =
                IdealsFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, IDEALS_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }
}