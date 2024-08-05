package com.example.tasksexample.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasksexample.data.model.TaskEntity
import com.example.tasksexample.data.repository.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TasksViewModel@Inject constructor(
    private val tasksRepository: TasksRepository
) : ViewModel() {

    private val _tasksUiState = MutableStateFlow(TasksUiState())
    val tasksUiState: StateFlow<TasksUiState> = _tasksUiState.asStateFlow()

    init {
        observeTasks()
    }

    fun insertTask(task: TaskEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            tasksRepository.insertTask(task)
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            tasksRepository.deleteTask(task)
        }
    }

    private fun observeTasks() {
        viewModelScope.launch {
            tasksRepository.getAllTasks().collect { tasksList ->
                _tasksUiState.update {
                    it.copy(tasks = tasksList)
                }
            }
        }
    }

}


data class TasksUiState(
    val tasks: List<TaskEntity> = emptyList()
)