package com.mobdeve.s13.oplegarcia.ishieric.safarigo


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SafariLogAdapter(private val animals: List<Animal>) : RecyclerView.Adapter<SafariLogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SafariLogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_safari_log, parent, false)
        return SafariLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: SafariLogViewHolder, position: Int) {
        val animal = animals[position]
        holder.bind(animal)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

}
