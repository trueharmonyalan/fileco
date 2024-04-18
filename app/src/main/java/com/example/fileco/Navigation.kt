package com.example.fileco

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationForFileCo() {
    val navController = rememberNavController()

    val sharedViewModel: datasharemodel = viewModel()


    NavHost(
        navController = navController,
        startDestination = "Home screen",
    ){

        composable(route="Home screen"){
            HomeUi(navController)
        }

        composable(route="File Compression"){
            WindowFileCompression(navController)
        }

        composable(route="Audio Compressor"){
            WindowAudioCompression(navController)
        }

        composable(route="Video Compressor"){
            WindowVideoCompression(navController, sharedViewModel = sharedViewModel)
        }
        composable(route="Image Compressor"){
            WindowImageCompression(navController)
        }
        composable(route="done"){
            FinalWindowPage(navController, sharedViewModel = sharedViewModel)
        }




    }

}


