package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat

class Level1Activity : AppCompatActivity() {

    private lateinit var targetImageView: ImageView
    private lateinit var exitButton: ImageView
    private lateinit var pointsTextView: TextView
    private lateinit var dbHelper: GameDatabaseHelper
    private var targetItem = "Tiger" // Initial target for this level
    private var points = 0

    // List of animals for each level, cycling through them
    private val animals = listOf("Tiger", "Cheetah", "Fox")
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_g1level1)

        // Initialize the views
        targetImageView = findViewById(R.id.target_image_view)
        pointsTextView = findViewById(R.id.points_text_view)

        // Initialize database and load points
        dbHelper = GameDatabaseHelper(this)
        loadPointsFromPreferences() // Load points from SharedPreferences

        // Initialize exit button
        exitButton = findViewById(R.id.exit_button)

        // Set click listener for exit button
        exitButton.setOnClickListener {
            showExitConfirmationDialog()
        }

        // Set initial points display
        updatePointsDisplay()

        // Set the initial target image
        setNewTarget()

        // Request camera permissions
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                startCamera()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        // Set a click listener on the image to show input dialog
        targetImageView.setOnClickListener {
            targetImageView.isEnabled = false // Disable image click while awaiting guess
            showGuessDialog()
        }

    }

    // Allow exiting of level
    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Exit Level")
            .setMessage("Are you sure you want to exit this level?")
            .setPositiveButton("Yes") { dialog, _ ->
                // Navigate back to SelectLevelActivity
                val intent = Intent(this, SelectLevelActivity::class.java)
                startActivity(intent)
                finish()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    // Prompt the user to guess
    private fun showGuessDialog() {
        val builder = AlertDialog.Builder(this)
        val input = EditText(this)

        builder.setTitle("What animal is this?")
        builder.setView(input)

        builder.setPositiveButton("Submit") { _, _ ->
            val guess = input.text.toString().trim()
            processGuess(guess)
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
            targetImageView.isEnabled = true // Re-enable image click if canceled
        }

        builder.show()
    }

    // Open camera
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = androidx.camera.core.Preview.Builder().build().also {
                it.setSurfaceProvider(findViewById<androidx.camera.view.PreviewView>(R.id.preview_view).surfaceProvider)
            }
            val cameraSelector = androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
            } catch (exc: Exception) {
                Toast.makeText(this, "Failed to open camera", Toast.LENGTH_SHORT).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    // Function to process the user's guesses and providing hints
    private fun processGuess(guess: String) {
        if (guess.equals(targetItem, ignoreCase = true)) {
            // Save score to the database and preferences
            saveScore("Level 1", points)
            savePointsToPreferences() // Save points to SharedPreferences
            Toast.makeText(this, "Correct! Next animal!", Toast.LENGTH_SHORT).show()

            // Add points for correct guess and move to the next target
            incrementPoints()
            setNewTarget()
            targetImageView.isEnabled = true
        } else {
            // Show a specific hint based on the current target animal
            val hint = when (targetItem) {
                "Tiger" -> "Hint: I am the largest species in the cat family. T _ G _ R"
                "Cheetah" -> "Hint: I am the fastest land animal. C H _ _ T _ H"
                "Fox" -> "Hint: I am known for my cunning and reddish fur. F _ _"
                else -> "Hint: Try again!"
            }
            Toast.makeText(this, hint, Toast.LENGTH_SHORT).show()
            targetImageView.isEnabled = true
        }
    }

    // Function to save the score of the user
    @SuppressLint("Range")
    private fun saveScore(level: String, points: Int) {
        val db = dbHelper.writableDatabase

        // Check if a record for this level already exists
        val cursor = db.query(
            GameDatabaseHelper.TABLE_SCORES,
            arrayOf(GameDatabaseHelper.COLUMN_ID),
            "${GameDatabaseHelper.COLUMN_LEVEL} = ?",
            arrayOf(level),
            null, null, null
        )

        if (cursor != null && cursor.moveToFirst()) {
            // If record exists, update it
            val id = cursor.getLong(cursor.getColumnIndex(GameDatabaseHelper.COLUMN_ID))
            val values = ContentValues().apply {
                put(GameDatabaseHelper.COLUMN_POINTS, points)
            }
            db.update(GameDatabaseHelper.TABLE_SCORES, values, "${GameDatabaseHelper.COLUMN_ID} = ?", arrayOf(id.toString()))
        } else {
            // If record does not exist, insert it
            val values = ContentValues().apply {
                put(GameDatabaseHelper.COLUMN_LEVEL, level)
                put(GameDatabaseHelper.COLUMN_POINTS, points)
            }
            db.insert(GameDatabaseHelper.TABLE_SCORES, null, values)
        }

        cursor?.close()
        db.close()
    }


    private fun setNewTarget() {
        if (currentIndex >= animals.size) {
            currentIndex = 0
            dbHelper.setLevelCompleted("Level 1")
            Toast.makeText(this, "Level Completed!, Proceed to Level 2 Yipee!!!", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, SelectLevelActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000) // 1.5-second delay
        }

        targetItem = animals[currentIndex]
        targetImageView.setImageResource(when (targetItem) {
            "Tiger" -> R.drawable.tiger_image
            "Cheetah" -> R.drawable.cheetah_image
            "Fox" -> R.drawable.fox_image
            else -> R.drawable.safarii
        })

        // Move to the next animal
        currentIndex++
    }

    // All funcions below work to add the points, save the current number of points,
    // and display it properly in each level.
    private fun incrementPoints() {
        points++ // Increment points
        updatePointsDisplay()
        savePointsToPreferences()
    }

    private fun updatePointsDisplay() {
        pointsTextView.text = "Points: $points"
    }

    private fun savePointsToPreferences() {
        val sharedPref = getSharedPreferences("game_data", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("total_points", points)
        editor.apply()
    }

    private fun loadPointsFromPreferences() {
        val sharedPref = getSharedPreferences("game_data", MODE_PRIVATE)
        points = sharedPref.getInt("total_points", 0)
        updatePointsDisplay()
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}
