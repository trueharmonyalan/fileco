package com.example.fileco

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val buttonStrokeColor = Color(0xFF9DB2BF)
@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeUi(navController: NavHostController) {

   // val fontColor = Color(0xFF526D82)



        Column(

            modifier = Modifier
                .background(color = Color(android.graphics.Color.parseColor("#27374D")))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center



        ) {


            Row(
                modifier = Modifier
                    .height(30.dp)
                    .width(70.dp)
                    .offset(x=-140.dp,y = -200.dp)
                    .background(color = Color(android.graphics.Color.parseColor("#DDE6ED")), shape = RoundedCornerShape(25.dp))
            ) {
                    Text(
                        text = "FileCo",
                        modifier = Modifier
                            .offset(x=10.dp,y=5.dp),
                        color = Color(android.graphics.Color.parseColor("#526D82")),
                        fontWeight = FontWeight.Medium

                    )
            }








            // Button One is inside this Container
            Column(

                modifier = Modifier
                    .offset(5.dp, 20.dp),


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
                        .border(3.dp, color = buttonStrokeColor, shape = RoundedCornerShape(60.dp))
                        .clickable {
                            navController.navigate("File Compression")
                        }




                ){




                    Text(
                        modifier = Modifier
                            .offset(x=100.dp,y=15.dp),
                        text ="PDF Compression",
                        fontSize = 25.sp,
                        color = Color.White,
                        letterSpacing = (-1).sp,
                        fontWeight = FontWeight.Medium,



                        )
                    //logo for file compression
                    Row(
                        modifier = Modifier
                            .offset(x=27.dp,y= (-16).dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .background(
                                    color = Color(android.graphics.Color.parseColor("#DDE6ED")),
                                    shape = RoundedCornerShape(5.dp)
                                )







                        ){

                            // Logo Implemented in Rounded Rectangle for file compression
                            Image(painter = painterResource(id = R.drawable.article_fill0_wght400_grad0_opsz24),
                                contentDescription = "Image of Image compression",
                                modifier = Modifier
                                    .offset(x = 5.dp, y = (0).dp)
                                    .size(40.dp)

                            )


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
                        .border(3.dp, color = buttonStrokeColor, shape = RoundedCornerShape(60.dp))
                        .clickable {
                            navController.navigate("Audio Compressor")
                        }


                ){
                    Text(
                        text ="Audio Compression",
                        fontSize = 25.sp,
                        color = Color.White,
                        letterSpacing = (-1).sp,
                        fontWeight = FontWeight.Medium,

                        modifier = Modifier
                            .offset(x=100.dp,y=16.dp)

                    )
                    //logo for Audio Compression
                    Column(
                        modifier = Modifier
                            .offset(x=27.dp,y= (-16).dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .background(
                                    color = Color(android.graphics.Color.parseColor("#DDE6ED")),
                                    shape = RoundedCornerShape(5.dp)
                                )






                        ){
                            // Logo Implemented in Rounded Rectangle for Audio Compression
                            Image(painter = painterResource(id = R.drawable.graphic_eq_fill0_wght400_grad0_opsz24),
                                contentDescription = "Image of Image compression",
                                modifier = Modifier
                                    .offset(x = 5.dp, y = (0).dp)
                                    .size(40.dp)

                            )


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
                        .border(3.dp, color = buttonStrokeColor, shape = RoundedCornerShape(60.dp))
                        .clickable {
                            navController.navigate("Video Compressor")
                        }


                ){
                    Text(
                        text ="Video Compression",
                        fontSize = 25.sp,
                        color = Color.White,
                        letterSpacing = (-1).sp,
                        fontWeight = FontWeight.Medium,

                        modifier = Modifier
                            .offset(x=100.dp,y=16.dp)

                    )
                    //logo for Video Compression
                    Column(
                        modifier = Modifier
                            .offset(x=27.dp,y= (-16).dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .background(
                                    color = Color(android.graphics.Color.parseColor("#DDE6ED")),
                                    shape = RoundedCornerShape(5.dp)
                                )






                        ){
                            // Logo Implemented in Rounded Rectangle for Video Compression
                            Image(painter = painterResource(id = R.drawable.video_library_fill0_wght400_grad0_opsz24),
                                contentDescription = "Image of Image compression",
                                modifier = Modifier
                                    .offset(x = 5.dp, y = (0).dp)
                                    .size(35.dp)

                            )

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
                        .border(3.dp, color = buttonStrokeColor, shape = RoundedCornerShape(60.dp))
                        .clickable {
                            navController.navigate("Image Compressor")
                        }


                ){
                    Text(
                        text ="Image Compression",
                        fontSize = 25.sp,
                        color = Color.White,
                        letterSpacing = (-1).sp,
                        fontWeight = FontWeight.Medium,

                        modifier = Modifier
                            .offset(x=100.dp,y=16.dp)

                    )
                    //logo for Image Compression
                    Column(
                        modifier = Modifier
                            .offset(x=27.dp,y= (-16).dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .background(
                                    color = Color(android.graphics.Color.parseColor("#DDE6ED")),
                                    shape = RoundedCornerShape(5.dp)
                                )






                        ){
                            // Logo Implemented in Rounded Rectangle for Image Compression
                            Image(painter = painterResource(id = R.drawable.image_fill0_wght400_grad0_opsz24),
                                contentDescription = "Image of Image compression",
                                modifier = Modifier
                                    .offset(x = 5.dp, y = (0).dp)
                                    .size(40.dp)

                            )

                        }

                    }
                }



            }
        }




    }







@Preview
@Composable
private fun nice() {
    val navController = rememberNavController()
    HomeUi(navController)
    
}
