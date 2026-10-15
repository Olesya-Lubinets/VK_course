package com.example.vk_course.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.vk_course.domain.VKApplication

@Composable
fun AppElement(app: VKApplication, imageLoader: ImageLoader) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = app.icon,
            modifier = Modifier.size(40.dp),
            contentScale = ContentScale.Crop,
            contentDescription = app.title,
            imageLoader = imageLoader
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = app.title, style = MaterialTheme.typography.titleMedium)
            Text(text = app.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Категория: ${app.category}", style = MaterialTheme.typography.labelSmall)
        }
    }
}