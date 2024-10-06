package com.divar.ui.core.failed_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.divar.ui.R
import com.divar.ui.core.text.BodyMediumText

@Composable
fun FailedScreen(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.failed_screen_message),
    onRefresh: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
    ) {
        BodyMediumText(text = text)
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Filled.Refresh,
            contentDescription = "refresh"
        )
    }
}