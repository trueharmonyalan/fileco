/*
package com.example.fileco


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

val buttonStrokeColor = Color(0xFF9DB2BF)
/*@Composable
fun CustomButton() {



    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .padding(30.dp)
            .offset(10.dp, 200.dp)


    ) {
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




            ){




                Text(
                    modifier = Modifier
                        .offset(x=100.dp,y=15.dp),
                    text ="File Compression",
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = (-1).sp,
                    fontWeight = FontWeight.Medium,



                    )
                //logo for file compression
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
*/

@Composable
fun CustomButton1(navController: NavHostController) {
    val buttonStrokeColor = Color(0xFF9DB2BF)


    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .padding(30.dp)
            .offset(10.dp, 200.dp)


    ) {
        // Button One is inside this Container
        Column(

            modifier = Modifier
                .offset(5.dp, 20.dp),


            ) {
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



            ) {


                Text(
                    modifier = Modifier
                        .offset(x = 100.dp, y = 15.dp),
                    text = "File Compression",
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = (-1).sp,
                    fontWeight = FontWeight.Medium,


                    )
                //logo for file compression
                Column(
                    modifier = Modifier
                        .offset(x = 27.dp, y = (-16).dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background(
                                color = Color(android.graphics.Color.parseColor("#DDE6ED")),
                                shape = RoundedCornerShape(5.dp)
                            )


                    ) {

                        // Logo Implemented in Rounded Rectangle for file compression
                        Image(
                            painter = painterResource(id = R.drawable.article_fill0_wght400_grad0_opsz24),
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

@Composable
fun CustomButton2(navController: NavHostController) {
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

}


@Composable
fun CustomButton3(navController: NavHostController) {
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
}

@Composable
fun CustomButton4(navController: NavHostController) {
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







*/