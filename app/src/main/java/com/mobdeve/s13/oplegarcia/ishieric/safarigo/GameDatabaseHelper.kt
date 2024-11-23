package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GameDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Define objects inside the database
    companion object {
        const val DATABASE_NAME = "game.db"
        const val DATABASE_VERSION = 1
        const val TABLE_SCORES = "scores"
        const val COLUMN_ID = "id"  // Add COLUMN_ID
        const val COLUMN_LEVEL = "level"
        const val COLUMN_POINTS = "points"
        const val COLUMN_COMPLETED = "completed"
    }

    // Function to create the database
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = """
            CREATE TABLE $TABLE_SCORES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_LEVEL TEXT,
                $COLUMN_POINTS INTEGER,
                $COLUMN_COMPLETED INTEGER DEFAULT 0
            )
        """
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            // Add the completed column to existing table
            db?.execSQL("ALTER TABLE $TABLE_SCORES ADD COLUMN $COLUMN_COMPLETED INTEGER DEFAULT 0")
        }
    }

    // Add function to mark level as completed
    fun setLevelCompleted(level: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_COMPLETED, 1)
        }
        db.update(TABLE_SCORES, values, "$COLUMN_LEVEL = ?", arrayOf(level))
        db.close()
    }

    fun isLevelCompleted(level: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_SCORES,
            arrayOf(COLUMN_COMPLETED),
            "$COLUMN_LEVEL = ?",
            arrayOf(level),
            null, null, null
        )

        val completed = if (cursor.moveToFirst()) {
            cursor.getInt(0) == 1
        } else {
            false
        }

        cursor.close()
        db.close()
        return completed
    }

    // Reset function also resets completion status
    fun resetScores() {
        val db = this.writableDatabase
        try {
            val values = ContentValues().apply {
                put(COLUMN_POINTS, 0)
                put(COLUMN_COMPLETED, 0)
            }
            db.update(TABLE_SCORES, values, null, null)
        } finally {
            db.close()
        }
    }
}


