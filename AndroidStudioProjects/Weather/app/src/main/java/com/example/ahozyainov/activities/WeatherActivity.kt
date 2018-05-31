package com.example.ahozyainov.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class WeatherActivity : AppCompatActivity() {

    private val sharedTextKey = "sharedText"
    private lateinit var lastShare: String
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
        println("WeatherActivity onCreate")
        textViewWeather = findViewById(R.id.text_view_weather)
        textViewCity = findViewById(R.id.text_view_city)
        imageView = findViewById(R.id.image_weather_activity)
        button = findViewById(R.id.share_button)
        weatherString = resources.getStringArray(R.array.weather)
        lastShare = resources.getString(R.string.last_share_weather)

        var getIntent: Intent = intent
        if (getIntent != null) {
            showWeather(getIntent.getIntExtra(idSpinner, 5), getIntent.getStringExtra(cityKey))
        }

        button.setOnClickListener({
            shareWeather()

        })
    }

    override fun onStart() {
        super.onStart()
        println("WeatherActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        println("WeatherActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        println("WeatherActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        println("WeatherActivity onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        println("WeatherActivity onDestroy")
    }

    private fun shareWeather() {
        var shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, textViewCity.text.toString() + ": " +
                textViewWeather.text.toString())
        shareIntent.type = "text/plain"

        if (shareIntent.resolveActivity(packageManager) != null) {
            startActivity(shareIntent)
        }

        var sendIntent: Intent = intent

        sendIntent.putExtra(sharedTextKey, lastShare + " " + textViewCity.text.toString())
        setResult(Activity.RESULT_OK, intent)
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
