package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.ui.theme.VibrantOrange // Reusing orange as the red color

@Composable
fun PTVHeader(onPolicyClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(VibrantOrange)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "PTV Sports TV App",
            style = MaterialTheme.typography.headlineMedium
        )
        IconButton(onClick = onPolicyClick) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "DMCA Policy"
            )
        }
    }
}

@Composable
fun DMCADialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "DMCA Policy") },
        text = { Text(text = stringResource(id = R.string.dmca_policy)) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}
