package com.example.ahozyainov.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val sharedTextKey = "sharedText"
    private var sharedText = "default"
    private val MYSETTINGS = "mySettings"
    private var city: String = "default"
    private val cityKey = "city"
    private val sendRequestCode = 1
    private val idSpinner = "idSpinner"
    private lateinit var settings: SharedPreferences
    private lateinit var spinner: Spinner
    private lateinit var button: Button
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("MainActivity onCreate")

        settings = getSharedPreferences(MYSETTINGS, Context.MODE_PRIVATE)
        spinner = findViewById(R.id.cities_spinner)
        button = findViewById(R.id.check_button)
        textView = findViewById(R.id.text_view_main)
        intent = Intent(this, WeatherActivity::class.java)

        if (savedInstanceState != null) {
            sharedText = savedInstanceState.getString(sharedTextKey)
            textView.text = sharedText
        }
        checkSettings()

        button.setOnClickListener({
            checkWeather()
        })
    }

    override fun onStart() {
        super.onStart()
        println("MainActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        println("MainActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        println("MainActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        println("MainActivity onStop")
        city = spinner.selectedItem.toString()
        settings.edit().putString(cityKey, city).apply()

    }

    override fun onDestroy() {
        super.onDestroy()
        println("MainActivity onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(sharedTextKey, sharedText)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == sendRequestCode) {
            if (resultCode == Activity.RESULT_OK) {
                sharedText = data!!.getStringExtra(sharedTextKey)
                textView.text = sharedText
            }
        }
    }

    private fun checkWeather() {
        var position: Int = spinner.selectedItemPosition
        city = spinner.selectedItem.toString()
        intent.putExtra(idSpinner, position)
        intent.putExtra(cityKey, city)
        startActivityForResult(intent, sendRequestCode)
    }

    private fun checkSettings() {
        if (settings.contains(cityKey)) {
            var checkCity: String = settings.getString(cityKey, "")
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
