package com.example.myapp.views

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.myapp.BuildConfig
import com.example.myapp.R
import com.example.myapp.utils.AppConstant
import java.io.File
import java.util.*


class AddNotesActivity : AppCompatActivity() {
    lateinit var EditTextTitle: EditText
    lateinit var EditTextDescription: TextView
    lateinit var submitButton: Button
    lateinit var imageviewEditor: ImageView

    val REQUEST_CODE_GALLERY = 2
    val REQUEST_CODE_CAMERA = 1
    val MY_PERMISSION_CODE = 124
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
                val intent = Intent()
                intent.putExtra(AppConstant.TITLE, EditTextTitle.text.toString())
                intent.putExtra(AppConstant.DESCRIPTION, EditTextDescription.text.toString())
                intent.putExtra(AppConstant.imagePath,picturePath)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

        })
        imageviewEditor.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (checkAndRequestPermission()) {
                    setupDialog()
                }
            }

        })
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
                    setupDialog()
                }
            }
        }
    }

    private fun setupDialog() {
        val view = LayoutInflater.from(this@AddNotesActivity).inflate(R.layout.dialog_selector, null)
        val textViewCamera = view.findViewById<TextView>(R.id.textViewCamera)
        val textViewGallery = view.findViewById<TextView>(R.id.textViewGallery)
        val dialog = AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(true)
                .create()


        textViewCamera.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (takePictureIntent.resolveActivity(packageManager) != null) {
                    var photoFile: File? = null

                    try {
                        photoFile = createImageFile()
                    } catch (e: Exception) {

                    }
//                    val resultActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//                        val bitmap = it?.data?.extras?.get("data") as Bitmap
//                        imageviewEditor.setImageBitmap(bitmap)
//                        picturePath=imageLocation?.path.toString()
//                        Glide.with(this@AddNotesActivity).load(imageLocation.absoluteFile).into(imageviewEditor)
//                    }

                    if (photoFile != null) {
                        val photoUri = FileProvider.getUriForFile(this@AddNotesActivity,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile)
                        imageLocation = photoFile
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                        dialog.hide()
                        startActivityForResult(takePictureIntent, REQUEST_CODE_CAMERA)
                        //resultActivity.launch(intent)
                    }
                }
            }
        })

//        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            val bitmap = it?.data?.extras?.get("data") as Bitmap
//            imageviewEditor.setImageBitmap(bitmap)
//            var selectImage = intent?.data
//            picturePath=selectImage.toString()
//            Glide.with(this).load(selectImage?.path).into(imageviewEditor)
//        }

        textViewGallery.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, REQUEST_CODE_GALLERY)

//                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//                startForResult.launch(intent)
                dialog.hide()
            }
        })
        dialog.show()
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
}