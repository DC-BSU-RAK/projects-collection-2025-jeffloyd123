package com.example.uaeapp

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerA: Spinner
    private lateinit var spinnerB: Spinner
    private lateinit var btnGenerate: Button
    private lateinit var btnPlay: Button
    private lateinit var btnInstructions: Button
    private var mediaPlayer: MediaPlayer? = null

    private val genres = listOf("Lo-fi", "Reggaeton", "Jazz", "EDM", "Hip-Hop")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        spinnerA = findViewById(R.id.spinnerGenreA)
        spinnerB = findViewById(R.id.spinnerGenreB)
        btnGenerate = findViewById(R.id.btnGenerate)
        btnPlay = findViewById(R.id.btnPlay)
        btnInstructions = findViewById(R.id.btnInstructions)

        // Set spinner options
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genres)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerA.adapter = adapter
        spinnerB.adapter = adapter

        // Show instructions
        btnInstructions.setOnClickListener {
            showInstructionsPopup()
        }

        // Generate beat
        btnGenerate.setOnClickListener {
            val genre1 = spinnerA.selectedItem.toString()
            val genre2 = spinnerB.selectedItem.toString()
            Toast.makeText(this, "Generating beat from $genre1 + $genre2...", Toast.LENGTH_SHORT).show()
            btnPlay.isEnabled = true
        }

        // Play beat
        btnPlay.setOnClickListener {
            Toast.makeText(this, "Playing mixed beat 🎵", Toast.LENGTH_SHORT).show()

//             Optionally play sound
             mediaPlayer = MediaPlayer.create(this, R.raw.brain_implant_cyberpunk)
             mediaPlayer?.start()
        }

        // Optional: show popup on launch
        // showInstructionsPopup()
    }

    private fun showInstructionsPopup() {
        val instructions = """
            🎵 How to Use BeatBlend 🎵
            
            1. Select two genres using the dropdowns.
            2. Tap "Generate Beat" to create a fusion.
            3. Tap "Play Beat" to listen to the result.
            4. Enjoy the music!
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Instructions")
            .setMessage(instructions)
            .setPositiveButton("Got it!") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}
