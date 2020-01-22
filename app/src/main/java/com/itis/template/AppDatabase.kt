package com.itis.template

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itis.template.dao.TaskDAO
import com.itis.template.entity.Task

@Database(entities = [Task::class], version = 4)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDAO(): TaskDAO

    companion object {
        private const val DATABASE_NAME = "task.db"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            buildDB(context).also { instance = it }
        }

        private fun buildDB(context: Context) =
                Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
    }
}
