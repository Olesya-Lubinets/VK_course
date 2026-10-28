package com.example.vk_course.presentation.appDetailsScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.vk_course.domain.model.Application
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.vk_course.R
import com.example.vk_course.domain.model.getCategoryFromResources
import com.example.vk_course.presentation.ApplicationViewModel
import com.example.vk_course.presentation.DownloadEvent

@Composable
fun AppDetailsContent(
    currentApp: Application,
    snackbarHostState: SnackbarHostState,
    viewModel:ApplicationViewModel,
) {

    LaunchedEffect(viewModel) {
        viewModel.downloadEvent.collect { event->
            Log.d("AppDetailsContent(","Event: ${event}")
            when (event) {
                is DownloadEvent.Success -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is DownloadEvent.Error -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // App header
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = currentApp.iconUrl,
                    contentDescription = currentApp.name,
                    modifier = Modifier
                        .size(96.dp),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = currentApp.name,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = currentApp.developer,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = stringResource(currentApp.category.getCategoryFromResources()),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = stringResource(R.string.age, currentApp.ageRating),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Description
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.description),
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = currentApp.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Screenshots
            currentApp.screenshotUrlList?.takeIf { it.isNotEmpty() }?.let { screenshots ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.Screenshots),
                        style = MaterialTheme.typography.titleMedium
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(screenshots) { screenshot ->
                            AsyncImage(
                                model = screenshot,
                                contentDescription = stringResource(
                                    R.string.screenshot,
                                    currentApp.name
                                ),
                                modifier = Modifier
                                    .width(320.dp)
                                    .height(180.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
            Button(
                onClick = {
                    viewModel.downloadApp(currentApp.id)
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
            ) {
                Text(text = stringResource(R.string.Install),  style = MaterialTheme.typography.titleMedium)
            }
        }

    }