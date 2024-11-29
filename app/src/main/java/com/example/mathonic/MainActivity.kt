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
//        viewModel.addLevel(arrayOf(12,3,4,5), 24)
        setContent {
            MathonicTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.StartPage, builder = {
                    composable(Routes.GameBox + "/{n1}/{n2}/{n3}/{n4}/{res}") { backStackEntry ->
                        val n1 = backStackEntry.arguments?.getString("n1")?.toIntOrNull() ?: 0
                        val n2 = backStackEntry.arguments?.getString("n2")?.toIntOrNull() ?: 0
                        val n3 = backStackEntry.arguments?.getString("n3")?.toIntOrNull() ?: 0
                        val n4 = backStackEntry.arguments?.getString("n4")?.toIntOrNull() ?: 0
                        val res = backStackEntry.arguments?.getString("res")?.toIntOrNull() ?: 0
                        GameBox(n1, n2, n3, n4, res)
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
