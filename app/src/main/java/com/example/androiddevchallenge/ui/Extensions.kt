package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun Int.strValue(): String {
    return stringResource(id = this)
}

@Composable
fun Int.strValueArgs(vararg formatArgs: Any): String {
    return stringResource(id = this, formatArgs)
}