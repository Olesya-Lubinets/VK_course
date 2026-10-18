package com.example.vk_course

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import com.example.vk_course.components.AppElement
import com.example.vk_course.components.MyToolBar
import com.example.vk_course.data.MockData
import com.example.vk_course.ui.ApplicationViewModel


@Composable
fun AppListScreen(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    viewModel: ApplicationViewModel = hiltViewModel()
) {
    val state by viewModel.listOfApplications.collectAsStateWithLifecycle()
    val mockApplication = MockData.applications.first()

    Scaffold(modifier = modifier,
        topBar = {
                MyToolBar(imageLoader, mockApplication)
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is DBState.Error -> {
                    Text(text = "Error while fetching data")
                }

                is DBState.Loading -> {
                    CircularProgressIndicator()
                }

                is DBState.Success -> {
                    val appList = (state as DBState.Success).vkApplicationList
                    if (appList.isEmpty()) {
                        Text("No applications yet")
                    } else {

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                vertical = 16.dp,
                                horizontal = 16.dp
                            )
                        ) {
                            items(appList,
                                key = { app -> app.id }
                            ) { app ->
                                AppElement(app, imageLoader)
                            }
                        }
                    }
                }
            }
        }
    }
}