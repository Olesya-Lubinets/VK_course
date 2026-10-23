package com.example.vk_course.presentation.appDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vk_course.R
import com.example.vk_course.presentation.ApplicationViewModel
import com.example.vk_course.presentation.AppDetailsState
import com.example.vk_course.presentation.appListScreen.MyToolBar
import kotlinx.coroutines.launch

@Composable
fun AppDetailsScreen(id: Int, onAppClick: (Int) -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val viewModel: ApplicationViewModel = hiltViewModel()
    LaunchedEffect(id) {
        viewModel.getById(id)
    }
    val appState by viewModel.appByID.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier,
        topBar = {
            MyToolBar(onClick =
            {
                scope.launch {
                    snackbarHostState.showSnackbar("Hey")
                }

            })
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            when (appState) {
                is AppDetailsState.Loading -> {
                    CircularProgressIndicator()
                }

                is AppDetailsState.Error -> {
                    Text(stringResource(R.string.wrong_id))
                }

                is AppDetailsState.Success -> {
                    val currentApp = (appState as AppDetailsState.Success).application
                    AppDetailsContent(currentApp,snackbarHostState,viewModel)
                }
            }
            ShowCase(onAppClick)
        }
    }

}

