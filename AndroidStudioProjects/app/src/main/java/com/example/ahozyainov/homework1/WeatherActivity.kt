package com.example.ahozyainov.homework1

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class WeatherActivity : AppCompatActivity() {

    private lateinit var textViewWeather: TextView
    private lateinit var textViewCity: TextView
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private val idSpinner = "idSpinner"
    private val cityKey = "city"
    private lateinit var weatherString: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        textViewWeather = findViewById(R.id.text_view_weather)
        textViewCity = findViewById(R.id.text_view_city)
        imageView = findViewById(R.id.image_weather_activity)
        button = findViewById(R.id.share_button)
        weatherString = resources.getStringArray(R.array.weather)


        var getIntent: Intent = intent
        if (getIntent != null) {
            showWeather(getIntent.getIntExtra(idSpinner, 5), getIntent.getStringExtra(cityKey))
        }

        button.setOnClickListener({
            shareWeather()

        })
    }

    override fun onBackPressed() {
        var sendIntent: Intent = intent
        sendIntent.putExtra("city", "Последний город: " + textViewCity.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun shareWeather() {
        var shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, textViewCity.text.toString() + ": " +
                textViewWeather.text.toString())
        shareIntent.type = "text/plain"

        if (shareIntent.resolveActivity(packageManager) != null) {
            startActivity(shareIntent)
        }
    }

    private fun showWeather(position: Int, idCity: String) {

        if (position == 0 || position == 3) {

            imageView.setImageResource(R.drawable.sunny)
            textViewCity.text = idCity
            textViewWeather.text = weatherString[1]
            textViewWeather.setBackgroundResource(R.color.colorBackground)

        } else if (position == 1 || position == 2) {
            imageView.setImageResource(R.drawable.rainy)
            textViewCity.text = idCity
            textViewWeather.text = weatherString[0]
            textViewWeather.setBackgroundResource(R.color.colorBackground)
        }


    }


}
