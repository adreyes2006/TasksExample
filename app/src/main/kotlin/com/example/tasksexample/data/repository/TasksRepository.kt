package com.example.tasksexample.data.repository

import com.example.tasksexample.data.dao.TaskDao
import com.example.tasksexample.data.model.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class
TasksRepository@Inject
    constructor(
        private val taskDao: TaskDao,
    ) {
        suspend fun insertTask(task: TaskEntity) {
            taskDao.insert(task)
        }

        suspend fun deleteTask(task: TaskEntity) {
            taskDao.deletetask(task)
        }

        fun getAllTasks(): Flow<List<TaskEntity>> = taskDao.getAllTasks()
    }
