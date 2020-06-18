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
import com.uldskull.rolegameassistant.fragments.fragment.AdapterButtonListener
import com.uldskull.rolegameassistant.fragments.fragment.CustomTextWatcher
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

    private var deleteIdealButton: ImageButton? = null
    private var setIdealGoodPointsEditText: EditText? = null
    private var setIdealEvilPointsEditText:EditText? = null
    private var addIdealButton: Button? = null
    private lateinit var idealsViewModel: IdealsViewModel

    private var idealToEditRecyclerView: RecyclerView? = null

    private var setIdealTitleEditText: EditText? = null

    private var saveIdealImageButton: ImageButton? = null

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
        initializeSetIdealTitleEditText()
        initializeSetIdealGoodPointsEditText()
        initializeSetIdealEvilPointsEditText()

        initializeSaveIdealButton()
        initializeAddIdealButton()
        initializeDeleteIdealButton()



        startObservation()
    }



    private fun initializeSetIdealEvilPointsEditText() {
        setIdealEvilPointsEditText = this?.findViewById(R.id.et_setIdealEvilPoints)
        setIdealEvilPointsEditText?.addTextChangedListener(object: CustomTextWatcher(){
            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * have just replaced old text that had length `before`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                if(s!=null){
                    var oldIdeal = idealsViewModel?.currentIdealToEdit
                    var newIdeal = DomainIdeal(
                        isChecked = oldIdeal?.isChecked,
                        idealGoodPoints = oldIdeal?.idealGoodPoints,
                        idealEvilPoints = s.toString().toInt(),
                        idealId = oldIdeal?.idealId,
                        idealName = oldIdeal?.idealName
                    )

                    idealsViewModel?.currentIdealToEdit = newIdeal
                }
            }
        })
    }

    private fun initializeSetIdealGoodPointsEditText() {
        setIdealGoodPointsEditText = this?.findViewById<EditText>(R.id.et_setIdealGoodPoints)
        setIdealGoodPointsEditText?.addTextChangedListener(object : CustomTextWatcher(){
            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * have just replaced old text that had length `before`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                if(s != null && s.isNotBlank() && s.isNotEmpty()){
                    var oldIdeal = idealsViewModel?.currentIdealToEdit
                    var newIdeal = DomainIdeal(
                        isChecked = oldIdeal?.isChecked,
                        idealGoodPoints = s.toString()?.toInt(),
                        idealEvilPoints = oldIdeal?.idealEvilPoints,
                        idealName = oldIdeal?.idealName,
                        idealId = oldIdeal?.idealId
                    )

                    idealsViewModel?.currentIdealToEdit = newIdeal
                }
            }
        })
    }

    private fun initializeSetIdealTitleEditText() {
        setIdealTitleEditText = this.findViewById(R.id.et_setIdealTitle)
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
                    var oldIdeal = idealsViewModel?.currentIdealToEdit
                    var newIdeal = DomainIdeal(
                        isChecked = oldIdeal?.isChecked,
                        idealEvilPoints = oldIdeal?.idealEvilPoints,
                        idealGoodPoints = oldIdeal?.idealGoodPoints,
                        idealName = s.toString(),
                        idealId = oldIdeal?.idealId
                    )

                    idealsViewModel?.currentIdealToEdit = newIdeal
                }
            }
        })
    }

    private fun initializeAddIdealButton() {
        addIdealButton = this?.findViewById(R.id.imageButton_addNewIdeal)
        if (addIdealButton != null) {
            addIdealButton?.setOnClickListener {
                var id = idealsViewModel?.insertIdeal(
                    DomainIdeal(
                        idealId = null,
                        idealName = "Fill the name",
                        idealGoodPoints = 0,
                        idealEvilPoints = 0,
                        isChecked = false
                    )
                )

                idealsViewModel?.refreshDataFromRepository()

                var ideal = idealsViewModel?.getIdealById(id)
                if(ideal != null){
                    idealsViewModel?.currentIdealToEdit = ideal
                    setIdealTitleEditText?.setText(ideal?.idealName)
                    setIdealGoodPointsEditText?.setText(ideal?.idealGoodPoints?.toString())
                    setIdealEvilPointsEditText?.setText(ideal?.idealEvilPoints?.toString())
                }
            }
        }

    }

    private fun initializeDeleteIdealButton() {
        deleteIdealButton = this?.findViewById<ImageButton>(R.id.imageButton_deleteIdealToEdit)
        if(deleteIdealButton != null){
            deleteIdealButton!!.setOnClickListener{
                if(idealsViewModel?.currentIdealToEdit != null){
                    idealsViewModel?.deleteIdeal(idealsViewModel?.currentIdealToEdit!!)
                }
                idealsViewModel?.refreshDataFromRepository()

                var ideals = idealsViewModel?.getAll()

                ideals?.forEach { i -> Log.d("DEBUG$TAG", "Ideal : ${i.idealName}") }
            }
        }

    }

    private fun initializeSaveIdealButton() {
        saveIdealImageButton = this?.findViewById(R.id.imageButton_saveIdealToEdit)

        if (saveIdealImageButton != null) {
            saveIdealImageButton!!.setOnClickListener {

                if (idealsViewModel?.currentIdealToEdit != null) {
                    idealsViewModel?.insertIdeal(idealsViewModel?.currentIdealToEdit!!)
                }
                idealsViewModel?.refreshDataFromRepository()
                var ideals = idealsViewModel?.getAll()

                ideals?.forEach { i -> Log.d("DEBUG$TAG", "Ideal : ${i.idealName}") }
            }
        }
    }

    private fun startObservation() {
        observeRepositoryIdeals()
    }

    private fun observeRepositoryIdeals() {
        idealsViewModel?.repositoryIdeals?.observe(this, Observer {
            var recyclerViewAdapter = IdealsToEditAdapter(this, this)
            recyclerViewAdapter?.setIdeals(it)
            idealToEditRecyclerView?.adapter = recyclerViewAdapter
            var layoutManager = LinearLayoutManager(
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
        Log.d("DEBUG$TAG", "Item pressed")
        if (domainModel != null) {
            et_setIdealTitle.setText(domainModel.idealName)
            et_setIdealGoodPoints.setText(domainModel.idealGoodPoints.toString())
            et_setIdealEvilPoints.setText(domainModel.idealEvilPoints.toString())

            idealsViewModel?.currentIdealToEdit = domainModel
        }
    }
}