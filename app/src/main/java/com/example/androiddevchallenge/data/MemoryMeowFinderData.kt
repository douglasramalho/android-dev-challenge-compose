package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.PetForAdoption
import com.example.androiddevchallenge.data.model.GenderType
import com.example.androiddevchallenge.data.model.Rescuer
import com.example.androiddevchallenge.data.model.SizeType

object MemoryMeowFinderData {
    val catsForAdoption = listOf(
        PetForAdoption(
            id = 1,
            name = "Blue",
            age = 2f,
            size = SizeType.LARGE,
            gender = GenderType.Female,
            breed = "Europeu",
            address = "R Poço Lote 91, Santo António - 9545-429",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam et felis porta, imperdiet ex vel, tincidunt nisi. Nam quis tortor eleifend, imperdiet ipsum vitae, facilisis mi. Phasellus ac purus et felis feugiat convallis eu quis sem. Proin et varius urna. Quisque vel rhoncus quam. Donec non quam scelerisque, dictum felis.",
            rescuer = Rescuer(
                name = "Douglas"
            ),
            photo = R.drawable.cat_blue
        ),
        PetForAdoption(
            id = 2,
            name = "Willow",
            age = 0.6f,
            size = SizeType.SMALL,
            gender = GenderType.Female,
            breed = "Siamês",
            address = "R Gago Coutinho 60, Esmoriz - 3885-447",
            about = "orem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ligula ipsum, elementum a nunc et, dignissim placerat justo. Donec sit amet pulvinar dolor, quis laoreet nisi. Mauris sollicitudin imperdiet est sed accumsan. Vivamus non imperdiet est. Nullam ut metus vel massa cursus iaculis. Sed posuere, augue in semper bibendum, lectus enim consequat eros, ac condimentum mauris mauris a elit. Vestibulum.",
            rescuer = Rescuer(
                name = "Raissa"
            ),
            photo = R.drawable.cat_willow
        ),
        PetForAdoption(
            id = 3,
            name = "Arya",
            age = 3f,
            size = SizeType.LARGE,
            gender = GenderType.Female,
            breed = "Angorá",
            address = "Rua Igreja 56, Pereira - 4755-404",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce commodo, eros nec faucibus vulputate, lectus ex semper ligula, id tincidunt ipsum mauris et mi. Donec a ex id urna malesuada malesuada. Fusce vitae mi sit amet ex accumsan vestibulum. Nunc.",
            rescuer = Rescuer(
                name = "Douglas"
            ),
            photo = R.drawable.cat_arya
        ),
        PetForAdoption(
            id = 4,
            name = "Oliver",
            age = 3f,
            size = SizeType.LARGE,
            gender = GenderType.Male,
            breed = "Asian Semi-longhair",
            address = "R Jardim 56, Sobrado - 4440-409",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur dolor lorem, ullamcorper sed quam eu, pharetra semper dolor. Integer hendrerit sed nisl et accumsan. Proin non sapien ut tortor consequat dapibus. Nulla gravida condimentum orci at tempus. Maecenas egestas dolor dignissim, rhoncus quam a, convallis dolor. Vivamus consectetur enim velit, sed tincidunt lacus condimentum mattis. Vestibulum lobortis, dui nec accumsan.",
            rescuer = Rescuer(
                name = "Francis"
            ),
            photo = R.drawable.cat_oliver
        ),
        PetForAdoption(
            id = 5,
            name = "Luna",
            age = 1.5f,
            size = SizeType.MEDIUM,
            gender = GenderType.Female,
            breed = "Birman",
            address = "Rua Alegria 37, Duas Igrejas - 4580-372",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque finibus ex eu diam fermentum lobortis. In eu ultricies tellus. Praesent ut nibh quis sem commodo maximus rutrum id diam. Praesent convallis vehicula dui lobortis malesuada. Integer nec arcu in odio aliquam finibus. Donec vel mi sit amet nulla consequat ullamcorper. Nam mollis lobortis imperdiet. Proin.",
            rescuer = Rescuer(
                name = "Kenneth"
            ),
            photo = R.drawable.cat_luna
        ),
        PetForAdoption(
            id = 6,
            name = "Hunter",
            age = 0.5f,
            size = SizeType.SMALL,
            gender = GenderType.Male,
            breed = "Bengal",
            address = "Avenida República 21, Lisboa - 1800-281",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eu massa dictum lacus lacinia sollicitudin non non ex. Ut condimentum suscipit nisi, non tempor odio. Etiam euismod tellus id lacus tincidunt iaculis. Morbi at tellus commodo tortor interdum vulputate. Aenean quis pulvinar ex. Donec elit eros, laoreet ac molestie varius, volutpat ac leo. Sed ut nunc a nunc dictum gravida. In porta nec tortor ut commodo. Quisque porttitor, erat condimentum pulvinar condimentum, lacus leo ultricies tellus, in iaculis neque eros.",
            rescuer = Rescuer(
                name = "Stuart"
            ),
            photo = R.drawable.cat_hunter
        ),
    )
}