package com.example.sqlitetraining

import android.content.ContentValues
import android.content.Context
import com.example.sqlitetraining.DatabaseHelper.Companion.COLUMN_NAME_MESSAGE
import com.example.sqlitetraining.DatabaseHelper.Companion.COLUMN_NAME_NOTE_ID
import com.example.sqlitetraining.DatabaseHelper.Companion.COLUMN_NAME_TITLE
import com.example.sqlitetraining.DatabaseHelper.Companion.COLUMN_NAME_USER_FIRST_NAME
import com.example.sqlitetraining.DatabaseHelper.Companion.COLUMN_NAME_USER_LAST_NAME
import com.example.sqlitetraining.DatabaseHelper.Companion.NOTE_TABLE_NAME

class ToDoDatabase(context: Context) {

    private val databaseHelper = DatabaseHelper(context)

    fun insertNote(noteId: Long, userFirstName: String, userLastName: String, title: String, message: String) {
        val writableDatabse = databaseHelper.writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_NAME_NOTE_ID, noteId)
            put(COLUMN_NAME_USER_FIRST_NAME, userFirstName)
            put(COLUMN_NAME_USER_LAST_NAME, userLastName)
            put(COLUMN_NAME_TITLE, title)
            put(COLUMN_NAME_MESSAGE, message)
        }

        writableDatabse.insert(
            NOTE_TABLE_NAME,
            null,
            values
        )

        writableDatabse.close()
    }

    fun getAllNotes(): List<Note> {
        val list = mutableListOf<Note>()

        val readableDatabase = databaseHelper.readableDatabase

        readableDatabase.rawQuery(
            "SELECT * FROM $NOTE_TABLE_NAME",
            null
        ).use { cursor ->
            val noteId = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_NAME_NOTE_ID))
            val userFirstName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_USER_FIRST_NAME))
            val userLastName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_USER_LAST_NAME))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TITLE))
            val message = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_MESSAGE))
            list.add(
                Note(
                    id = noteId,
                    userFirstName = userFirstName,
                    userLastName = userLastName,
                    title = title,
                    message = message
                )
            )

            readableDatabase.close()
            return list
        }
    }
}