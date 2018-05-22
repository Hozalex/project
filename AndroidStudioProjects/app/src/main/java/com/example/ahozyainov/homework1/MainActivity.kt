package com.example.ahozyainov.homework1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var spinner: Spinner = findViewById(R.id.cities_spinner)
        var button: Button = findViewById(R.id.check_button)
        var textView: TextView = findViewById(R.id.text_view)
        var imageView: ImageView = findViewById(R.id.image)
        var weatherString: Array<String> = resources.getStringArray(R.array.weather)

        button.setOnClickListener(View.OnClickListener {
            var position: Int = spinner.selectedItemPosition
            if (position == 0 || position == 3) {
                imageView.setImageResource(R.drawable.sunny)
                textView.text = weatherString[1]

            } else if (position == 1 || position == 2) {
                imageView.setImageResource(R.drawable.rainy)
                textView.text = weatherString[0]
            }


        })

    }
}
