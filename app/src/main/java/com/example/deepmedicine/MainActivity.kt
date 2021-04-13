package com.example.deepmedicine

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private val mInputSize = 224
    private lateinit var mModelPath: String
    private val mLabelPath = "label.txt"
    private lateinit var classifier: Classifier
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ArrayAdapter.createFromResource(this, R.array.algorithms, android.R.layout.simple_spinner_item).
        also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        button_choose_image?.setOnClickListener { openFileChooser() }
        classify_button?.setOnClickListener { classify() }
    }

    private fun classify() {
        val modelName: String = spinner.selectedItem.toString()
        when (modelName){
            "VGG19" -> mModelPath = "converted_model_vgg19.tflite"
            "ResNet50" -> mModelPath = "converted_model_resnet50.tflite"
        }
        classifier = Classifier(assets, mModelPath, mLabelPath, mInputSize)

        if (image_view.drawable != null) {
            val bitmap = (image_view.drawable as BitmapDrawable).bitmap
            val result = classifier.recognizeImage(bitmap)
            val intent = Intent(this@MainActivity, Result::class.java)
            intent.putExtra("label1", result[0].title)
            intent.putExtra("probability1", result[0].confidence)
            intent.putExtra("label2", result[1].title)
            intent.putExtra("probability2", result[1].confidence)
            intent.putExtra("imageUri", imageUri)
            intent.putExtra("modelName", modelName)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Select an image!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openFileChooser() {
        val intent = Intent()
        intent.type ="image/*"
        intent.action = Intent.ACTION_OPEN_DOCUMENT
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            Picasso.get().load(imageUri).into(image_view)
        }
    }
}