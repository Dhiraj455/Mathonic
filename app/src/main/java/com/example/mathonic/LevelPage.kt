package com.example.mathonic

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelPage(viewModel: MathonicViewModel, navController: NavController) {
    val levelList = viewModel.levelList.observeAsState().value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Levels") })
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                if (levelList != null) {
                    if (levelList.isNotEmpty()) {
                        LazyColumn {
                            itemsIndexed(levelList) { index: Int, item: Levels ->
                                LevelItem(level = item){
                                    navController.navigate(Routes.GameBox + "/${item.numbers[0]}/${item.numbers[1]}/${item.numbers[2]}/${item.numbers[3]}/${item.result}")
                                }
                            }
                        }
                    } else {
                        // If the list is empty, display "No Items"
                        Text(text = "No Items")
                    }
                }
            }
        }
    )
}

@Composable
fun LevelItem(level: Levels, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth().clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Level ${level.id}")
            Text(text = "Numbers: ${level.numbers.joinToString()}")
            Text(text = "Result: ${level.result}")
        }
    }
}