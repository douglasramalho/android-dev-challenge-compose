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
package com.example.androiddevchallenge.ui.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.PetForAdoption
import com.example.androiddevchallenge.data.model.ageStringResource
import com.example.androiddevchallenge.data.repository.MemoryDataSource
import com.example.androiddevchallenge.data.repository.MeowRepositoryImpl
import com.example.androiddevchallenge.ui.base.BaseScreen
import com.example.androiddevchallenge.ui.base.ContentLoading
import com.example.androiddevchallenge.ui.strValue
import com.example.androiddevchallenge.ui.strValueArgs
import com.example.androiddevchallenge.ui.theme.blue700
import com.example.androiddevchallenge.ui.theme.orange900
import com.example.androiddevchallenge.ui.theme.white50
import com.example.androiddevchallenge.ui.theme.whiteAlpha

@Composable
fun DetailScreen(
    navController: NavController,
    petId: Int,
    darkTheme: Boolean = false
) {
    BaseScreen(navController = navController, title = "Detail", darkTheme = darkTheme) {
        val viewModel: DetailViewModel = viewModel(
            key = "detailViewModel",
            factory = DetailViewModel.DetailViewModelFactory(
                MeowRepositoryImpl(MemoryDataSource())
            )
        )

        val detailState by viewModel.detailState.observeAsState()

        when (detailState) {
            DetailViewModel.DetailState.Loading -> ContentLoading()
            is DetailViewModel.DetailState.Success -> {
                val petDetail = (detailState as DetailViewModel.DetailState.Success).pet
                DetailContent(petDetail = petDetail)
            }
            is DetailViewModel.DetailState.Error -> {
                // TODO: handle error state
            }
        }

        viewModel.findPetForAdoption(petId)
    }
}

@Composable
private fun DetailContent(petDetail: PetForAdoption) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState(), true)
    ) {
        val (detailContainer, buttonAdoptMe) = createRefs()

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .constrainAs(detailContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(buttonAdoptMe.top)
                }
        ) {
            val (photo, surface, favorite) = createRefs()

            Image(
                painter = painterResource(id = petDetail.photo),
                contentDescription = R.string.description_pet_photo.strValueArgs(
                    petDetail.name,
                    petDetail.breed
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp, 8.dp))
                    .constrainAs(photo) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.FillWidth
            )

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .constrainAs(surface) {
                        top.linkTo(photo.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                ConstraintLayout(
                    modifier = Modifier.padding(8.dp)
                ) {
                    val (name, breed, boxAge, boxLocation, divider1, divider2, textAbout) = createRefs()

                    Text(
                        text = petDetail.name,
                        color = orange900,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .constrainAs(name) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                            },
                    )

                    Text(
                        text = petDetail.breed,
                        color = blue700,
                        modifier = Modifier
                            .constrainAs(breed) {
                                start.linkTo(name.start)
                                top.linkTo(name.bottom)
                            },
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .constrainAs(boxAge) {
                                start.linkTo(parent.start)
                                top.linkTo(breed.bottom)
                            }
                    ) {
                        BoxInfo(R.string.label_age.strValue(), petDetail.ageStringResource())
                        Spacer(modifier = Modifier.size(8.dp))
                        BoxInfo(
                            R.string.label_sex.strValue(),
                            petDetail.gender.stringResId.strValue()
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        BoxInfo(
                            R.string.label_size.strValue(),
                            petDetail.size.stringResId.strValue()
                        )
                    }

                    Divider(
                        color = white50,
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .constrainAs(divider1) {
                                start.linkTo(parent.start)
                                top.linkTo(boxAge.bottom)
                                end.linkTo(parent.end)
                            }
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .constrainAs(boxLocation) {
                                start.linkTo(parent.start)
                                top.linkTo(boxAge.bottom)
                                end.linkTo(parent.end)
                            }
                    ) {
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.ic_location),
                                contentDescription = R.string.description_pet_address.strValueArgs(
                                    petDetail.address
                                ),
                                modifier = Modifier.size(18.dp)
                            )
                            Text(text = petDetail.address)
                        }
                    }

                    Divider(
                        color = white50,
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .constrainAs(divider2) {
                                start.linkTo(parent.start)
                                top.linkTo(boxLocation.bottom)
                                end.linkTo(parent.end)
                            }
                    )

                    Text(
                        text = petDetail.about,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .constrainAs(textAbout) {
                                start.linkTo(parent.start)
                                top.linkTo(divider2.bottom)
                                end.linkTo(parent.end)
                            }
                    )
                }
            }

            val favoriteIconInitialState = if (petDetail.isFavorite) {
                R.drawable.ic_favorite_filled
            } else R.drawable.ic_favorite_borded

            var favoriteIcon by rememberSaveable { mutableStateOf(favoriteIconInitialState) }

            IconButton(
                onClick = {
                    favoriteIcon = if (petDetail.isFavorite) {
                        R.drawable.ic_favorite_borded
                    } else R.drawable.ic_favorite_filled

                    petDetail.isFavorite = !petDetail.isFavorite
                },
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(favorite) {
                        top.linkTo(photo.bottom)
                        end.linkTo(parent.end)
                    }
            ) {
                Image(
                    painter = painterResource(id = favoriteIcon),
                    contentDescription = R.string.description_pet_favorite_button.strValueArgs(
                        petDetail.name
                    ),
                    contentScale = ContentScale.FillWidth,
                )
            }
        }

        val context = LocalContext.current.applicationContext
        Button(
            onClick = {
                Toast.makeText(context, "Thanks for adopt me!", Toast.LENGTH_LONG).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .constrainAs(buttonAdoptMe) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Text(text = R.string.button_adopt_me.strValue())
        }
    }
}

@Composable
private fun BoxInfo(title: String, value: String) {
    Surface(modifier = Modifier.clip(RoundedCornerShape(16.dp))) {
        Column(
            modifier = Modifier
                .background(whiteAlpha)
                .padding(8.dp)
        ) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = value, color = blue700)
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LightPreview() {
    DetailScreen(rememberNavController(), 1)
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun DarkPreview() {
    DetailScreen(rememberNavController(), 1, true)
}
