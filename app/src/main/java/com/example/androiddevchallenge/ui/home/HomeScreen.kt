package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Cat
import com.example.androiddevchallenge.data.GenderType
import com.example.androiddevchallenge.data.Rescuer
import com.example.androiddevchallenge.data.cats
import com.example.androiddevchallenge.ui.base.BaseScreen
import com.example.androiddevchallenge.ui.theme.orange900
import java.text.NumberFormat

@Composable
fun HomeScreen(navController: NavController, darkTheme: Boolean = false, petClicked: () -> Unit) {
    BaseScreen(navController = navController, title = "Meow Finder", darkTheme = darkTheme) {
        CatsList(cats = cats, petClicked)
    }
}

@Composable
private fun CatsList(cats: List<Cat>, petClicked: () -> Unit) {
    Box(
        Modifier.fillMaxHeight()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(cats) { cat ->
                CatItem(cat = cat, rescuer = Rescuer("Douglas"), petClicked)
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun CatItem(cat: Cat, rescuer: Rescuer, petClicked: () -> Unit) {
    Card(modifier = Modifier.fillMaxSize()) {
        Column {
            CatItemHeader(rescuer = rescuer)
            Column(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    petClicked()
                }) {
                CatItemContent(cat = cat)
            }
        }
    }
}

@Composable
private fun CatItemHeader(rescuer: Rescuer) {
    Row(modifier = Modifier.padding(8.dp)) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .wrapContentSize(Alignment.Center)
                .clip(
                    CircleShape
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_rescue_photo_background),
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
                    text = "Rescuer",
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun CatItemContent(cat: Cat) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        val (catGender, catPhoto) = createRefs()

        Image(
            painter = painterResource(id = cat.photo),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(catPhoto) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxSize()
        )

        val imageGender = when (cat.gender) {
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

        val ageResourceValue = if (cat.age < 1) {
            val age = cat.age.toString().split(".")[1]
            LocalContext.current.resources.getQuantityString(
                R.plurals.age_month,
                age.toInt(),
                age
            )
        } else {
            val age = cat.age.toString().split(".")[0]
            LocalContext.current.resources.getQuantityString(
                R.plurals.age_year,
                age.toInt(),
                age
            )
        }

        Text(
            text = "${cat.name} | $ageResourceValue",
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
            text = cat.breed,
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