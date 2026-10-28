package com.example.vk_course.presentation.appDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vk_course.R
import com.example.vk_course.presentation.AppListState
import com.example.vk_course.presentation.ApplicationViewModel

@Composable
fun ShowCase(onAppClick: (Int) -> Unit) {
    val viewModel: ApplicationViewModel = hiltViewModel()
    val state by viewModel.listOfApplications.collectAsStateWithLifecycle()
    when (state) {
        is AppListState.Error -> Text(stringResource(R.string.error_while_fetching_data))
        is AppListState.Loading -> CircularProgressIndicator()
        is AppListState.Success -> {
            val appList = (state as AppListState.Success).applicationList
            if (appList.isEmpty()) {
                Text(stringResource(R.string.no_applications_found))
            } else {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = stringResource(R.string.show_case),
                        style = MaterialTheme.typography.titleMedium
                    )
                        AppsPager(appList, onAppClick)
                }
            }
        }
    }

}