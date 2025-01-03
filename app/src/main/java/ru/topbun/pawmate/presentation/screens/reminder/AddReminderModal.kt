package ru.topbun.pawmate.presentation.screens.reminder

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ru.topbun.pawmate.entity.Reminder

@Composable
fun AddReminderDialog(onSave: (Reminder) -> Unit, onDismiss: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dateTime by remember { mutableStateOf(System.currentTimeMillis() + 60000) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Добавить напоминание") },
        text = {
            Column {
                OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Заголовок") })
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Описание") })
                // Здесь можно добавить выбор даты и времени
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(Reminder(title = title, description = description, frequency = 3434, dateTime = dateTime))
                onDismiss()
            }) {
                Text("Сохранить")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
}
