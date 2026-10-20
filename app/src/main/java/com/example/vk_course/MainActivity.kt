package com.example.vk_course

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.vk_course.navigation.ScreenNavigation
import com.example.vk_course.presentation.ApplicationViewModel
import com.example.vk_course.presentation.theme.VK_courseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            VK_courseTheme {
                ScreenNavigation()
            }
        }
    }
}



