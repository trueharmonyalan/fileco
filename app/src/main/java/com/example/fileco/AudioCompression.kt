package com.example.fileco

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arthenica.ffmpegkit.FFmpegKit
import com.arthenica.ffmpegkit.FFmpegSession
import com.arthenica.ffmpegkit.ReturnCode
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WindowAudioCompression(navHostController: NavHostController) {

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

    var hasStoragePermission by remember { mutableStateOf(false) }


    fun getRealPathFromAudioURI(context: Context, contentUri: Uri): String? {
        return try {
            val projection = arrayOf(MediaStore.Audio.Media.DATA)
            context.contentResolver.query(contentUri, projection, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
                    cursor.getString(columnIndex)
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }



    val directory = File(context.filesDir, "Audios")

//// Create the directory if it doesn't exist
//    if (!directory.exists()) {
//        directory.mkdirs()  // Make parent directories as well
//    }
//
//// Now you can save processed files to this directory
//    val outputFileName = "processedvideo.mp4"  // Provide a filename for the output file


    // Get the list of existing files in the directory
    val existingFiles = directory.listFiles { file -> file.isFile }?.toList() ?: emptyList()

// Find the highest number suffix used in existing files
    val maxSuffix = existingFiles
        .mapNotNull { file ->
            val fileName = file.name
            val fileNameWithoutExtension = fileName.substringBeforeLast(".")
            val suffix = fileNameWithoutExtension.substringAfterLast("_").toIntOrNull()
            suffix
        }
        .maxOrNull() ?: 0

// Construct the output file name with a suffix
    val baseFileName_ = "processed_audios"
    val outputFileName = "$baseFileName_${maxSuffix + 1}.mp4"

    val processedFile = File(directory, outputFileName)

    val AudioPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { selectedUri ->

            val audioPath = selectedUri?.let { getRealPathFromAudioURI(context, it) }
            selectedAudio = selectedUri
            selectAudioDetails = audioPath
            println(audioPath)


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
                    text = "Selected Audio: ${selectAudioDetails}",
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
                Text(
                    text = "Quality",
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = (-1).sp,
                    fontWeight = FontWeight.Medium,

                    modifier = Modifier
                        .offset(x = 20.dp, y = (15).dp)

                )


                TextField(
                    value = qualityReader,
                    leadingIcon = {
                        Icon(painter = painterResource(id = R.drawable.clarify_fill0_wght400_grad0_opsz24),
                            contentDescription ="quality icon",

                            )
                    },
                    modifier = Modifier
                        .width(100.dp)
                        .offset(x = 128.dp, y = 5.dp)
                        .border(
                            width = 2.dp,
                            color = buttonStrokeColor,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color(android.graphics.Color.parseColor("#27374D")),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White

                    ),

                    singleLine = true,


                    onValueChange = { userResponse ->

                        if (userResponse.isEmpty()){
                            qualityReader = ""
                        }
                        else{
                            val numberCheck =userResponse.toIntOrNull()
                            if (numberCheck != null && numberCheck in 1..100){
                                qualityReader = userResponse
                                //quality reader is the variable use for compression operation
                            }
                        }
                    },


                    )
            }
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
                        val quality = "128k" // Set the default quality here
                        // Get quality value from the text box and assign it to the quality variable

                        val fileName = selectedAudio

                        // Construct the FFmpeg command with the quality parameter
                        val command = "-i $fileName -b:a $quality ${processedFile.absolutePath}"

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

                        AudioPicker.launch("audio/*")


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




@Preview
@Composable
private fun WindowFileCompressionPrev() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val navController = rememberNavController()
        WindowAudioCompression(navController)
    }


}