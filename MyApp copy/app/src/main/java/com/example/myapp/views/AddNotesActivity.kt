package com.example.myapp.views

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.icu.text.CaseMap
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
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
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

        })
        imageviewEditor.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                setupDialog()
            }

        })
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

                    if (photoFile != null) {
                        val photoUri = FileProvider.getUriForFile(this@AddNotesActivity,
                                BulidConfig.APPLICATION_ID + ".provider",
                                photoFile)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                        startActivityForResult(takePictureIntent, REQUEST_CODE_CAMERA)
                    }
                }
            }
        })

        textViewGallery.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, REQUEST_CODE_GALLERY)
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
}