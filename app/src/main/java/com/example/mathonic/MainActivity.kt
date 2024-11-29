package com.example.mathonic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mathonic.ui.theme.MathonicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MathonicViewModel::class.java]
//        viewModel.clearLevel()
//        viewModel.addLevel(arrayOf(12,2,3,8), 24)
//        viewModel.addLevel(arrayOf(10, 1, 5, 4), 18) // 10-1 * 5+4
//        viewModel.addLevel(arrayOf(12,3,4,5), 24)
//        viewModel.addLevel(arrayOf(7, 3, 2, 6), 30) // (7 + 3) * (6 / 2)
//        viewModel.addLevel(arrayOf(9, 4, 3, 1), 16) // ((9 - 4) * 3) + 1
//        viewModel.addLevel(arrayOf(15, 5, 2, 3), 35) // ((15 / 3) + 2) * 5
//        viewModel.addLevel(arrayOf(20, 4, 6, 1), 36)// ((20 / 4) + 1) * 6 = 36
//        viewModel.addLevel(arrayOf(11, 8, 2, 1), 19) // 11 + 8 / 2 - 1
//        viewModel.addLevel(arrayOf(17, 6, 2, 4), 46) // 17 + 6 * 4 / 2
//        viewModel.addLevel(arrayOf(20, 8, 3, 5), 125) // 20 + 5 * 8 - 3
        setContent {
            MathonicTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.StartPage, builder = {
                    composable(Routes.GameBox + "/{n1}/{n2}/{n3}/{n4}/{res}/{id}") { backStackEntry ->
                        val n1 = backStackEntry.arguments?.getString("n1")?.toIntOrNull() ?: 0
                        val n2 = backStackEntry.arguments?.getString("n2")?.toIntOrNull() ?: 0
                        val n3 = backStackEntry.arguments?.getString("n3")?.toIntOrNull() ?: 0
                        val n4 = backStackEntry.arguments?.getString("n4")?.toIntOrNull() ?: 0
                        val res = backStackEntry.arguments?.getString("res")?.toIntOrNull() ?: 0
                        val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
                        GameBox(n1, n2, n3, n4, res, id)
                    }
                    composable(Routes.StartPage){
                        StartPage(navController)
                    }
                    composable(Routes.LevelPage){
                        LevelPage(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                })
            }
        }
    }
}
