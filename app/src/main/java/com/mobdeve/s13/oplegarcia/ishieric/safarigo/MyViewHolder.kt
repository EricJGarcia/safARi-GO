package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder class for binding data to a single item view in the RecyclerView.
 * This ViewHolder handles the display of a Game object.
 *
 * @param itemView The root view for a single item in the RecyclerView.
 */
class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // Declare the views that we want to bind data to
    private val gamenoTv: TextView = itemView.findViewById(R.id.gameno_Tv)
    private val gamedescTv: TextView = itemView.findViewById(R.id.gamedesc_Tv)
    private val playBtn: Button = itemView.findViewById(R.id.play_Btn)

    /**
     * Binds the data from a Game object to the views in this ViewHolder.
     *
     * @param game The Game object containing the data to display.
     */
    fun bindData(game: Game) {
        // Set the game number and description text
        gamenoTv.text = game.gameId
        gamedescTv.text = game.description

        // Set up the play button to navigate to SelectLevelActivity
        playBtn.setOnClickListener {
            // Get the context from the item view and create an Intent to start SelectLevelActivity
            val context = itemView.context
            val intent = Intent(context, SelectLevelActivity::class.java)

            // Start the SelectLevelActivity when the button is clicked
            context.startActivity(intent)
        }
    }
}
