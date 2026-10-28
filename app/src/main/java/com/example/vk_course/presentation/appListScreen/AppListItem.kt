package com.example.vk_course.presentation.appListScreen

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vk_course.R
import com.example.vk_course.domain.model.Application
import com.example.vk_course.domain.model.getCategoryFromResources

@Composable
fun AppListItem(app: Application, onClick:()->Unit ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        AsyncImage(
            model = app.iconUrl,
            modifier = Modifier.size(40.dp),
            contentScale = ContentScale.Crop,
            contentDescription = app.name,
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = app.name, style = MaterialTheme.typography.titleMedium)
            Text(text = app.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = stringResource(R.string.category,stringResource(app.category.getCategoryFromResources())), style = MaterialTheme.typography.labelSmall)
        }
    }
}