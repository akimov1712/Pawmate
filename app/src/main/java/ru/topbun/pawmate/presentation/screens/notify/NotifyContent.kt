package ru.topbun.pawmate.presentation.screens.notify

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

object NotifyScreen: Screen {
    @Composable
    override fun Content() {
        Text(text = "notifyScreen")
    }
}