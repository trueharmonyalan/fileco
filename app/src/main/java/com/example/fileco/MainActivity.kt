package com.example.fileco

import android.os.Bundle
import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.example.fileco.ui.theme.FilecoTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilecoTheme {
                WindowFileCompression()

            }
        }
    }
}



@Preview
@Composable
 fun ButtonPrev(
) {
     Column(modifier = Modifier.fillMaxSize()) {
         CustomButton()
     }


}