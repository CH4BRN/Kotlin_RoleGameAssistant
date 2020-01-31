// File PictureFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.presentation.fragments.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.presentation.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.presentation.view_model.NewCharacterViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "PictureFragment" :
 *   Handle character's picture
 **/
class PictureFragment(val context: Activity) : Fragment() {

    private lateinit var newCharacterViewModel: NewCharacterViewModel
    /** Fragment Lifecycle  **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newCharacterViewModel = getViewModel()

        return initializeView(inflater, container)
    }

    fun selectImageAlbum() {
        if (activityIsNull()) return
        val intent = getImageIntent()

        if (intent.resolveActivity(activity!!.packageManager) != null) {
            startSelectImageActivity(intent)
        }
    }

    private fun activityIsNull(): Boolean {
        if (activity == null) {
            return true
        }
        return false
    }

    private fun startSelectImageActivity(intent: Intent) {
        activity!!.startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE_IN_ALBUM)
    }

    private fun getImageIntent(): Intent {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        return intent
    }

    /** Initialize the view corresponding to this fragment class    **/
    private fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_picture, container, false
        )
        return initialRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var imageButton = activity?.findViewById<ImageButton>(R.id.img_btn_characterPicture)

        setImageButtonListener(imageButton)
    }

    private fun setImageButtonListener(imageButton: ImageButton?) {
        imageButton?.setOnClickListener(View.OnClickListener {
            selectImageAlbum()
        })
    }

    companion object {

        @JvmStatic
        fun newInstance(activity: NewCharacterActivity, position: Int): PictureFragment {
            val fragment =
                PictureFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }

        /** Key position code   **/
        private const val KEY_POSITION = "position"
        /** Initial root view.  **/
        private lateinit var initialRootView: View
        /** Request code for image selection    **/
        private const val REQUEST_CODE_SELECT_IMAGE_IN_ALBUM = 1
    }

}