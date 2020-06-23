// File NewIdealActivity.kt
// @Author pierre.antoine - 03/03/2020 - No copyright.

package com.uldskull.rolegameassistant.activities.editIdeal

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.CustomActivity
import com.uldskull.rolegameassistant.activities.replaceFragment
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomAdapterButtonListener
import com.uldskull.rolegameassistant.fragments.core.listeners.CustomTextWatcher
import com.uldskull.rolegameassistant.fragments.fragment.bars.NavigationBarFragment
import com.uldskull.rolegameassistant.fragments.fragment.ideals.ideal_toEdit.IdealsToEditAdapter
import com.uldskull.rolegameassistant.models.DomainIdeal
import com.uldskull.rolegameassistant.viewmodels.IdealsViewModel
import kotlinx.android.synthetic.main.activity_ideals_edit.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "NewIdealActivity" :
 *   Activity for a new ideal creation
 **/
class EditIdealsActivity : CustomActivity(),
    CustomAdapterButtonListener<DomainIdeal> {
    companion object {
        private const val TAG = "NewIdealActivity"
    }

    /**
     * Delete ideal image button
     */
    private var deleteIdealButton: ImageButton? = null

    /**
     * ideal good points edit text
     */
    private var idealGoodPointsEditText: EditText? = null

    /**
     * ideal evil points edit text
     */
    private var idealEvilPointsEditText: EditText? = null

    /**
     * add ideal button
     */
    private var addIdealButton: Button? = null

    /**
     * ideals view model
     */
    private lateinit var idealsViewModel: IdealsViewModel

    /**
     * ideal to edit recycler view
     */
    private var idealToEditRecyclerView: RecyclerView? = null

    /**
     * ideal title edit text
     */
    private var setIdealTitleEditText: EditText? = null

    /**
     * save ideal button
     */
    private var saveIdealImageButton: ImageButton? = null

    /**
     * Activity life-cycle, called when the activity is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_ideals_edit)
        loadNavigationBarFragment()
        idealsViewModel = getViewModel()
        idealToEditRecyclerView =
            this.findViewById(R.id.activityEditIdeals_recyclerView_displayedIdeals)
        initializeSetIdealTitleEditText()
        initializeSetIdealGoodPointsEditText()
        initializeSetIdealEvilPointsEditText()

        initializeSaveIdealButton()
        initializeAddIdealButton()
        initializeDeleteIdealButton()



        startObservation()
    }


    /**
     * initialize the evil points edit text
     */
    private fun initializeSetIdealEvilPointsEditText() {
        idealEvilPointsEditText =
            this.findViewById(R.id.activityEditIdeals_editText_idealEvilPoints)
        idealEvilPointsEditText?.addTextChangedListener(object : CustomTextWatcher() {
            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * have just replaced old text that had length `before`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                if (s != null) {
                    val oldIdeal = idealsViewModel.currentIdealToEdit
                    val newIdeal =
                        DomainIdeal(
                            isChecked = oldIdeal?.isChecked,
                            idealGoodPoints = oldIdeal?.idealGoodPoints,
                            idealEvilPoints = s.toString().toInt(),
                            idealId = oldIdeal?.idealId,
                            idealName = oldIdeal?.idealName
                        )

                    idealsViewModel.currentIdealToEdit = newIdeal
                }
            }
        })
    }

    /**
     * initialize the good points edit text
     */
    private fun initializeSetIdealGoodPointsEditText() {
        idealGoodPointsEditText =
            this.findViewById(R.id.activityEditIdeals_editText_idealGoodPoints)
        idealGoodPointsEditText?.addTextChangedListener(object : CustomTextWatcher() {
            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * have just replaced old text that had length `before`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                if (s != null && s.isNotBlank() && s.isNotEmpty()) {
                    val oldIdeal = idealsViewModel.currentIdealToEdit
                    val newIdeal =
                        DomainIdeal(
                            isChecked = oldIdeal?.isChecked,
                            idealGoodPoints = s.toString().toInt(),
                            idealEvilPoints = oldIdeal?.idealEvilPoints,
                            idealName = oldIdeal?.idealName,
                            idealId = oldIdeal?.idealId
                        )

                    idealsViewModel.currentIdealToEdit = newIdeal
                }
            }
        })
    }

    /**
     * initialize the ideal title edit text
     */
    private fun initializeSetIdealTitleEditText() {
        setIdealTitleEditText = this.findViewById(R.id.activityEditIdeals_editText_idealTitle)
        setIdealTitleEditText?.addTextChangedListener(object : CustomTextWatcher() {
            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * have just replaced old text that had length `before`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                if (s != null) {
                    val oldIdeal = idealsViewModel.currentIdealToEdit
                    val newIdeal =
                        DomainIdeal(
                            isChecked = oldIdeal?.isChecked,
                            idealEvilPoints = oldIdeal?.idealEvilPoints,
                            idealGoodPoints = oldIdeal?.idealGoodPoints,
                            idealName = s.toString(),
                            idealId = oldIdeal?.idealId
                        )

                    idealsViewModel.currentIdealToEdit = newIdeal
                }
            }
        })
    }

    /**
     * initialize the add ideal button
     */
    private fun initializeAddIdealButton() {
        addIdealButton = this.findViewById(R.id.activityEditIdeals_imageButton_addNewIdeal)
        if (addIdealButton != null) {
            addIdealButton?.setOnClickListener {
                val id = idealsViewModel.insertIdeal(
                    DomainIdeal(
                        idealId = null,
                        idealName = "Fill the name",
                        idealGoodPoints = 0,
                        idealEvilPoints = 0,
                        isChecked = false
                    )
                )

                idealsViewModel.refreshDataFromRepository()

                val ideal = idealsViewModel.getIdealById(id)
                if (ideal != null) {
                    idealsViewModel.currentIdealToEdit = ideal
                    setIdealTitleEditText?.setText(ideal.idealName)
                    idealGoodPointsEditText?.setText(ideal.idealGoodPoints?.toString())
                    idealEvilPointsEditText?.setText(ideal.idealEvilPoints?.toString())
                }
            }
        }

    }

    /**
     * initialize the delete ideal button
     */
    private fun initializeDeleteIdealButton() {
        deleteIdealButton =
            this.findViewById(R.id.activityEditIdeals_imageButton_deleteIdeal)
        if (deleteIdealButton == null) {
            throw Exception("Button is null")
        }
        deleteIdealButton!!.setOnClickListener {
            if (idealsViewModel.currentIdealToEdit != null) {
                idealsViewModel.deleteIdeal(idealsViewModel.currentIdealToEdit!!)
            }
            idealsViewModel.refreshDataFromRepository()

            val ideals = idealsViewModel.getAll()

            ideals?.forEach { i -> Log.d("DEBUG$TAG", "Ideal : ${i.idealName}") }
        }


    }

    /**
     * initialize the save ideal button
     */
    private fun initializeSaveIdealButton() {
        saveIdealImageButton = this.findViewById(R.id.activityEditIdeals_imageButton_saveIdeal)
        if (saveIdealImageButton == null) {
            throw Exception("Button is null")
        }
        saveIdealImageButton!!.setOnClickListener {

            if (idealsViewModel.currentIdealToEdit != null) {
                idealsViewModel.insertIdeal(idealsViewModel.currentIdealToEdit!!)
            }
            idealsViewModel.refreshDataFromRepository()
            val ideals = idealsViewModel.getAll()

            ideals?.forEach { i -> Log.d("DEBUG$TAG", "Ideal : ${i.idealName}") }
        }

    }

    /**
     * start observation
     */
    private fun startObservation() {
        observeRepositoryIdeals()
    }

    /**
     * observe repository ideals
     */
    private fun observeRepositoryIdeals() {
        idealsViewModel.repositoryIdeals?.observe(this, Observer {
            val recyclerViewAdapter = IdealsToEditAdapter(this, this)
            recyclerViewAdapter.setIdeals(it)
            idealToEditRecyclerView?.adapter = recyclerViewAdapter
            val layoutManager = LinearLayoutManager(
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
            R.id.activityEditBreed_layout_navigationBar,
            NavigationBarFragment.newInstance(this)
        )
    }

    /**
     * Called when a recyclerview cell is pressed
     */
    override fun itemPressed(domainIdeal: DomainIdeal?, position: Int?) {
        Log.d("DEBUG$TAG", "Item pressed")
        if (domainIdeal != null) {
            activityEditIdeals_editText_idealTitle.setText(domainIdeal.idealName)
            activityEditIdeals_editText_idealGoodPoints.setText(domainIdeal.idealGoodPoints.toString())
            activityEditIdeals_editText_idealEvilPoints.setText(domainIdeal.idealEvilPoints.toString())

            idealsViewModel.currentIdealToEdit = domainIdeal
        }
    }
}