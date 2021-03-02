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
package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.GenderType
import com.example.androiddevchallenge.data.model.PetForAdoption
import com.example.androiddevchallenge.data.model.Rescuer
import com.example.androiddevchallenge.data.model.ageStringResource
import com.example.androiddevchallenge.data.repository.MemoryDataSource
import com.example.androiddevchallenge.data.repository.MeowRepositoryImpl
import com.example.androiddevchallenge.ui.base.BaseScreen
import com.example.androiddevchallenge.ui.base.ContentLoading
import com.example.androiddevchallenge.ui.strValueArgs
import com.example.androiddevchallenge.ui.theme.orange900

@Composable
fun HomeScreen(
    navController: NavController,
    darkTheme: Boolean = false,
    petClicked: (petId: Int) -> Unit
) {
    val viewModel: HomeViewModel = viewModel(
        key = "homeViewModel",
        factory = HomeViewModel.HomeViewModelFactory(
            MeowRepositoryImpl(MemoryDataSource())
        )
    )

    BaseScreen(navController = navController, title = "Meow Finder", darkTheme = darkTheme) {
        PetsForAdoptionList(viewModel, petClicked)
    }
}

@Composable
private fun PetsForAdoptionList(viewModel: HomeViewModel, petClicked: (petId: Int) -> Unit) {
    val homeState by viewModel.homeState.observeAsState()

    Box(Modifier.fillMaxHeight()) {
        when (homeState) {
            HomeViewModel.HomeState.Loading -> ContentLoading()
            is HomeViewModel.HomeState.Success -> {
                val cats = (homeState as HomeViewModel.HomeState.Success).pets
                PetListContent(catsForAdoption = cats, petClicked = petClicked)
            }
            is HomeViewModel.HomeState.Error -> {
                // TODO: handle error state
            }
        }
    }
}

@Composable
private fun PetListContent(
    catsForAdoption: List<PetForAdoption>,
    petClicked: (petId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(catsForAdoption) { cat ->
            Card(modifier = Modifier.fillMaxSize()) {
                Column {
                    PetItemHeader(rescuer = cat.rescuer)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { petClicked(cat.id) }
                    ) {
                        PetItemContent(pet = cat)
                    }
                }
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun PetItemHeader(rescuer: Rescuer) {
    Row(modifier = Modifier.padding(8.dp)) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .wrapContentSize(Alignment.Center)
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(id = rescuer.photo),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Row {
                Text(
                    text = rescuer.name,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.W700
                )
            }
            Row {
                Text(
                    text = stringResource(id = R.string.rescuer),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun PetItemContent(pet: PetForAdoption) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        val (catGender) = createRefs()

        Image(
            painter = painterResource(id = pet.photo),
            contentDescription = R.string.description_pet_photo.strValueArgs(
                pet.name,
                pet.breed
            ),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        val imageGender = when (pet.gender) {
            GenderType.Female -> R.drawable.ic_female_background
            GenderType.Male -> R.drawable.ic_male_background
        }

        Image(
            painter = painterResource(id = imageGender),
            contentDescription = null,
            modifier = Modifier.constrainAs(catGender) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        )
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .absolutePadding(0.dp, 8.dp, 0.dp, 8.dp),
    ) {
        val (nameAge, breed) = createRefs()

        Text(
            text = "${pet.name} | ${pet.ageStringResource()}",
            color = orange900,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(nameAge) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = pet.breed,
            modifier = Modifier
                .constrainAs(breed) {
                    top.linkTo(nameAge.bottom)
                    start.linkTo(nameAge.start)
                    end.linkTo(nameAge.end)
                }
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LightPreview() {
    HomeScreen(rememberNavController(), petClicked = {})
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun DarkPreview() {
    HomeScreen(rememberNavController(), true, petClicked = {})
}
