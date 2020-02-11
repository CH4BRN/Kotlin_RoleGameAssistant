// File PictureFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.activities.NewCharacterActivity
import com.uldskull.rolegameassistant.viewmodels.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_picture.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   Class "PictureFragment" :
 *   Handle character's picture
 **/
class PictureFragment(val context: Activity) : Fragment() {

    /** View model for new character    **/
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

    /** Select image from the gallery   **/
    private fun selectImageAlbum() {
        if (isActivityNull()) return

        val galleryIntent = getPickImageIntent()
        startActivityForResult(galleryIntent, 0)

    }

    /** Get a pick image intent **/
    private fun getPickImageIntent(): Intent {
        return Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
    }

    /** Called when the started activity is finished    **/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        Toast.makeText(activity, "On activity result", Toast.LENGTH_SHORT)

        when (requestCode) {
            0 -> {
                if (isResultOk(resultCode) && data != null) {
                    var selectedImage: Uri? = data.data

                    img_btn_characterPicture.setImageURI(selectedImage)
                }
            }
            else -> {
                showBadRequestToast()
            }

        }
    }

    /** Show bad request toast  **/
    private fun showBadRequestToast() {
        Toast.makeText(activity, "Bad request code", Toast.LENGTH_SHORT)
    }

    /** Is result code ok   **/
    private fun isResultOk(resultCode: Int) = resultCode == Activity.RESULT_OK

    /** Is activity null    **/
    private fun isActivityNull(): Boolean {
        if (activity == null) {
            return true
        }
        return false
    }


    /** Initialize the view corresponding to this fragment class    **/
    private fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_picture, container, false
        )
        return initialRootView
    }

    /** Activity life-cycle **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var imageButton = activity?.findViewById<ImageButton>(R.id.img_btn_characterPicture)

        setImageButtonListener(imageButton)
    }

    /** Set image button listener       **/
    private fun setImageButtonListener(imageButton: ImageButton?) {
        imageButton?.setOnClickListener {
            selectImageAlbum()
        }
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