package com.example.vk_course.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vk_course.presentation.appDetailsScreen.AppDetailsScreen
import com.example.vk_course.presentation.appListScreen.AppListScreen
import androidx.navigation.toRoute

@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.AppList
    ) {

        composable<NavRoutes.AppList> {
            AppListScreen(
                onAppClick = { appId ->
                    navController.navigate(NavRoutes.AppDetails(id = appId))
                }
            )
        }

        composable<NavRoutes.AppDetails> { backStackEntry ->
            val route = backStackEntry.toRoute<NavRoutes.AppDetails>()
            AppDetailsScreen(
                id = route.id,
                onAppClick = { appId ->
                    navController.navigate(NavRoutes.AppDetails(id = appId))
                }
            )
        }
    }
}