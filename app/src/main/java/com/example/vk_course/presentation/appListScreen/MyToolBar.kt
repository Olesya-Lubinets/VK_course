package com.example.vk_course.presentation.appListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vk_course.domain.model.VKApplication

@Composable
fun MyToolBar(app: VKApplication) {
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        AsyncImage(
            model = app.iconUrl,
            modifier = Modifier.size(56.dp),
            contentScale = ContentScale.Crop,
            contentDescription = app.name
        )
        Text(
            text = app.name,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}