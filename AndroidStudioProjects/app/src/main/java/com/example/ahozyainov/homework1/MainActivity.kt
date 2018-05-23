package com.example.ahozyainov.homework1

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var city: String? = null
    private val CITY_NAME: String = "City"
    private lateinit var settings: SharedPreferences
    private lateinit var spinner: Spinner
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private lateinit var weatherString: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settings = getSharedPreferences("mySettings", Context.MODE_PRIVATE)
        spinner = findViewById(R.id.cities_spinner)
        button = findViewById(R.id.check_button)
        textView = findViewById(R.id.text_view)
        imageView = findViewById(R.id.image)
        weatherString = resources.getStringArray(R.array.weather)

        checkSettings()

        button.setOnClickListener({
            checkWeather()
        })

    }

    override fun onPause() {
        super.onPause()
        city = spinner.selectedItem.toString()
        settings.edit().putString(CITY_NAME, city).apply()

    }

    private fun checkWeather() {
        var position: Int = spinner.selectedItemPosition

        if (position == 0 || position == 3) {
            imageView.setImageResource(R.drawable.sunny)
            textView.text = weatherString[1]
            textView.setBackgroundResource(R.color.colorBackground)

        } else if (position == 1 || position == 2) {
            imageView.setImageResource(R.drawable.rainy)
            textView.text = weatherString[0]
            textView.setBackgroundResource(R.color.colorBackground)
        }

    }

    private fun checkSettings() {
        if (settings.contains(CITY_NAME)) {
            var checkCity: String = settings.getString(CITY_NAME, "")
            var cityArray: Array<String> = resources.getStringArray(R.array.cities)
            var index = 0
            for (item in cityArray) {
                if (checkCity.equals(item)) {
                    index = cityArray.indexOf(item)
                }
            }
            spinner.setSelection(index)
        }
    }

}
