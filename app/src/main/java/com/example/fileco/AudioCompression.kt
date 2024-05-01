package com.example.fileco

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arthenica.ffmpegkit.FFmpegKit
import com.arthenica.ffmpegkit.FFmpegSession
import com.arthenica.ffmpegkit.ReturnCode
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WindowAudioCompression(
    navController: NavHostController,
    sharedViewModel :datasharemodel

    ) {


    val context = LocalContext.current

    var qualityReader by remember {
        mutableStateOf("")
    }
    val buttonStrokeColor = Color(0xFF9DB2BF)

    var selectedAudio by remember {
        mutableStateOf<Uri?>(null)
    }



    var selectAudioDetails by remember {
        mutableStateOf<String?>(null)
    }

    var compressedAudio by remember {
        mutableStateOf<String?>(null)
    }

    val isClicked = remember { mutableStateOf(false) }



    val AudioPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { selectedUri ->

        selectedAudio  = selectedUri

            val inputStream = selectedUri?.let { context.contentResolver.openInputStream(it) }
            val tempFile = File(context.cacheDir, "temp_audio.mp3")

            inputStream?.use { inputStream ->
                val outputStream = FileOutputStream(tempFile)
                inputStream.copyTo(outputStream)
                outputStream.flush()
                outputStream.close()
            }

// Now you can pass the temporary file path or File object to the third-party library
            val tempFilePath = tempFile.absolutePath
            println(tempFilePath)
            selectAudioDetails = tempFilePath



        }
    )





    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor("#27374D")))
            .offset(x = 30.dp)
            .padding(10.dp)

    ) {

//logo for file compression
        Column(
            modifier = Modifier
                .offset(x=30.dp,y= (60).dp)
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
                Image(painter = painterResource(id = R.drawable.graphic_eq_fill0_wght400_grad0_opsz24),
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
                    )

                    .padding(0.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                //Icon inside rounded rectangle

                Text(
                    text = "Selected Audio: ${selectedAudio}",
                    color = Color.White,

                )




            }
        }


        // Button Three is inside this Container
        Column(

            modifier = Modifier
                .offset(25.dp, 650.dp)

        ) {
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


            ) {
                Row {


                Button(
                    colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#17273e"))),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .offset(20.dp, 10.dp)
                        .border(3.dp, color = buttonStrokeColor, shape = RoundedCornerShape(60.dp)),

                    onClick = {
                        if(isClicked.value) {
                            qualityReader = "128k"
                            Toast.makeText(
                                context,
                                "Quality is set to 128k",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else{
                            Toast.makeText( context, "Select audio first",Toast.LENGTH_SHORT).show()
                        }


                    }
                )
                {
                    Text(text = "128k")

                }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#17273e"))),
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier
                            .offset(35.dp, 10.dp)
                            .border(
                                3.dp,
                                color = buttonStrokeColor,
                                shape = RoundedCornerShape(60.dp)
                            ),


                        onClick = {
                            if(isClicked.value) {
                                qualityReader = "224k"
                                Toast.makeText(
                                    context,
                                    "Quality is set to 224k",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }else{
                                Toast.makeText( context, "Select audio first",Toast.LENGTH_SHORT).show()
                            }

                        }
                    )
                    {
                        Text(text = "224k")

                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#17273e"))),
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier
                            .offset(50.dp, 10.dp)
                            .border(
                                3.dp,
                                color = buttonStrokeColor,
                                shape = RoundedCornerShape(60.dp)
                            ),

                        onClick = {
                            if(isClicked.value) {
                                qualityReader = "320k"
                                Toast.makeText(
                                    context,
                                    "Quality is set to 320k",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }else{
                                Toast.makeText( context, "Select audio first",Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                    {
                        Text(text = "320k")

                    }
            }
            }

        }
        val outputDir = File(context.cacheDir, "processed_audio")
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }
            val randomInt = Random.nextInt(1000)
            var outputFileName = "output${randomInt}.mp3"
            val outputFilePath = File(outputDir, outputFileName).absolutePath
            val outputFileAudio = File(outputDir, outputFileName)




        fun compressAudio(inputPath: String, quality: String, outputFile: String){


            // Get quality value from the text box and assign it to the quality variable

            // Construct the FFmpeg command with the quality parameter
            val command = "-i $inputPath -b:a $quality $outputFile"

            // Execute the FFmpeg command
            val session: FFmpegSession = FFmpegKit.execute(command)

            // Check the execution result
            if (ReturnCode.isSuccess(session.returnCode)) {
                // Compression successful
                Log.d(TAG, "Audio compression successful! Output file")
            } else {
                // Compression failed
                Log.e(TAG, "Audio compression failed.")
            }

        }


        // Done Button

        Column(

            modifier = Modifier
                .offset(25.dp, 740.dp)

        ) {

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
                        isClicked.value = !isClicked.value
                        val audio = selectAudioDetails
                        if (audio != null && qualityReader.isNotEmpty()) {

                            compressAudio(audio, qualityReader, outputFilePath)
                            compressedAudio = outputFilePath
                            sharedViewModel.receiveAudio(AudioFile = outputFileAudio.absoluteFile)
                            navController.navigate("doneAudio")


                        }else {
                            Toast.makeText(context, "Select audio first", Toast.LENGTH_SHORT).show()
                        }


                    }


            ) {
                Text(

                    text = "Done",
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = (-1).sp,
                    fontWeight = FontWeight.Medium,

                    modifier = Modifier
                        .offset(x = 130.dp, y = 16.dp)

                )
            }

        }

        Column(

            modifier = Modifier
                .offset(25.dp, 560.dp)

        ) {

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
                        AudioPicker.launch("audio/mpeg")

                    }


            ) {
                Text(

                    text = "Select audio",
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = (-1).sp,
                    fontWeight = FontWeight.Medium,

                    modifier = Modifier
                        .offset(x = 100.dp, y = 16.dp)

                )

                Image(
                    painter = painterResource(id = R.drawable.add_box_fill0_wght400_grad0_opsz24),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(60.dp)
                        .padding(0.dp)
                        .offset(x = 40.dp, y = (-15).dp)

                )


            }

        }

    }
    }





