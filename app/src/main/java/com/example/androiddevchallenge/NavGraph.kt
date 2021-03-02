package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.androiddevchallenge.ui.detail.DetailScreen
import com.example.androiddevchallenge.ui.home.HomeScreen

object Routes {
    const val HOME_ROUTE = "home"
    const val DETAIL_ROUTE = "detail/{petId}"
}

@Composable
fun NavGraph(startDestination: String = Routes.HOME_ROUTE) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.HOME_ROUTE) {
            HomeScreen(navController) { petId ->
                navController.navigate("detail/$petId") {
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
        composable(
            Routes.DETAIL_ROUTE,
            arguments = listOf(navArgument("petId") { type = NavType.IntType })
        ) { backStackEntry ->

            DetailScreen(navController, backStackEntry.arguments?.getInt("petId") ?: 0)
        }
    }
}