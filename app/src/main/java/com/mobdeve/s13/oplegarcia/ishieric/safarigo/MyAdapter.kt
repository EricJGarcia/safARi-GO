package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter class for managing and binding data to a RecyclerView.
 * This adapter takes a list of Game objects and displays them in the RecyclerView.
 *
 * @property data The list of Game objects to display.
 */
class MyAdapter(private val data: ArrayList<Game>) : RecyclerView.Adapter<MyViewHolder>() {

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     * This method inflates the item layout and creates a new ViewHolder instance.
     *
     * @param parent The ViewGroup into which the new View will be added.
     * @param viewType The view type of the new View.
     * @return A new MyViewHolder instance with the inflated item layout.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        // Inflate the layout for each item in the RecyclerView
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method binds the data from the Game object to the ViewHolder.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item.
     * @param position The position of the item within the data list.
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = data[position]

        // Bind the Game object data to the view holder
        holder.bindData(review)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The number of items in the data list.
     */
    override fun getItemCount(): Int {
        return data.size
    }
}
