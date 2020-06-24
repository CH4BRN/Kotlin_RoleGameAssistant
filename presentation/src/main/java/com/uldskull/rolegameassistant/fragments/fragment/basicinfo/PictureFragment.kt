// File PictureFragment.kt
// @Author pierre.antoine - 28/01/2020 - No copyright.

package com.uldskull.rolegameassistant.fragments.fragment.basicinfo

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.uldskull.rolegameassistant.R
import com.uldskull.rolegameassistant.fragments.core.CustomCompanion
import com.uldskull.rolegameassistant.fragments.core.CustomFragment
import com.uldskull.rolegameassistant.fragments.fragment.KEY_POSITION
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_CAMERA
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_GALLERY
import com.uldskull.rolegameassistant.fragments.fragment.REQUEST_CODE_SELECT_IMAGE_IN_ALBUM
import com.uldskull.rolegameassistant.fragments.viewPager.adapter.PICTURE_FRAGMENT_POSITION
import com.uldskull.rolegameassistant.viewmodels.character.CharactersPictureViewModel
import com.uldskull.rolegameassistant.viewmodels.character.NewCharacterViewModel
import kotlinx.android.synthetic.main.fragment_picture.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *   Class "PictureFragment" :
 *   Handle character's picture
 **/
class PictureFragment : CustomFragment() {


    /** View model for new character    **/
    private val newCharacterViewModel: NewCharacterViewModel by sharedViewModel()

    /**  View model for picture **/
    private val charactersPictureViewModel: CharactersPictureViewModel by sharedViewModel()

    /** Fragment Lifecycle  **/
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity = requireActivity()
        Log.d("DEBUG $TAG", "Activity is null ? ${activity == null}")
        Log.d(TAG, "onCreateView")
        return initializeView(inflater, container)
    }

    /** Select image from the gallery   **/
    private fun selectImageAlbum() {
        Log.d(TAG, "selectImageAlbum")
        val galleryIntent = getPickImageIntent()
        startActivityForResult(
            galleryIntent,
            REQUEST_CODE_SELECT_IMAGE_IN_ALBUM
        )
    }

    /**
     * Select picture
     */
    private fun selectPicture() {
        Log.d(TAG, "selectPicture")
        if (context != null) {
            val pictureDialog = AlertDialog.Builder(context!!)

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

    }

    /**
     * Take photo from camera
     */
    private fun takePhotoFromCamera() {
        Log.d(TAG, "takePhotoFromCamera")
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(
            intent,
            REQUEST_CODE_CAMERA
        )
    }

    /**
     * choose photo from gallery
     */
    private fun choosePhotoFromGallery() {
        Log.d(TAG, "choosePhotoFromGallery")
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            EXTERNAL_CONTENT_URI
        )

        startActivityForResult(
            galleryIntent,
            REQUEST_CODE_GALLERY
        )
    }

    /** Get a pick image intent **/
    private fun getPickImageIntent(): Intent {
        Log.d(TAG, "getPickImageIntent")
        return Intent(
            Intent.ACTION_PICK,
            EXTERNAL_CONTENT_URI
        )
    }

    /** Called when the started activity is finished    **/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        Log.d(TAG, "onActivityResult")

        when (requestCode) {
            REQUEST_CODE_SELECT_IMAGE_IN_ALBUM -> {
                if (isResultOk(resultCode) && data != null) {
                    val selectedImage: Uri? = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    if (selectedImage != null) {
                        var cursor = activity?.contentResolver?.query(
                            selectedImage,
                            filePathColumn,
                            null,
                            null,
                            null,
                            null
                        )
                        cursor?.moveToFirst()
                        var columnIndex = cursor?.getColumnIndex(filePathColumn[0])
                        if (columnIndex != null) {
                            var picturePath = cursor?.getString(columnIndex)
                            Log.d("DEBUG$TAG", "Picture path :$picturePath")
                            charactersPictureViewModel.picturePath.value = picturePath
                            //img_btn_characterPicture.setImageURI(selectedImage)
                            newCharacterViewModel.characterPictureUri = picturePath
                        }
                    }
                }
            }
            REQUEST_CODE_GALLERY -> {
                Toast.makeText(context, "Gallery", Toast.LENGTH_SHORT).show()
                if (isResultOk(resultCode) && data != null) {
                    val selectedImage: Uri? = data.data
                    if (selectedImage != null) {
                            charactersPictureViewModel.picturePath.value = selectedImage.toString()
                            newCharacterViewModel.characterPictureUri = selectedImage.toString()
                    }
                }
            }
            REQUEST_CODE_CAMERA -> {
                Toast.makeText(context, "Camera", Toast.LENGTH_SHORT).show()
                if (isResultOk(resultCode) && data != null) {
                    val selectedImage: Uri? = data.data
                    charactersPictureViewModel.picturePath.value = selectedImage.toString()
                    //img_btn_characterPicture.setImageURI(selectedImage)
                }
            }
            else -> {
                showBadRequestToast()
            }

        }
    }

    /** Show bad request toast  **/
    private fun showBadRequestToast() {
        Log.d(TAG, "showBadRequestToast")
        Toast.makeText(activity, "Bad request code", Toast.LENGTH_SHORT).show()
    }

    /** Is result code ok   **/
    private fun isResultOk(resultCode: Int): Boolean {
        Log.d(TAG, "isResultOk")
        return resultCode == Activity.RESULT_OK
    }


    /** Initialize the view corresponding to this fragment class    **/
    override fun initializeView(layoutInflater: LayoutInflater, container: ViewGroup?): View? {
        Log.d(TAG, "initializeView")
        initialRootView = layoutInflater.inflate(
            R.layout.fragment_picture, container, false
        )
        return initialRootView
    }

    /** Activity life-cycle **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        val imageButton = activity?.findViewById<ImageButton>(R.id.img_btn_characterPicture)

        observePictureUri()

        setImageButtonListener(imageButton)

        Log.d("DEBUG $TAG", "activity is null ? ${activity == null}")
        Log.d("DEBUG $TAG", "$activity")
    }

    /**
     * Observe picture URI
     */
    private fun observePictureUri() {
        charactersPictureViewModel?.picturePath?.observe(this, Observer {
            var uri = Uri.parse(it)
            loadImage(uri)
        })
    }

    /**
     * Load a picture in image button with Picasso
     */
    private fun loadImage(uri: Uri?) {
        Log.d("DEBUG$TAG", "Uri : ${uri.toString()}")
        Picasso.get()
            .load(uri)
            .into(img_btn_characterPicture)
    }

    /** Set image button listener       **/
    private fun setImageButtonListener(imageButton: ImageButton?) {
        Log.d(TAG, "setImageButtonListener")
        imageButton?.setOnClickListener {
            selectPicture()
        }
    }

    /**
     * Activity lifecycle
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("DEBUG", "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        activity = getActivity()
        Log.d("DEBUG", "activity is null ? ${activity == null}")
    }

    companion object : CustomCompanion() {
        private const val TAG = "PictureFragment"

        @JvmStatic
        override fun newInstance(activity: Activity): PictureFragment {
            Log.d(TAG, "newInstance")
            val fragment =
                PictureFragment(

                )
            fragment.activity = activity
            val args = Bundle()

            args.putInt(KEY_POSITION, PICTURE_FRAGMENT_POSITION)
            fragment.arguments = args

            return fragment
        }
    }
}