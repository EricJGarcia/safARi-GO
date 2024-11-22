package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val gamenoTv: TextView = itemView.findViewById(R.id.gameno_Tv)
    private val gamedescTv: TextView = itemView.findViewById(R.id.gamedesc_Tv)
    private val playBtn: Button = itemView.findViewById(R.id.play_Btn)

    fun bindData(game: Game) {
        gamenoTv.text = game.gameId
        gamedescTv.text = game.description

        // Set up the play button to navigate to SelectLevelActivity
        playBtn.setOnClickListener {
            val context = itemView.context
            val intent = Intent(context, SelectLevelActivity::class.java)
            context.startActivity(intent)
        }
    }
}


