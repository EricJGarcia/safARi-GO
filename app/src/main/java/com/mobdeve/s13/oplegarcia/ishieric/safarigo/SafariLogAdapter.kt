package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter class for displaying a list of animals in the SafariLogRecyclerView.
 * Each item shows an animal image and its name in the Safari Log.
 *
 * @param animals The list of Animal objects to display.
 */
class SafariLogAdapter(private val animals: List<Animal>) : RecyclerView.Adapter<SafariLogViewHolder>() {

    /**
     * Creates a new ViewHolder to hold an item view in the RecyclerView.
     *
     * @param parent The parent ViewGroup that this ViewHolder's item view will be attached to.
     * @param viewType The type of the view being created.
     * @return A new SafariLogViewHolder object that holds the item view.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SafariLogViewHolder {
        // Inflate the item layout for each animal in the list
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_safari_log, parent, false)

        // Return a new SafariLogViewHolder with the inflated view
        return SafariLogViewHolder(view)
    }

    /**
     * Binds data to the given ViewHolder.
     *
     * @param holder The SafariLogViewHolder to bind data to.
     * @param position The position of the item within the RecyclerView's dataset.
     */
    override fun onBindViewHolder(holder: SafariLogViewHolder, position: Int) {
        // Get the animal at the current position in the list
        val animal = animals[position]

        // Bind the animal data to the ViewHolder
        holder.bind(animal)
    }

    /**
     * Returns the total number of items in the dataset.
     *
     * @return The size of the animals list.
     */
    override fun getItemCount(): Int {
        // Return the number of animals in the list
        return animals.size
    }
}
