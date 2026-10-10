package com.example.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.ui.theme.VibrantBlue
import com.example.ui.theme.VibrantOrange

data class MatchCategory(val name: String, val id: String)

@Composable
fun MatchGrid(onCategoryClick: (String) -> Unit) {
    val categories = listOf(
        MatchCategory("International Matches", "international"),
        MatchCategory("Leagues Matches", "leagues"),
        MatchCategory("All Matches", "all")
    )

    Row(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        categories.forEach { category ->
            MatchCard(category, onClick = { onCategoryClick(category.id) })
        }
    }
}

@Composable
fun MatchCard(category: MatchCategory, onClick: () -> Unit) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (pressed) 0.95f else 1f)
    val color by animateColorAsState(if (pressed) VibrantOrange else VibrantBlue)

    Card(
        modifier = Modifier
            .size(180.dp)
            .padding(8.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .clickable {
                pressed = true
                onClick()
            },
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Sports,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = androidx.compose.ui.graphics.Color.White
            )
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleMedium,
                color = androidx.compose.ui.graphics.Color.White
            )
        }
    }
}
