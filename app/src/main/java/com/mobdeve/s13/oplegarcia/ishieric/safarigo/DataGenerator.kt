package com.mobdeve.s13.oplegarcia.ishieric.safarigo

class DataGenerator {
    companion object {
        fun generateData(): ArrayList<Game> {
            val tempList = ArrayList<Game>()
            // Add normal game levels
            tempList.add(Game("Animals", "LET US DISCOVER AND LEARN ABOUT ANIMALS!"))


            return tempList
        }
    }
}
