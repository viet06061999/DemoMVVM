package com.sun.mvvm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id: Int,
    val email: String,
    val password: String
)
