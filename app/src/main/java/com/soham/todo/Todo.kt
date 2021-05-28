package com.soham.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoTable")
data class Todo(
    val title: String,
    val description: String,
    val category: String,
    val data: Long,
    val time: Long,
    val isFinished: Int = -1,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)