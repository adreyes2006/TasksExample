package com.example.tasksexample.data.di

import android.content.Context
import androidx.room.Room
import com.example.tasksexample.data.dao.TaskDao
import com.example.tasksexample.data.database.TasksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTasksDatabase(
        @ApplicationContext context: Context,
    ): TasksDatabase =
        Room
            .databaseBuilder(
                context,
                TasksDatabase::class.java,
                "task.db",
            ).build()

    @Provides
    fun provideTaskDao(tasksDatabase: TasksDatabase): TaskDao = tasksDatabase.taskDao
}
