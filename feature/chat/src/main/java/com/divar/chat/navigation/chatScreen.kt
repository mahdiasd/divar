package com.divar.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.divar.chat.ChatScreen

const val chatRoute = "chat_route"
fun NavGraphBuilder.chatScreen(
) {
    composable(
        route = chatRoute,
    ) {
        ChatScreen()
    }
}

fun NavController.navigateToChat() {
    navigate(chatRoute)
}
