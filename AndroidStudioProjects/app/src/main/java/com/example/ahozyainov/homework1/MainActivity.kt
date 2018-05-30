package com.example.ahozyainov.homework1

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

    private val CITY = "city"
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


        settings = getSharedPreferences(MYSETTINGS, Context.MODE_PRIVATE)
        spinner = findViewById(R.id.cities_spinner)
        button = findViewById(R.id.check_button)
        textView = findViewById(R.id.text_view_main)
        intent = Intent(this, WeatherActivity::class.java)

        checkSettings()

        button.setOnClickListener({
            checkWeather()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == sendRequestCode) {
            if (resultCode == Activity.RESULT_OK) {

                textView.text = data!!.getStringExtra(CITY)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        city = spinner.selectedItem.toString()
        settings.edit().putString(cityKey, city).apply()

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
