package com.example.fileco

import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

import com.example.fileco.ui.theme.FilecoTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this));
        }
        setContent {
            FilecoTheme {

                NavigationForFileCo()

            }
        }
    }
}



