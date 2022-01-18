package com.example.myapp.addnotes

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.myapp.BuildConfig
import com.example.myapp.R
import com.example.myapp.addnotes.bottomsheet.FileSelectorFragment
import com.example.myapp.utils.AppConstant
import java.io.File
import java.io.IOException
import java.util.*


class AddNotesActivity : AppCompatActivity(),onOptionClickListener {
    companion object {
        private const val REQUEST_CODE_GALLERY = 2
        private const val REQUEST_CODE_CAMERA = 1
        private const val MY_PERMISSION_CODE = 124
    }

    lateinit var EditTextTitle: EditText
    lateinit var EditTextDescription: TextView
    lateinit var submitButton: Button
    lateinit var imageviewEditor: ImageView
    var picturePath = ""
    lateinit var imageLocation: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        bindViews()
        clickListener()
    }

    private fun clickListener() {
        submitButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (EditTextTitle.text.toString().isNotEmpty() && EditTextDescription.text.toString().isNotEmpty()) {
                    val intent = Intent()
                    intent.putExtra(AppConstant.TITLE, EditTextTitle.text.toString())
                    intent.putExtra(AppConstant.DESCRIPTION, EditTextDescription.text.toString())
                    intent.putExtra(AppConstant.imagePath, picturePath)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }else{
                    Toast.makeText(this@AddNotesActivity,getString(R.string.empty_string),Toast.LENGTH_SHORT).show()
                }
            }

        })
        imageviewEditor.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (checkAndRequestPermission()) {
                    openPicker()
                }
            }

        })
    }

    private fun openPicker() {
        val dialog=FileSelectorFragment.newInstance()
        dialog.show(supportFragmentManager,FileSelectorFragment.TAG)
    }

    private fun checkAndRequestPermission(): Boolean {
        val permissionCamera = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        val storagePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        val listPermissionNeeded = ArrayList<String>()
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            listPermissionNeeded.add(android.Manifest.permission.CAMERA)
        }

        if (listPermissionNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionNeeded.toTypedArray<String>(), MY_PERMISSION_CODE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openPicker()
                }
            }
        }
    }



    private fun createImageFile(): File? {
        val timeStamp = java.text.SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val fileName = "JPEG" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDir)
    }

    private fun bindViews() {
        EditTextTitle = findViewById(R.id.EditTextTitle)
        EditTextDescription = findViewById(R.id.EditTextDescription)
        submitButton = findViewById(R.id.SubmitButton)
        imageviewEditor = findViewById(R.id.imageviewEditor)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_CAMERA -> {
                    picturePath = imageLocation.path.toString()
                    Glide.with(this@AddNotesActivity).load(imageLocation.absoluteFile).into(imageviewEditor)
                }
                REQUEST_CODE_GALLERY -> {
                    val selectImage = data?.data
                    picturePath = selectImage?.path.toString()
                    Glide.with(this).load(selectImage?.path).into(imageviewEditor)
                }

            }
        }
    }

    override fun onCameraClick() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null

            try {
                photoFile = createImageFile()
            } catch (e: IOException) {

            }

            if (photoFile != null) {
                val photoUri = FileProvider.getUriForFile(this@AddNotesActivity,
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile)
                imageLocation = photoFile
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(takePictureIntent, REQUEST_CODE_CAMERA)
                //resultActivity.launch(intent)
            }
        }
    }


    override fun onGalleryClick() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE_GALLERY)
    }
}
interface onOptionClickListener{
    fun onCameraClick()
    fun onGalleryClick()
}