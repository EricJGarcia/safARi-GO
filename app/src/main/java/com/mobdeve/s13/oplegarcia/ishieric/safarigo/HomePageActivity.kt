package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomePageActivity : AppCompatActivity() {

    // Our data
    private val characterList: ArrayList<Game> = DataGenerator.generateData()
    // Our RecyclerView reference
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        // Initialize the RecyclerView
        this.recyclerView = findViewById(R.id.recyclerView)

        // Set the Adapter. We have to define our own Adapter so that we can properly set the
        // information into the item layout we created. It is typical to pass the data we want
        // displayed into the adapter. There are other variants of RecyclerViews that query data
        // from online sources in batches (instead of passing everything), but we'll get to that
        // when we reach accessing remote DBs.
        this.recyclerView.adapter = MyAdapter(this.characterList)

        // Set the LayoutManager. This can be set to different kinds of LayoutManagers but we're
        // keeping things simple with a LinearLayout.
        this.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
