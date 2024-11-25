package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

class SafariLogActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safari_log)

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recycler_view)

        // Set up layout manager for a vertically-centered single item per screen
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT

        // Sample animal data for display
        val animals = listOf(
            Animal(R.drawable.bat_image, "Bat", "This is a Bat, the only mammal that can truly fly."),
            Animal(R.drawable.bear_image, "Bear", "This is a Bear, known for hibernating in winter and loving honey."),
            Animal(R.drawable.camel_image, "Camel", "This is a Camel, famous for its humps that store fat for long desert journeys."),
            Animal(R.drawable.cat_image, "Cat", "This is a Cat, known for its purring and retractable claws."),
            Animal(R.drawable.cheetah_image, "Cheetah", "This is a Cheetah, the fastest land animal."),
            Animal(R.drawable.chicken_image, "Chicken", "This is a Chicken, a common farm animal that lays eggs."),
            Animal(R.drawable.chameleon_image, "Chameleon", "This is a Chameleon, known for changing color to blend into surroundings."),
            Animal(R.drawable.cow_image, "Cow", "This is a Cow, which produces milk and says 'moo.'"),
            Animal(R.drawable.crab_image, "Crab", "This is a Crab, which has a hard shell and walks sideways."),
            Animal(R.drawable.crocodile_image, "Crocodile", "This is a Crocodile, a reptile with a powerful bite that lives near water."),
            Animal(R.drawable.dolphin_image, "Dolphin", "This is a Dolphin, known for its playful leaps and intelligence."),
            Animal(R.drawable.dog_image, "Dog", "This is a Dog, loyal and known for barking to communicate and loving to fetch."),
            Animal(R.drawable.duck_image, "Duck", "This is a Duck, which quacks and loves swimming in ponds."),
            Animal(R.drawable.fox_image, "Fox", "This is a Fox, known for its cunning nature and reddish fur."),
            Animal(R.drawable.hedgehog_image, "Hedgehog", "This is a Hedgehog, which has spiky quills and rolls up to protect itself."),
            Animal(R.drawable.koala_image, "Koala", "This is a Koala, known for living in trees and eating eucalyptus leaves."),
            Animal(R.drawable.lobster_image, "Lobster", "This is a Lobster, known for its big claws and popularity as seafood."),
            Animal(R.drawable.octopus_image, "Octopus", "This is an Octopus, which has eight arms and can squirt ink to escape."),
            Animal(R.drawable.panda_image, "Panda", "This is a Panda, a black-and-white animal that loves eating bamboo."),
            Animal(R.drawable.penguin_image, "Penguin", "This is a Penguin, known for waddling and living in icy environments."),
            Animal(R.drawable.seal_image, "Seal", "This is a Seal, playful in the ocean and known for clapping its flippers on land."),
            Animal(R.drawable.seahorse_image, "Seahorse", "This is a Seahorse, a tiny sea creature where males carry the babies."),
            Animal(R.drawable.shark_image, "Shark", "This is a Shark, a fast swimmer with sharp teeth that lives in the ocean."),
            Animal(R.drawable.swordfish_image, "Swordfish", "This is a Swordfish, known for its long, pointed 'sword' nose."),
            Animal(R.drawable.stingray_image, "Stingray", "This is a Stingray, which glides along the ocean floor with a long, thin tail."),
            Animal(R.drawable.tiger_image, "Tiger", "This is a Tiger, known for its orange coat with black stripes."),
            Animal(R.drawable.walrus_image, "Walrus", "This is a Walrus, which has large tusks and whiskers and lives in cold waters."),
            Animal(R.drawable.whale_image, "Whale", "This is a Whale, one of the largest animals in the ocean that breathes through a blowhole.")
        )


        // Set the adapter
        recyclerView.adapter = SafariLogAdapter(animals)
    }
}

