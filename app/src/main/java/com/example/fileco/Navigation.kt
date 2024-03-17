package com.example.fileco

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationForFileCo() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Home screen",
    ){

        composable(route="Home screen"){
            HomeUi(navController)
        }

    }

}