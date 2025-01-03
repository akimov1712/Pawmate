package ru.topbun.pawmate.presentation.screens.reminder

import android.text.format.DateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import ru.topbun.pawmate.entity.Reminder
import ru.topbun.pawmate.presentation.screens.profile.ProfileViewModel

object ReminderScreen: Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel = rememberScreenModel { ReminderViewModel(context) }
        val state by viewModel.state.collectAsState()

        var onOpenAddReminder by remember { mutableStateOf(false) }
        if (onOpenAddReminder){
            AddReminderDialog(onSave = {
                viewModel.addReminder(it)
            }) {
                onOpenAddReminder = false
            }
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Button(onClick = {
                onOpenAddReminder = true
            }) {
                Text("Создать напоминание")
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.reminderList) { reminder ->
                    ReminderItem(reminder, onEdit = { }, onDelete = {
                        viewModel.deleteReminder(reminder)
                    })
                }
            }
        }
    }
}

@Composable
fun ReminderItem(reminder: Reminder, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(reminder.title)
            Text(reminder.description,)
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("Дата: ${DateFormat.format("dd.MM.yyyy HH:mm", reminder.dateTime)}")
                Row {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Редактировать")
                    }
                    IconButton(onClick = onDelete) {
                        Icon(Icons.Default.Delete, contentDescription = "Удалить")
                    }
                }
            }
        }
    }
}
