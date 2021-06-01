package com.soham.todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Todo::class], version = 1, exportSchema = false)
@TypeConverters(dateConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun DAO1(): DAO

//
//    companion object {
//        // Singleton prevents multiple instances of database opening at the
//        // same time.
//        @Volatile
//        private var INSTANCE: AppDataBase? = null
//
//        fun getDatabase(context: Context): AppDataBase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDataBase::class.java,
//                    "todo.db"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//    }

}