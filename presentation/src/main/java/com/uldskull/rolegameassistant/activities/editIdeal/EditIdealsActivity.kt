// File NewIdealActivity.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.editIdeal

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.CustomActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.bars.NavigationBarFragment
import com.uldskull.rolegameassistant.fragments.fragment.ideals.ideal_toEdit.IdealsToEditAdapter
import com.uldskull.rolegameassistant.models.character.DomainIdeal
import com.uldskull.rolegameassistant.viewmodels.IdealsViewModel
import kotlinx.android.synthetic.main.activity_edit_ideals.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewIdealActivity" :
 *   Activity for a new ideal creation
 **/
class EditIdealsActivity : CustomActivity(), AdapterButtonListener<DomainIdeal> {
    companion object {
        private const val TAG = "NewIdealActivity"
    }

    private lateinit var idealsViewModel: IdealsViewModel

    private var idealToEditRecyclerView:RecyclerView? = null

    private var saveIdealImageButton:ImageButton? = null
    /**
     * Activity life-cycle, called when the activity is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_edit_ideals)
        loadNavigationBarFragment()
        idealsViewModel = getViewModel()
        idealToEditRecyclerView = this.findViewById(R.id.recyclerView_editIdeals)

        saveIdealImageButton = this?.findViewById(R.id.imageButton_saveIdealToEdit)

        if(saveIdealImageButton != null){
            saveIdealImageButton!!.setOnClickListener{

                if(idealsViewModel?.currentIdealToEdit != null){
                    idealsViewModel?.insertIdeal(idealsViewModel?.currentIdealToEdit!!)
                }
                idealsViewModel?.refreshDataFromRepository()

            }
        }


        startObservation()
    }

    private fun startObservation() {
        observeRepositoryIdeals()
    }

    private fun observeRepositoryIdeals() {
        idealsViewModel?.repositoryIdeals?.observe(this, Observer {
            var recyclerViewAdapter = IdealsToEditAdapter(this, this)
            recyclerViewAdapter?.setIdeals(it)
            idealToEditRecyclerView?.adapter = recyclerViewAdapter
            var layoutManager =   LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
            idealToEditRecyclerView?.layoutManager = layoutManager

        })
    }

    /**
     * Load the navigation bar fragment.
     */
    private fun loadNavigationBarFragment() {
        Log.d(TAG, "loadNavigationBarFragment")

        this.replaceFragment(
            R.id.activityNewIdeal_container_navigationBar,
            NavigationBarFragment.newInstance(this)
        )
    }

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainModel: DomainIdeal?, position: Int?) {
        Log.d("DEBUG$TAG","Item pressed")
        if(domainModel != null){
            et_idealTitle.setText(domainModel.idealName)
            et_setIdealGoodPoints.setText(domainModel.idealGoodPoints.toString())
            et_setIdealEvilPoints.setText(domainModel.idealEvilPoints.toString())

            idealsViewModel?.currentIdealToEdit = domainModel
        }
    }
}