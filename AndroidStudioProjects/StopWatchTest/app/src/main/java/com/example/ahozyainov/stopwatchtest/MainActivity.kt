package com.example.ahozyainov.stopwatchtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import android.view.View
import android.widget.TextView
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val RUNNING = "running"
    private val SECONDS = "seconds"
    private lateinit var tvCounter: TextView
    private var isRunning = false
    private var seconds = 0L
    private val handler: Handler = Handler()
    private val delay = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCounter = findViewById(R.id.textView)

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getLong(SECONDS)
            isRunning = savedInstanceState.getBoolean(RUNNING)
        }

        findViewById<View>(R.id.button_reset).setOnClickListener({
            seconds = 0
            updateCounter()
        })
        findViewById<View>(R.id.button_pause).setOnClickListener({
            isRunning = false
            handler.removeCallbacksAndMessages(null)
        })
        findViewById<View>(R.id.button_start).setOnClickListener({
            if (!isRunning) {
                runTimer()
                isRunning = true
            }

        })

    }

    override fun onSaveInstanceState(outState: Bundle?) {

        outState?.putBoolean(RUNNING, isRunning)
        outState?.putLong(SECONDS, seconds)
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacksAndMessages(null)
    }

    override fun onResume() {
        super.onResume()
        if (isRunning) runTimer()

    }

    private fun runTimer() {
        handler.post({

            updateCounter()
            ++seconds
            handler.postDelayed({}, delay)
        })
    }

    private fun updateCounter() {
        var hours = TimeUnit.SECONDS.toHours(seconds)
        var minutes = TimeUnit.SECONDS.toMinutes(seconds - TimeUnit.HOURS.toSeconds(hours))
        var secs = seconds % 60
        var time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs)
        tvCounter.text = time
    }
}
