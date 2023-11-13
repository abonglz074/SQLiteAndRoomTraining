package com.example.sqlitetraining

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(
    context: Context
) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_NAME = "to_do_app.db"
        private const val DATABASE_VERSION = 1

        const val NOTE_TABLE_NAME = "note"
        const val COLUMN_NAME_NOTE_ID = "noteId"
        const val COLUMN_NAME_USER_FIRST_NAME = "userFirstName"
        const val COLUMN_NAME_USER_LAST_NAME = "userLastName"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_MESSAGE = "message"

        private const val CREATE_TABLE_NOTE = """
            CREATE TABLE $NOTE_TABLE_NAME (
                $COLUMN_NAME_NOTE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME_USER_FIRST_NAME TEXT NOT NULL,
                $COLUMN_NAME_USER_LAST_NAME TEXT NOT NULL,
                $COLUMN_NAME_TITLE TEXT NOT NULL,
                $COLUMN_NAME_MESSAGE TEXT
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $NOTE_TABLE_NAME")

        onCreate(db)
    }
}