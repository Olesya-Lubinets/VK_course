package com.example.vk_course

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.example.vk_course.components.AppElement
import com.example.vk_course.ui.ApplicationViewModel
import com.example.vk_course.ui.theme.VK_courseTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val applicationViewModel: ApplicationViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Add data to database at first run
        // MockData.applications.forEach { applicationViewModel.addApplication(it) }

        enableEdgeToEdge()
        setContent {
            VK_courseTheme {
                AppListScreen(modifier = Modifier.fillMaxSize(), imageLoader)
            }
        }
    }
}



