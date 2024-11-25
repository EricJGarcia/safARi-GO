package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SafariLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val animalImageView: ImageView = itemView.findViewById(R.id.animal_image_view)
    private val animalNameTextView: TextView = itemView.findViewById(R.id.animal_name_text_view)
    private val animalDescriptionTextView: TextView = itemView.findViewById(R.id.animal_description_text_view)

    fun bind(animal: Animal) {
        animalImageView.setImageResource(animal.imageResId)
        animalNameTextView.text = animal.name // Set the animal name
        animalDescriptionTextView.text = animal.description // Set the animal description
    }
}


