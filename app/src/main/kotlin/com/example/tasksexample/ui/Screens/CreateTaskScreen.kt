package com.example.tasksexample.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.tasksexample.data.model.TaskEntity
import com.example.tasksexample.ui.theme.TasksExampleTheme
import com.example.tasksexample.viewModel.TasksViewModel

@Composable
fun CreateTask(
    viewModel: TasksViewModel,
    onTaskAdded: () -> Unit,
) {
    var title: String by remember {
        mutableStateOf(value = "")
    }
    var content: String by remember {
        mutableStateOf(value = "")
    }
    val task =
        TaskEntity(
            content = content,
            title = title,
        )

    TasksExampleTheme {
        Surface {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Header(text = "Create your task")
                AddTaskComponent(
                    title = title,
                    onTitleChange = { title = it },
                    content = content,
                    onContentChange = { content = it },
                    insertTask = { viewModel.insertTask(task) },
                    onTaskAdded = { onTaskAdded() },
                )
            }
        }
    }
}

@Composable
fun AddTaskComponent(
    title: String,
    onTitleChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit,
    insertTask: () -> Unit,
    onTaskAdded: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .padding(15.dp)
                .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text("Title") },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(60.dp),
        )
        OutlinedTextField(
            value = content,
            onValueChange = onContentChange,
            label = { Text("Content") },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(240.dp),
        )

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = {
                    insertTask()
                    onTaskAdded()
                },
                modifier =
                    Modifier
                        .width(120.dp),
            ) {
                Text(text = "Add Task")
            }
            Button(
                onClick = {
                    onTaskAdded()
                },
                modifier =
                    Modifier
                        .width(120.dp),
            ) {
                Text(text = "Cancel")
            }
        }
    }
}

@PreviewLightDark
@Composable
fun TaskPreview() {
    TasksExampleTheme {
        Surface {
            AddTaskComponent(
                title = "First Task",
                content = "First task Description",
                onTitleChange = {},
                onContentChange = {},
                insertTask = {},
                onTaskAdded = {},
            )
        }
    }
}
