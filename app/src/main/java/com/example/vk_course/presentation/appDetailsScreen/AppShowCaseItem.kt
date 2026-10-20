package com.example.vk_course.presentation.appDetailsScreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vk_course.domain.model.VKApplication

@Composable
fun AppShowCaseItem(app: VKApplication, onClick: (Int) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onClick(app.id)
                Log.d("AppShowCaseItem","Navigate with ID: ${app.id}")
            }
    ) {
        AsyncImage(
            model = app.iconUrl,
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Crop,
            contentDescription = app.name,
        )
        Text(text = app.name, style = MaterialTheme.typography.titleMedium)
    }
}