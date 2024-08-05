package com.example.tasksexample.ui.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.tasksexample.data.model.TaskEntity
import com.example.tasksexample.ui.theme.TasksExampleTheme
import com.example.tasksexample.viewModel.TasksUiState
import com.example.tasksexample.viewModel.TasksViewModel

@Composable
fun General(taskViewModel: TasksViewModel, navigateToAddTask: () -> Unit) {
    val notesUiState by taskViewModel.tasksUiState.collectAsState()
    TasksExampleTheme {
        Surface {
            HomeScreen(uiState = notesUiState,
                navigateToAddNote = navigateToAddTask,
                onDeleteTask = { taskEntity -> taskViewModel.deleteTask(taskEntity) })
        }
    }
}

@Composable
fun HomeScreen(
    uiState: TasksUiState, navigateToAddNote: () -> Unit, onDeleteTask: (TaskEntity) -> Unit
) {
    Scaffold(
        topBar = {
            Header(text = "Task Monitor")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToAddNote() }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        },
        modifier = Modifier.padding(15.dp).fillMaxSize(),
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            items(items = uiState.tasks) {
                TaskSection(modifier = Modifier,
                    title = it.title,
                    content = it.content,
                    onDelete = { onDeleteTask(it) }

                )
            }
        }
    }
}

@Composable
fun Header(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.displayMedium
        )
    }
}

@Composable
fun TaskSection(
    modifier: Modifier, title: String, content: String, onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, Color.Black),
    ) {
        Row(
            modifier = modifier.fillMaxWidth().height(60.dp).padding(
                    start = 15.dp,
                    end = 15.dp,
                ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icons.Filled.AccountCircle
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = modifier.width(260.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = content,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp).clickable {
                        onDelete()
                    })
              }
        }
}

@PreviewLightDark
@Composable
fun TabPreview() {
    TasksExampleTheme {
        Surface {
            TaskSection(
                modifier = Modifier,
                title = "First Note",
                content = "This is my first note",
                onDelete = {},
            )
        }
    }
}

@PreviewLightDark
@Composable
fun HeaderPreview() {
    TasksExampleTheme {
        Surface {
            Header(
                text = "Tasks Monitor"
            )
        }
    }
}

