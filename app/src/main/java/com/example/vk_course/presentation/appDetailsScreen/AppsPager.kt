package com.example.vk_course.presentation.appDetailsScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vk_course.domain.model.Application

@Composable
fun AppsPager(appList: List<Application>, onAppClick: (String) -> Unit) {

    val itemsPerPage = 3
    val chunkedList = appList.chunked(itemsPerPage)

    val pagerState = rememberPagerState(
        pageCount = { chunkedList.size }
    )
    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) { page ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            val groupByPage = chunkedList[page]

            groupByPage.forEach { appItem ->
                Log.d(
                    "AppsPager",
                    "name=${appItem.name}, id=${appItem.id}"
                )
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    AppShowCaseItem(
                        app = appItem,
                        onClick = onAppClick
                    )
                }
            }
        }
    }
}