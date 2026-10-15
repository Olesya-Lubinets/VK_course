package com.example.vk_course

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.example.vk_course.data.MockData
import com.example.vk_course.ui.ApplicationViewModel
import com.example.vk_course.ui.theme.VK_courseTheme
import dagger.hilt.android.AndroidEntryPoint
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import com.example.vk_course.domain.VKApplication
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.first

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val applicationViewModel: ApplicationViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Add data to database at first run
       // MockData.applications.forEach { applicationViewModel.addApplication(it) }

        enableEdgeToEdge()
        setContent {
            VK_courseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting( modifier = Modifier.padding(innerPadding) )
                }
            }
        }
    }
}



@Composable
fun Greeting(modifier: Modifier,
             viewModel: ApplicationViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
    }
    val state by viewModel.listOfApplications
        .collectAsStateWithLifecycle()
    when (state) {
        is DBState.Error -> Text( text = "Error while fetching data")
        is DBState.Loading -> {}
        is DBState.Success -> {
            val appList = (state as DBState.Success).vkApplicationList
            Column (modifier = Modifier.statusBarsPadding()) {
                MyToolBar(imageLoader, appList.first())
                Spacer(modifier = Modifier.size(16.dp))
                MyAppList(imageLoader,appList)
            }
        }
    }

}

@Composable
fun MyToolBar(imageLoader:ImageLoader, app:VKApplication) {
    Row(modifier = Modifier.background(Color.LightGray).fillMaxWidth()
        .padding(vertical = 16.dp).padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        AsyncImage(
            model = app.icon,
            modifier = Modifier.size(56.dp),
            contentScale = ContentScale.Crop,
            imageLoader = imageLoader,
            contentDescription = "Current app icon"
        )
        Text(
            text = app.title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }

}

@Composable
fun MyAppList(imageLoader:ImageLoader,appList:List<VKApplication>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 16.dp)) {
        items(appList) { app ->
            AppElement(app,imageLoader)
        }
    }
}

@Composable
fun AppElement(app:VKApplication, imageLoader: ImageLoader) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        AsyncImage(
            model = app.icon,
            modifier = Modifier.size(40.dp),
            contentScale = ContentScale.Crop,
            contentDescription = "Icon",
            imageLoader = imageLoader
        )
        Column() {
            Text(text = app.title,
                style = MaterialTheme.typography.titleMedium)
            Text(text = app.description)
            Text(text = "Категория:"+ app.category.toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}

