package com.example.fileco

import android.content.Context
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.io.File
import java.io.IOException

@Composable
fun AudioFinalWindow(navController: NavHostController, sharedViewModel: datasharemodel) {
    val context = LocalContext.current
    val AudioComes = sharedViewModel.finalAudioOutput
    val audioName = AudioComes?.name
    val audioSize = AudioComes?.length()
    println(audioName)

    fun formatFileSize(size: Long): String {
        if (size <= 0) return "0 MB"
        val fileSizeInMB = size.toDouble() / (1024 * 1024)
        return String.format("%.2f MB", fileSizeInMB)
    }
    val sizeOffile = audioSize?.let { formatFileSize(it) }



    fun saveAudioToUri(context: Context, audioFile: File, uri: Uri) {
        try {
            context.contentResolver.openOutputStream(uri)?.use { output ->
                audioFile.inputStream().use { input ->
                    input.copyTo(output)
                }
            }
            Toast.makeText(context, "Compressed Audio saved successfully", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(context, "Error saving Audio: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    val saveAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("audio/mpeg"),
        onResult = { uri ->
            if (uri != null) {
                // Save the video file to the selected location
                saveAudioToUri(context, AudioComes!!, uri)
            }
        }
    )

    fun saveAudio() {
        // Get the Uri of the compressed video file
        val uri = AudioComes?.let { Uri.fromFile(it) }

        // Create an intent to save the file
        saveAudioLauncher.launch(audioName)
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


                .offset(((-10).dp), 200.dp)
                .padding(0.dp)
        ) {

            //rounded rectangle configuration

            Column(
                modifier = Modifier
                    .height(296.dp)
                    .width(396.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#17273e")),
                        shape = RoundedCornerShape(15.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "Compressed Video:${audioName}",
                    color = androidx.compose.ui.graphics.Color.White,

                    )
                Text(
                    text = "Compressed Video Size:${sizeOffile}",
                    color = androidx.compose.ui.graphics.Color.White,
                )



            }
        }


    }

    Column(
        modifier = Modifier
            .offset(x=25.dp,y=520.dp)

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

                    saveAudio()
                }


        ) {
            Text(
                text = "Save",
                fontSize = 25.sp,
                color = androidx.compose.ui.graphics.Color.White,
                letterSpacing = (-1).sp,
                fontWeight = FontWeight.Medium,

                modifier = Modifier
                    .offset(x = 20.dp, y = 16.dp)

            )
        }
    }





}