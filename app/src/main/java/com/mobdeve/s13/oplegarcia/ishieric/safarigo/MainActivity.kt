package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.btn_start)
        val howToPlayButton: Button = findViewById(R.id.btn_how_to_play)

        // When the "START" button is clicked, go to HomePageActivity first
        startButton.setOnClickListener {
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }

        // When the "How to Play" button is clicked, go to HowToPlayActivity
        howToPlayButton.setOnClickListener {
            val intent = Intent(this, HowToPlayActivity::class.java)
            startActivity(intent)
        }
    }
}


