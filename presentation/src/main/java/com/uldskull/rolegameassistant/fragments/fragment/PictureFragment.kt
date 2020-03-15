// File PictureFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
class PictureFragment(val context: Activity) : CustomFragment(context) {


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
        startActivityForResult(galleryIntent, REQUEST_CODE_SELECT_IMAGE_IN_ALBUM)
    }

    private fun selectPicture() {
        val pictureDialog = AlertDialog.Builder(context)

        pictureDialog.setTitle("Select action : ")
        val pictureDialogItems = arrayOf(
            "Select photo from gallery",
            "Select photo from camera"
        )

        pictureDialog.setItems(
            pictureDialogItems
        ) { _, which ->
            /**
             * This method will be invoked when a button in the dialog is clicked.
             *
             * @param which the button that was clicked (ex.
             * [DialogInterface.BUTTON_POSITIVE]) or the position
             * of the item clicked
             */
            when (which) {
                0 -> choosePhotoFromGallery()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }


    private fun choosePhotoFromGallery() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            EXTERNAL_CONTENT_URI
        )

        startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY)
    }


    /** Get a pick image intent **/
    private fun getPickImageIntent(): Intent {
        return Intent(
            Intent.ACTION_PICK,
            EXTERNAL_CONTENT_URI
        )
    }

    /** Called when the started activity is finished    **/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        Toast.makeText(activity, "On activity result", Toast.LENGTH_SHORT).show()

        when (requestCode) {
            REQUEST_CODE_SELECT_IMAGE_IN_ALBUM -> {
                if (isResultOk(resultCode) && data != null) {
                    val selectedImage: Uri? = data.data
                    img_btn_characterPicture.setImageURI(selectedImage)
                }
            }
            REQUEST_CODE_GALLERY -> {
                Toast.makeText(context, "Gallery", Toast.LENGTH_SHORT).show()
                if (isResultOk(resultCode) && data != null) {
                    val selectedImage: Uri? = data.data
                    img_btn_characterPicture.setImageURI(selectedImage)
                }
            }
            REQUEST_CODE_CAMERA -> {
                Toast.makeText(context, "Camera", Toast.LENGTH_SHORT).show()
                if (isResultOk(resultCode) && data != null) {
                    val selectedImage: Uri? = data.data
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
        Toast.makeText(activity, "Bad request code", Toast.LENGTH_SHORT).show()
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
    override fun initializeView(inflater: LayoutInflater, container: ViewGroup?): View? {
        initialRootView = inflater.inflate(
            R.layout.fragment_picture, container, false
        )
        return initialRootView
    }

    /** Activity life-cycle **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageButton = activity?.findViewById<ImageButton>(R.id.img_btn_characterPicture)

        setImageButtonListener(imageButton)
    }

    /** Set image button listener       **/
    private fun setImageButtonListener(imageButton: ImageButton?) {
        imageButton?.setOnClickListener {
            //  selectImageAlbum()
            selectPicture()
        }
    }

    companion object : CustomCompanion() {

        @JvmStatic
        override fun newInstance(activity: Activity, position: Int): PictureFragment {
            val fragment =
                PictureFragment(activity)
            val args = Bundle()

            args.putInt(KEY_POSITION, position)
            fragment.arguments = args

            return fragment
        }
    }
}