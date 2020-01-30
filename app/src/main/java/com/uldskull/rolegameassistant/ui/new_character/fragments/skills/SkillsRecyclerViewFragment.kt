// File SkillsRecyclerViewFragment.kt
// @Author pierre.antoine - 29/01/2020 - No copyright.

package com.uldskull.rolegameassistant.ui.new_character.fragments.skills

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.ui.new_character.activities.NewCharacterActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "SkillsRecyclerViewFragment" :
 *   Manage skill's RecyclerView fragment.
 **/
class SkillsRecyclerViewFragment : Fragment() {
    /** ViewModel for skills    **/
    private lateinit var skillsViewModel: SkillsViewModel

    /** Adapter for skills recycler view    **/
    private var skillsAdapter: SkillsAdapter? = null

    /** Reccycler view for skills   **/
    private var skillsRecyclerView: RecyclerView? = null

    /** Fragment life-cycle **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skillsViewModel = getViewModel()
    }

    /** Fragment life-cycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        when (NewCharacterActivity.progression.value) {
            3 -> NewCharacterActivity.progression.value = 4
            4 -> NewCharacterActivity.progression.value = 6

        }
        return initializeView(inflater, container)
    }

    /** Initialize the view **/
    private fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_recyclerview_skills, container, false
        )
        return initialRootView
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)

        when (NewCharacterActivity.progression.value) {
            4 -> NewCharacterActivity.progression.value = 6

        }
    }

    /** Fragment life-cycle **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skillsRecyclerView = activity?.findViewById(R.id.recycler_view_skills)
                as RecyclerView?

        skillsAdapter = SkillsAdapter(activity as Context)

        startSkillsObservation()

        skillsRecyclerView?.adapter = skillsAdapter

        skillsRecyclerView?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )

    }


    /** Observe ViewModel's skills  **/
    private fun startSkillsObservation() {
        this.skillsViewModel.skills.observe(this, Observer { skills ->
            kotlin.run {
                skills?.let { skillsAdapter?.setSkills(it) }
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance(activity: Activity, position: Int): SkillsRecyclerViewFragment {
            var fragment = SkillsRecyclerViewFragment()
            var args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

        private const val KEY_POSITION = "position"
        private lateinit var initialRootView: View

    }
}