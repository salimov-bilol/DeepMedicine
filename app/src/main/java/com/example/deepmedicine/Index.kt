package com.example.deepmedicine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_index.*

class Index : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        detector_button.setOnClickListener {
            val intent = Intent(this@Index, MainActivity::class.java)
            startActivity(intent)
        }

        metrics_button.setOnClickListener {
            val intent = Intent(this@Index, ModelMetrics::class.java)
            startActivity(intent)
        }

        user_manual_button.setOnClickListener {
            val intent = Intent(this@Index, Manual::class.java)
            startActivity(intent);
        }
    }
}