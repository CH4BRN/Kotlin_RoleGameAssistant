// File CustomRecyclerViewFragment.kt
// @Author pierre.antoine - 13/03/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.os.Bundle
import android.view.View

/**
 * Abstract class for custom recycler view fragment.
 */
abstract class CustomRecyclerViewFragment(activity: Activity) : CustomFragment(activity) {
    /**
     * Initialize the recycler view.
     */
    protected abstract fun initializeRecyclerView()

    /**
     * Start ViewModel's collection observation.
     */
    protected abstract fun startObservation()

    /**
     * Set the recycler view adapter.
     */
    protected abstract fun setRecyclerViewAdapter()

    /**
     * Set the RecyclerView's layout manager.
     */
    protected abstract fun setRecyclerViewLayoutManager()

    /**
     * Fragment life-cycle : Called once the view is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        startObservation()
        setRecyclerViewAdapter()
        setRecyclerViewLayoutManager()
    }
}