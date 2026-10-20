package com.example.vk_course.presentation.appDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vk_course.R
import com.example.vk_course.presentation.ApplicationViewModel
import com.example.vk_course.presentation.AppDetailsState

@Composable
fun AppDetailsScreen(id: Int, onAppClick: (Int)-> Unit) {

    val viewModel: ApplicationViewModel = hiltViewModel()
    viewModel.getById(id)
    val appState by viewModel.appByID.collectAsStateWithLifecycle()
    Column() {
        when (appState) {
            is AppDetailsState.Loading -> {
                CircularProgressIndicator()
            }

            is AppDetailsState.Error -> {
                Text(stringResource(R.string.wrong_id))
            }

            is AppDetailsState.Success -> {
                val currentApp = (appState as AppDetailsState.Success).application
                AppDetailsContent(currentApp)
            }
        }
        ShowCase(onAppClick)
    }
}

