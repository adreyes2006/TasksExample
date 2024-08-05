package com.example.tasksexample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasksexample.ui.Screens.CreateTask
import com.example.tasksexample.ui.Screens.General
import com.example.tasksexample.viewModel.TasksViewModel

const val home_screen = "home"
const val add_task = "addTask"

@Composable
fun TasksNavigation(viewModel: TasksViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = home_screen) {
        composable(route = home_screen) {
            General(viewModel, navigateToAddTask = { navController.navigate(route = add_task) })
        }
        composable(route = add_task) {
            CreateTask(
                viewModel = viewModel,
                onTaskAdded = { navController.popBackStack() },
            )
        }
    }
}
