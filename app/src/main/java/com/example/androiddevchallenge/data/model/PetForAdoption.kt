package com.example.androiddevchallenge.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.androiddevchallenge.R

data class PetForAdoption(
    val id: Int,
    val name: String,
    val age: Float,
    val gender: GenderType,
    val size: SizeType,
    val breed: String,
    val address: String,
    val about: String,
    val rescuer: Rescuer,
    @DrawableRes
    val photo: Int,
    var isFavorite: Boolean = false
)

enum class SizeType(@StringRes val stringResId: Int) {
    SMALL(R.string.size_small),
    MEDIUM(R.string.size_medium),
    LARGE(R.string.size_large)
}

sealed class GenderType(@StringRes val stringResId: Int) {
    object Female : GenderType(R.string.gender_female)
    object Male : GenderType(R.string.gender_male)
}

@Composable
fun PetForAdoption.ageStringResource(): String {
    return if (age < 1) {
        val age = age.toString().split(".")[1]
        LocalContext.current.resources.getQuantityString(
            R.plurals.age_month,
            age.toInt(),
            age
        )
    } else {
        val age = age.toString().split(".")[0]
        LocalContext.current.resources.getQuantityString(
            R.plurals.age_year,
            age.toInt(),
            age
        )
    }
}