package com.example.fileco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.R
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fileco.ui.theme.FilecoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilecoTheme {
                homeUi()
            }
        }
    }
}

@Composable
fun homeUi() {

    val fontColor = Color(0xFF526D82)
        Box(

        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor("#27374D")))
            .offset(x = 1.dp, y = 24.dp)

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(color = Color(android.graphics.Color.parseColor("#DDE6ED")))



        ){
            Text(
                text = "Compressor",
                color = fontColor,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .offset(20.dp,4.dp)


            )
        }

    }



}