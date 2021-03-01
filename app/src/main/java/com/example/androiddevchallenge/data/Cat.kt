package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

data class Cat(
    val name: String,
    val age: Float,
    val gender: GenderType,
    val breed: String,
    @DrawableRes
    val photo: Int = R.drawable.cat_1
)

val cats = listOf(
    Cat("Blue", 0.1f, GenderType.Female, "Domestic Short Hair"),
    Cat("Willow", 3f, GenderType.Male, "Domestic Short Hair")
)

sealed class GenderType(val stringResId: Int) {
    object Female : GenderType(R.string.gender_female)
    object Male : GenderType(R.string.gender_male)
}