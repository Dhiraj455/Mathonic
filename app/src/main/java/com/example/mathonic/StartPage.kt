package com.example.mathonic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun StartPage(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.android_trivia),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.image_header_height))
                    .padding(horizontal = dimensionResource(id = R.dimen.horizontal_margin)),
                alignment = Alignment.Center
            )

            // Button below the image
            Button(
                onClick = { navController.navigate(Routes.LevelPage) },
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.horizontal_margin))
            ) {
                Text(
                    text = stringResource(id = R.string.play),
                    fontSize = dimensionResource(id = R.dimen.button_text_size).value.sp,
                    color = colorResource(id = R.color.colorAccent)
                )
            }
        }
    }
}