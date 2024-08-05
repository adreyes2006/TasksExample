package com.example.tasksexample.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tasksexample.data.dao.TaskDao
import com.example.tasksexample.data.model.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
)
abstract class TasksDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
}
