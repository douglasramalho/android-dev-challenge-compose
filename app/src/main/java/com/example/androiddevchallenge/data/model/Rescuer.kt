package com.example.androiddevchallenge.data.model

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

data class Rescuer(
    val name: String,
    @DrawableRes
    val photo: Int = R.drawable.ic_rescuer
)
