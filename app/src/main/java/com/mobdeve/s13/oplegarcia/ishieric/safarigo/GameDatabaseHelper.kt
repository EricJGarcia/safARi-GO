package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Database helper class to manage the creation, upgrading, and access to a SQLite database.
 * Handles storage and retrieval of scores and level completion status for the game.
 */
class GameDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Database name and version
        const val DATABASE_NAME = "game.db"
        const val DATABASE_VERSION = 2

        // Table and column names
        const val TABLE_SCORES = "scores"
        const val COLUMN_ID = "id"  // Primary key ID column
        const val COLUMN_LEVEL = "level" // Stores level identifiers
        const val COLUMN_POINTS = "points" // Stores points earned in each level
        const val COLUMN_COMPLETED = "completed" // Stores completion status for each level
    }

    /**
     * Called when the database is created for the first time.
     * Creates the "scores" table with columns for ID, level, points, and completion status.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = """
            CREATE TABLE $TABLE_SCORES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_LEVEL TEXT,
                $COLUMN_POINTS INTEGER,
                $COLUMN_COMPLETED INTEGER DEFAULT 0
            )
        """
        db?.execSQL(CREATE_TABLE) // Executes the SQL command to create the table
    }

    /**
     * Called when the database needs to be upgraded.
     * Adds a new "completed" column if upgrading from an older version without it.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            // Adds "completed" column to the existing "scores" table if it doesn't already exist
            db?.execSQL("ALTER TABLE $TABLE_SCORES ADD COLUMN $COLUMN_COMPLETED INTEGER DEFAULT 0")
        }
    }

    /**
     * Marks a specific level as completed by setting the "completed" column to 1.
     *
     * @param level The level identifier to mark as completed.
     */
    fun setLevelCompleted(level: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_COMPLETED, 1) // Sets completion status to true (1)
        }
        db.update(TABLE_SCORES, values, "$COLUMN_LEVEL = ?", arrayOf(level)) // Updates the specific level row
        db.close() // Closes the database connection
    }

    /**
     * Checks if a specific level has been marked as completed.
     *
     * @param level The level identifier to check.
     * @return Boolean indicating whether the level is completed (true) or not (false).
     */
    fun isLevelCompleted(level: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_SCORES,
            arrayOf(COLUMN_COMPLETED), // Selects only the "completed" column
            "$COLUMN_LEVEL = ?", // Filters by level
            arrayOf(level),
            null, null, null
        )

        // Retrieves the completion status
        val completed = if (cursor.moveToFirst()) {
            cursor.getInt(0) == 1 // Returns true if completed, otherwise false
        } else {
            false // Returns false if level doesn't exist
        }

        cursor.close() // Closes the cursor
        db.close() // Closes the database connection
        return completed
    }

    /**
     * Resets all scores and completion statuses in the database.
     * Sets points to 0 and marks all levels as not completed (0).
     */
    fun resetScores() {
        val db = this.writableDatabase
        try {
            val values = ContentValues().apply {
                put(COLUMN_POINTS, 0) // Resets points to 0
                put(COLUMN_COMPLETED, 0) // Resets completion status to not completed (0)
            }
            db.update(TABLE_SCORES, values, null, null) // Updates all rows in the "scores" table
        } finally {
            db.close() // Ensures database connection is closed
        }
    }
}
