package com.example.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.theme.VibrantGreen

data class StreamLink(val name: String, val url: String)

@Composable
fun LinksScreen(categoryId: String, onLinkClick: (String) -> Unit) {
    val links = when (categoryId) {
        "international" -> listOf(
            StreamLink("Channel 1", "https://malikfaizangamerr-coder.github.io/Rizzonewchannel/#ch=1"),
            StreamLink("Channel 2", "https://malikfaizangamerr-coder.github.io/Rizzonewchannel/#ch=2"),
            StreamLink("Channel 5", "https://malikfaizangamerr-coder.github.io/Rizzonewchannel/#ch=5"),
            StreamLink("Channel 7", "https://malikfaizangamerr-coder.github.io/Rizzonewchannel/#ch=7"),
            StreamLink("Channel 9", "https://malikfaizangamerr-coder.github.io/Rizzonewchannel/#ch=9")
        )
        "leagues" -> listOf(
            StreamLink("Channel 1", "https://malikfaizangamerr-coder.github.io/Rizzonewchannel/#ch=1"),
            StreamLink("Channel 3", "https://malikfaizangamerr-coder.github.io/Rizzonewchannel/#ch=3"),
            StreamLink("Channel 4", "https://malikfaizangamerr-coder.github.io/Rizzonewchannel/#ch=4"),
            StreamLink("Channel 5", "https://malikfaizangamerr-coder.github.io/Rizzonewchannel/#ch=5"),
            StreamLink("Channel 7", "https://malikfaizangamerr-coder.github.io/Rizzonewchannel/#ch=7")
        )
        "all" -> (1..11).map { StreamLink("Channel $it", "https://malikfaizangamerr-coder.github.io/Rizzonewchannel/#ch=$it") }
        else -> emptyList()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(links.size) { index ->
            LinkCard(links[index], onClick = { onLinkClick(links[index].url) })
        }
    }
}

@Composable
fun LinkCard(link: StreamLink, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = VibrantGreen),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
            Text(text = link.name, color = androidx.compose.ui.graphics.Color.White)
        }
    }
}
