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
