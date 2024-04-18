package com.example.fileco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import coil.request.videoFrameMillis
import com.example.fileco.ui.theme.FilecoTheme
import java.io.File

class FinalWindow: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FilecoTheme {

                FinalWindowPage()
            }
        }

    }


    @Composable
    fun FinalWindowPage() {

        val outputVideo by remember {
            mutableStateOf<File?>(null)
        }



        Box(

            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(android.graphics.Color.parseColor("#27374D")))
                .padding(5.dp)
                .offset(x = 30.dp),


            ) {

//logo for file compression
            Column(
                modifier = Modifier
                    .offset(x = 30.dp, y = (60).dp),
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
                        painter = painterResource(id = R.drawable.image_fill0_wght400_grad0_opsz24),
                        contentDescription = "Image of Image compression",
                        modifier = Modifier
                            .offset(x = 5.dp, y = (5).dp)
                            .size(40.dp)

                    )


                }

            }
            //rounded rectangle position
            Column(
                modifier = Modifier


                    .offset(40.dp, 200.dp)
                    .padding(0.dp)
            ) {

                //rounded rectangle configuration

                Column(
                    modifier = Modifier
                        .height(296.dp)
                        .width(296.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#17273e")),
                            shape = RoundedCornerShape(15.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    val model = ImageRequest.Builder(LocalContext.current)
                        .data(outputVideo)
                        .videoFrameMillis(10000)
                        .decoderFactory { result, options, _ ->
                            VideoFrameDecoder(
                                result.source,
                                options
                            )
                        }
                        .build()


                    AsyncImage(model = model, contentDescription = null)


                }

            }


        }

    }
}
