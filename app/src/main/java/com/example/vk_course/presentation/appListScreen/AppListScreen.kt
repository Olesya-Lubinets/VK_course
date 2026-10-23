package com.example.vk_course.presentation.appListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vk_course.R
import com.example.vk_course.presentation.ApplicationViewModel
import com.example.vk_course.presentation.AppListState
import kotlinx.coroutines.launch

@Composable
fun AppListScreen(
    modifier: Modifier = Modifier,
    onAppClick: (Int) -> Unit
) {
    val viewModel: ApplicationViewModel = hiltViewModel()
    val state by viewModel.listOfApplications.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(modifier = modifier,
        topBar = {
            MyToolBar(onClick =
            {
                scope.launch {
                    snackbarHostState.showSnackbar("Hey")
                }

            })
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (state) {
                is AppListState.Error -> {
                    Text(text = stringResource(R.string.error_while_fetching_data))
                }

                is AppListState.Loading -> {
                    CircularProgressIndicator()
                }

                is AppListState.Success -> {
                    val appList = (state as AppListState.Success).vkApplicationList
                    if (appList.isEmpty()) {
                        Text(stringResource(R.string.no_applications_found))
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
                                AppListItem(app, onClick = { onAppClick(app.id) })
                            }
                        }
                    }
                }
            }
        }
    }
}
