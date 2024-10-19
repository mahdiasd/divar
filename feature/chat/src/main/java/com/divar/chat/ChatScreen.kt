package com.divar.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.extension.baseModifier

@Composable
fun ChatScreen() {
    Column(
        modifier = Modifier.baseModifier(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
    ) {
        BodyMediumText(text = "Chat Screen")
    }
}