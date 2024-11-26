package com.mobdeve.s13.oplegarcia.ishieric.safarigo

class DataGenerator {
    companion object {
        /**
         * Generates and returns a list of game data for use in the app.
         *
         * @return ArrayList of Game objects, each containing a level name and description.
         */
        fun generateData(): ArrayList<Game> {
            // Temporary list to hold Game objects
            val tempList = ArrayList<Game>()

            // Add normal game levels
            tempList.add(Game("Animals", "Discover Earth's animals!")) // Example of adding a game level with name and description

            return tempList // Returns the generated list of Game objects
        }
    }
}
