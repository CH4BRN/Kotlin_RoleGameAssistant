// PictureFragment.kt created by UldSkull - 24/06/2020

package com.uldskull.rolegameassistant.fragments.fragment.picture

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.basicinfo.BasicInfoFragment
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.PICTURE_FRAGMENT_POSITION

/**
Class "PictureFragment"

Fragment to chose picture
 */
class PictureFragment : CustomFragment() {
    /**
     * Initialize the initial root view.
     */
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_picture, container, false
        )
        return initialRootView
    }

    /**
     * Called to load children fragments after the fragment creation.
     * This is called in [.onViewCreated]"
     */
    override fun loadChildrenFragments() {
        super.loadChildrenFragments()
        loadPictureFragment()
    }

    /**
     * Load picture fragment
     */
    private fun loadPictureFragment() {
        if (activity != null) {
            try {
                val transaction = childFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.fragmentPicture_layout_picturePicker,
                    PicturePickerFragment.newInstance(
                        activity!!
                    )
                ).commit()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    /**
     * Companion object
     */
    companion object : CustomCompanion() {
        /**
         * Fragment's TAG
         */
        private const val TAG = "PictureFragment"

        /**
         * Singleton
         */
        override fun newInstance(activity: Activity): CustomFragment {
            val fragment = PictureFragment()
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, PICTURE_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }

    }

}