package com.mobdeve.s13.oplegarcia.ishieric.safarigo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GameDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "game.db"
        const val DATABASE_VERSION = 1
        const val TABLE_SCORES = "scores"
        const val COLUMN_ID = "id"  // Add COLUMN_ID
        const val COLUMN_LEVEL = "level"
        const val COLUMN_POINTS = "points"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = """
            CREATE TABLE $TABLE_SCORES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_LEVEL TEXT,
                $COLUMN_POINTS INTEGER
            )
        """
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_SCORES")
        onCreate(db)
    }

    // Function to reset all scores to 0
    fun resetScores() {
        val db = this.writableDatabase
        try {
            // Update all scores to 0
            val values = ContentValues().apply {
                put(COLUMN_POINTS, 0)
            }
            db.update(TABLE_SCORES, values, null, null)
        } finally {
            db.close()
        }
    }}


