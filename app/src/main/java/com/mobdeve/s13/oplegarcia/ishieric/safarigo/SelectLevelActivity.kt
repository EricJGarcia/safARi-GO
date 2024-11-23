package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.util.Log


class SelectLevelActivity : AppCompatActivity() {

    private lateinit var theme: String // This will track which theme is selected.
    private lateinit var resetbutton: Button
    private lateinit var dbHelper: GameDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_level)

        dbHelper = GameDatabaseHelper(this)

        // Button references for 10 levels
        val level1Btn: Button = findViewById(R.id.level1_btn)
        val level2Btn: Button = findViewById(R.id.level2_btn)
        val level3Btn: Button = findViewById(R.id.level3_btn)
        val level4Btn: Button = findViewById(R.id.level4_btn)
        val level5Btn: Button = findViewById(R.id.level5_btn)
        val level6Btn: Button = findViewById(R.id.level6_btn)
        val level7Btn: Button = findViewById(R.id.level7_btn)
        val level8Btn: Button = findViewById(R.id.level8_btn)
        val level9Btn: Button = findViewById(R.id.level9_btn)
        val level10Btn: Button = findViewById(R.id.level10_btn)

        // Initialize the reset button
        resetbutton = findViewById(R.id.reset_btn)

        // Retrieve the theme that was selected (default to "Animals")
        theme = intent.getStringExtra("SELECTED_THEME") ?: "Animals"

        // Update UI based on the selected theme (Only Animals theme available)
        updateUIForSelectedTheme(level1Btn, level2Btn, level3Btn, level4Btn, level5Btn,
            level6Btn, level7Btn, level8Btn, level9Btn, level10Btn)

        // Handle button clicks for Level 1 to Level 10
        level1Btn.setOnClickListener { navigateToLevelActivity(Level1Activity::class.java) }
        level2Btn.setOnClickListener { navigateToLevelActivity(Level2Activity::class.java) }
        level3Btn.setOnClickListener { navigateToLevelActivity(Level3Activity::class.java) }
        level4Btn.setOnClickListener { navigateToLevelActivity(Level4Activity::class.java) }
        level5Btn.setOnClickListener { navigateToLevelActivity(Level5Activity::class.java) }
        level6Btn.setOnClickListener { navigateToLevelActivity(Level6Activity::class.java) }
        level7Btn.setOnClickListener { navigateToLevelActivity(Level7Activity::class.java) }
        level8Btn.setOnClickListener { navigateToLevelActivity(Level8Activity::class.java) }
        level9Btn.setOnClickListener { navigateToLevelActivity(Level9Activity::class.java) }
        level10Btn.setOnClickListener { navigateToLevelActivity(Level10Activity::class.java) }

        // Handle the Reset Button click
        resetbutton.setOnClickListener {
            resetGameData()
        }
    }

    private fun updateUIForSelectedTheme(vararg buttons: Button) {
        if (theme == "Animals") {
            // Check completion status for each level
            buttons.forEachIndexed { index, button ->
                val levelNumber = index + 1
                val isCompleted = dbHelper.isLevelCompleted("Level $levelNumber")
                if (isCompleted) {
                    button.isEnabled = false
                    button.alpha = 0.5f
                } else {
                    button.isEnabled = true
                    button.alpha = 1.0f
                }
            }
        }
    }

    private fun disableLevels(vararg buttons: Button) {
        buttons.forEach { button ->
            button.isEnabled = false
            button.alpha = 0.5f
        }
    }

    private fun enableLevels(vararg buttons: Button) {
        buttons.forEach { button ->
            button.isEnabled = true
            button.alpha = 1f
        }
    }

    private fun navigateToLevelActivity(levelActivity: Class<*>) {
        val intent = Intent(this, levelActivity)
        startActivity(intent)
        finish()
    }

    // Function to reset game data
    private fun resetGameData() {
        dbHelper.resetScores()

        // Re-enable all level buttons
        val buttons = arrayOf(
            findViewById<Button>(R.id.level1_btn),
            findViewById<Button>(R.id.level2_btn),
            findViewById<Button>(R.id.level3_btn),
            findViewById<Button>(R.id.level4_btn),
            findViewById<Button>(R.id.level5_btn),
            findViewById<Button>(R.id.level6_btn),
            findViewById<Button>(R.id.level7_btn),
            findViewById<Button>(R.id.level8_btn),
            findViewById<Button>(R.id.level9_btn),
            findViewById<Button>(R.id.level10_btn)
        )

        buttons.forEach { button ->
            button.isEnabled = true
            button.alpha = 1.0f
        }

        Toast.makeText(this, "Game progress has been reset!", Toast.LENGTH_SHORT).show()
    }


}

