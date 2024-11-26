package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder class that represents each item in the Safari Log.
 * It binds the data (image, name, and description) of an animal to the item view.
 *
 * @param itemView The view representing a single item in the RecyclerView.
 */
class SafariLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // ImageView for displaying the animal's image
    private val animalImageView: ImageView = itemView.findViewById(R.id.animal_image_view)

    // TextView for displaying the animal's name
    private val animalNameTextView: TextView = itemView.findViewById(R.id.animal_name_text_view)

    // TextView for displaying the animal's description
    private val animalDescriptionTextView: TextView = itemView.findViewById(R.id.animal_description_text_view)

    /**
     * Binds the animal data to the corresponding views (ImageView and TextViews).
     *
     * @param animal The Animal object containing the data to be displayed.
     */
    fun bind(animal: Animal) {
        // Set the image resource for the animal's image
        animalImageView.setImageResource(animal.imageResId)

        // Set the animal name text
        animalNameTextView.text = animal.name

        // Set the animal description text
        animalDescriptionTextView.text = animal.description
    }
}
