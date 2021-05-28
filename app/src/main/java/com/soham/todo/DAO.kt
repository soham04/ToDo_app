package com.soham.todo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAO {

    @Insert()
    suspend fun insertTodo(todo: Todo): Long

    @Query("SELECT * from todoTable where isFinished != -1")
    fun getTask(): LiveData<List<Todo>>

    @Query("Update todoTable set isFinished = 1 where id=:uid")
    fun finishTask(uid: Long)

    @Query("Delete from todoTable where id=:uid")
    fun deleteTask(uid: Long)

}