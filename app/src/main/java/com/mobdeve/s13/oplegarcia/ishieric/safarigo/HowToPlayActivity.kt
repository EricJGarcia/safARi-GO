package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity that displays the instructions on how to play the game.
 * This activity is intended to provide a guide for users unfamiliar with the game mechanics.
 */
class HowToPlayActivity : AppCompatActivity() {
    /**
     * Called when the activity is first created.
     * Sets the layout for the activity using `activity_how_to_play.xml`.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view to the layout for How To Play instructions
        setContentView(R.layout.activity_how_to_play)
    }
}

