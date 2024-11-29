package com.example.mathonic

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameBox(n1 : Int, n2 : Int, n3 : Int, n4 : Int, res : Int) {
    val num1 : MutableState<Int> = remember { mutableStateOf(n1) }
    val num2 : MutableState<Int> = remember { mutableStateOf(n2) }
    val num3 : MutableState<Int> = remember { mutableStateOf(n3) }
    val num4 : MutableState<Int> = remember { mutableStateOf(n4) }
    val result : MutableState<Int> = remember { mutableStateOf(res) }
    val someData : MutableState<Int> = remember { mutableStateOf(0) }
    val doneCards = remember { mutableListOf<String>() }
    val selectedCards = remember { mutableStateListOf<Pair<String,String>>() }
    val opDone = remember { mutableListOf<Pair<Pair<String, Int>, Pair<String, Int>>>() }

    fun doOperation()  :Int {
        val n1 = selectedCards[0].first.toInt()
        val n2 = selectedCards[2].first.toInt()
        val op = selectedCards[1].first
        var re = 0
        when (op) {
            "+" -> re = n1 + n2
            "-" -> re = n1 - n2
            "x" -> re = n1 * n2
            "÷" -> re = n1 / n2
        }
        return re
    }

    val context = LocalContext.current
    fun undotask () {
        if (opDone.isNotEmpty()) {
            val lastTask = opDone.removeAt(opDone.size - 1)
            val (card1, number1) = lastTask.first
            val (card2, number2) = lastTask.second

            fun setCardValue(card: String, number: Int) {
                when (card) {
                    "Card1" -> num1.value = number
                    "Card2" -> num2.value = number
                    "Card3" -> num3.value = number
                    "Card4" -> num4.value = number
                }
            }

            setCardValue(card1, number1)
            setCardValue(card2, number2)
            if (doneCards.contains(card1)){
                doneCards.remove(card1)
            }
            else if (doneCards.contains(card2)){
                doneCards.remove(card2)
            }
            Toast.makeText(context, "Undo Clicked", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "No Element in Undo Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                CardItem(num1.value, selectedCards, "Card1"){
                    if(selectedCards.any { it.second == "Card1" }){
                        selectedCards.removeAll{ it.second == "Card1" }
                    }
                    else {
                        if (selectedCards.size < 3) {
                            if (selectedCards.size == 0 || selectedCards.size == 2){
                                selectedCards.add(Pair("${num1.value}", "Card1"))
                            }
                        }
                    }
                }
                CardItem(num2.value, selectedCards, "Card2"){
                    if(selectedCards.any { it.second == "Card2" }){
                        selectedCards.removeAll{ it.second == "Card2" }
                    }
                    else{
                        if (selectedCards.size < 3) {
                            if (selectedCards.size == 0 || selectedCards.size == 2){
                                selectedCards.add(Pair("${num2.value}", "Card2"))
                            }
                        }
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                CardItem(num3.value, selectedCards, "Card3"){
                    if(selectedCards.any { it.second == "Card3" }){
                        selectedCards.removeAll{ it.second == "Card3" }
                    }
                    else {
                        if (selectedCards.size < 3) {
                            if (selectedCards.size == 0 || selectedCards.size == 2){
                                selectedCards.add(Pair("${num3.value}", "Card3"))
                            }
                        }
                    }
                }
                CardItem(num4.value, selectedCards, "Card4"){
                    if(selectedCards.any { it.second == "Card4" }){
                        selectedCards.removeAll{ it.second == "Card4" }
                    }
                    else{
                        if (selectedCards.size < 3) {
                            if (selectedCards.size == 0 || selectedCards.size == 2){
                                selectedCards.add(Pair("${num4.value}", "Card4"))
                            }
                        }
                    }
                }
            }
        }

        // Center overlapping card
        Card(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(50),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.elevatedCardElevation(8.dp)
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(text = "${result.value}", style = MaterialTheme.typography.bodyLarge, color = Color.Black)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Addition button
            Button(
                onClick = {
                    if(selectedCards.any { it.second == "Add" }){
                        selectedCards.removeAll{ it.second == "Add" }
                    }
                    else {
                        if (selectedCards.size < 3) {
                            if (selectedCards.size == 1) {
                                selectedCards.add(Pair("+","Add"))
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedCards.any { it.second == "Add" }) Color.LightGray else Color.Gray
                ),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(16.dp),

                ) {
                Text(text = "+", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))
            }

            // Subtraction button
            Button(
                onClick = {
                    if(selectedCards.any { it.second == "Sub" }){
                        selectedCards.removeAll{ it.second == "Sub" }
                    }
                    else {
                        if (selectedCards.size < 3) {
                            if (selectedCards.size == 1) {
                                selectedCards.add(Pair("-","Sub"))
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedCards.any { it.second == "Sub" }) Color.LightGray else Color.Gray
                ),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(text = "-", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))
            }

            Button(
                onClick = {
                    if(selectedCards.any { it.second == "Mul" }){
                        selectedCards.removeAll{ it.second == "Mul" }
                    }
                    else {
                        if (selectedCards.size < 3) {
                            if (selectedCards.size == 1) {
                                selectedCards.add(Pair("x","Mul"))
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedCards.any { it.second == "Mul" }) Color.LightGray else Color.Gray
                ),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(text = "×", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))
            }

            // Division button
            Button(
                onClick = {
                    if(selectedCards.any { it.second == "Div" }){
                        selectedCards.removeAll{ it.second == "Div" }
                    }
                    else {
                        if (selectedCards.size < 3) {
                            if (selectedCards.size == 1) {
                                selectedCards.add(Pair("÷","Div"))
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedCards.any { it.second == "Div" }) Color.LightGray else Color.Gray
                ),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(text = "÷", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))
            }

            Button(
                onClick = {
                    undotask()
                },
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(text = "↶", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
            }


        }
    }
    LaunchedEffect(selectedCards.size) {
        if (selectedCards.size == 3) {
            val res = doOperation()
            var undoNum1 = 0
            var undoNum2 = 0
            val cardId = selectedCards[0].second
            val cardId2 = selectedCards[2].second
            when (cardId){
                "Card1" -> {
                    undoNum1 = num1.value
                    num1.value = -1
                }
                "Card2" -> {
                    undoNum1 = num2.value
                    num2.value = -1
                }
                "Card3" -> {
                    undoNum1 = num3.value
                    num3.value = -1
                }
                "Card4" -> {
                    undoNum1 = num4.value
                    num4.value = -1
                }
            }
            when (cardId2){
                "Card1" -> {
                    undoNum2 = num1.value
                    num1.value = res
                }
                "Card2" -> {
                    undoNum2 = num2.value
                    num2.value = res
                }
                "Card3" -> {
                    undoNum2 = num3.value
                    num3.value = res
                }
                "Card4" -> {
                    undoNum2 = num4.value
                    num4.value = res
                }
            }
            opDone.add(Pair(Pair(cardId,undoNum1),Pair(cardId2, undoNum2)))
            someData.value = res
            selectedCards.clear()
            val numbers = listOf(num1.value, num2.value, num3.value, num4.value)
            val countNegatives = numbers.count { it == -1 }
            val countPositives = numbers.count { it > 0 }

            if (countNegatives == 3 && countPositives == 1) {
                if (result.value == someData.value){
                    Toast.makeText(context, "Correct Answer", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(context, "The answer is Wrong : ${someData.value}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}



@Composable
fun CardItem(value : Int, selectedCards: List<Pair<String, String>>, cardId : String,
             onClick: () -> Unit) {
    val isSelected = selectedCards.any { it.second == cardId }
    Card(
        modifier = Modifier
            .size(160.dp)
            .clickable {
                if (value != -1) onClick()
            },
        colors = CardDefaults.cardColors(
            if (isSelected) Color.Green else Color.Gray // Change color when selected
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = if (value != -1) "$value" else "", style = MaterialTheme.typography.bodyLarge, color = if (isSelected) Color.Black else Color.White)
        }
    }
}