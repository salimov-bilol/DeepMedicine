package com.example.deepmedicine

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_result.*

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val imageUri = intent.getParcelableExtra<Uri>("imageUri")
        val label_1 = intent.getStringExtra("label1")
        val label_2 = intent.getStringExtra("label2")
        val probability_1 = intent.getFloatExtra("probability1", 0f)
        val probability_2 = intent.getFloatExtra("probability2", 0f)
        val modelNameText = intent.getStringExtra("modelName")

        Picasso.get().load(imageUri).into(imageView)

        label1.text = label_1
        label2.text = label_2
        modelName.text = modelNameText
        probability1.text = probability_1.toString()
        probability2.text = probability_2.toString()
    }
}