package com.example.fileco

import android.os.Bundle
import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent

import com.example.fileco.ui.theme.FilecoTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilecoTheme {
                NavigationForFileCo()

            }
        }
    }
}



