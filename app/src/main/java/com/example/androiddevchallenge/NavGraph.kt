/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
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
