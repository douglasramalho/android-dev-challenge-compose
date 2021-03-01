package com.example.androiddevchallenge.ui.base

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun BaseScreen(
    navController: NavController,
    title: String,
    darkTheme: Boolean,
    body: @Composable () -> Unit
) {
    MyTheme(navController = navController, title = title, darkTheme = darkTheme) {
        body()
    }
}