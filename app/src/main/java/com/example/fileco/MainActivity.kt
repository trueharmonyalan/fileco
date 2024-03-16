package com.example.fileco

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.R
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
                HomeUi()
                CustomButton()
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeUi() {

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

@Composable
fun CustomButton() {
    val ButtonStorkeColor = Color(0xFF9DB2BF)

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .padding(30.dp)
            .offset(0.dp, 150.dp)


    ) {
        // Button One is inside this Container
        Column(

            modifier = Modifier
                .offset(5.dp, 20.dp)

        ){
            //button design for Button One
            Column(
                modifier = Modifier
                    .height(68.dp)
                    .width(321.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#526D82")),
                        shape = RoundedCornerShape(60.dp)
                    )
                    .border(3.dp, color = ButtonStorkeColor, shape = RoundedCornerShape(60.dp))



            ){




                Text(
                    modifier = Modifier
                        .offset(x=100.dp,y=15.dp),
                    text ="File Compression",
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = -1.sp,
                    fontWeight = FontWeight.Medium,



                )
                    //logo for file compression
                Column(
                    modifier = Modifier
                        .offset(x=27.dp,y=-16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background( color = Color(android.graphics.Color.parseColor("#DDE6ED")),
                                shape = RoundedCornerShape(5.dp)
                            )






                    ){

                    }

                }

            }



        }

        // Button Two is inside this Container
        Column(

            modifier = Modifier
                .offset(5.dp, 40.dp)

        ){
            //button design for Button TWo
            Column(
                modifier = Modifier
                    .height(68.dp)
                    .width(321.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#526D82")),
                        shape = RoundedCornerShape(60.dp)
                    )
                    .border(3.dp, color = ButtonStorkeColor, shape = RoundedCornerShape(60.dp))


            ){
                Text(
                    text ="Audio Compression",
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = -1.sp,
                    fontWeight = FontWeight.Medium,

                    modifier = Modifier
                        .offset(x=100.dp,y=16.dp)

                )
                //logo for Audio Compression
                Column(
                    modifier = Modifier
                        .offset(x=27.dp,y=-16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background( color = Color(android.graphics.Color.parseColor("#DDE6ED")),
                                shape = RoundedCornerShape(5.dp)
                            )






                    ){

                    }

                }

            }



        }

        // Button Three is inside this Container
        Column(

            modifier = Modifier
                .offset(5.dp, 60.dp)

        ){
            //button design for Button Three
            Column(
                modifier = Modifier
                    .height(68.dp)
                    .width(321.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#526D82")),
                        shape = RoundedCornerShape(60.dp)
                    )
                    .border(3.dp, color = ButtonStorkeColor, shape = RoundedCornerShape(60.dp))


            ){
                Text(
                    text ="Video Compression",
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = -1.sp,
                    fontWeight = FontWeight.Medium,

                    modifier = Modifier
                        .offset(x=100.dp,y=16.dp)

                )
                //logo for Video Compression
                Column(
                    modifier = Modifier
                        .offset(x=27.dp,y=-16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background( color = Color(android.graphics.Color.parseColor("#DDE6ED")),
                                shape = RoundedCornerShape(5.dp)
                            )






                    ){

                    }

                }
            }



        }

        // Button Four is inside this Container
        Column(

            modifier = Modifier
                .offset(5.dp, 80.dp)

        ){
            //button design for Button Four
            Column(
                modifier = Modifier
                    .height(68.dp)
                    .width(321.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#526D82")),
                        shape = RoundedCornerShape(60.dp)
                    )
                    .border(3.dp, color = ButtonStorkeColor, shape = RoundedCornerShape(60.dp))


            ){
                Text(
                    text ="Image Compression",
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = -1.sp,
                    fontWeight = FontWeight.Medium,

                    modifier = Modifier
                        .offset(x=100.dp,y=16.dp)

                )
                //logo for Image Compression
                Column(
                    modifier = Modifier
                        .offset(x=27.dp,y=-16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background( color = Color(android.graphics.Color.parseColor("#DDE6ED")),
                                shape = RoundedCornerShape(5.dp)
                            )






                    ){

                    }

                }
            }



        }
    }


}

@Preview
@Composable
private fun buttonPrev() {
    CustomButton()

}