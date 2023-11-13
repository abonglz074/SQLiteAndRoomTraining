package com.example.sqlitetraining

data class Note(
    val id: Long,
    val userFirstName: String,
    val userLastName: String,
    val title: String,
    val message: String
)
