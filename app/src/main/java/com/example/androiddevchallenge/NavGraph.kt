package com.example.androiddevchallenge

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.detail.DetailScreen
import com.example.androiddevchallenge.ui.home.HomeScreen

object Routes {
    const val HOME_ROUTE = "home"
    const val DETAIL_ROUTE = "detail"
}

@Composable
fun NavGraph(startDestination: String = Routes.HOME_ROUTE) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.HOME_ROUTE) {
            HomeScreen(navController) {
                navController.navigate(Routes.DETAIL_ROUTE) {
                    // It is not working yet!
                    NavOptionsBuilder()
                        .anim {
                            enter = R.anim.slide_in_right
                            exit = R.anim.slide_out_left
                            popEnter = R.anim.slide_in_left
                            popExit = R.anim.slide_out_right
                        }
                }
            }

        }
        composable(Routes.DETAIL_ROUTE) {
            DetailScreen(navController)
        }
    }
}