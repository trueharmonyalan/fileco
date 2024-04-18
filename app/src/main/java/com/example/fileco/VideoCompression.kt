package com.example.fileco

import android.content.ContentValues.TAG
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import coil.request.videoFrameMillis
import com.arthenica.ffmpegkit.FFmpegKit
import com.arthenica.ffmpegkit.ReturnCode
import java.io.File
import java.io.IOException
import java.io.InputStream


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WindowVideoCompression(navController: NavHostController, sharedViewModel: datasharemodel) {
    var qualityReader by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val buttonStrokeColor = Color(0xFF9DB2BF)

    var selectedVideo by remember {
        mutableStateOf<Uri?>(null)
    }
    var selectedvideopath by remember {
        mutableStateOf<String?>(null)
    }
    println(selectedvideopath)
    var Compressedvideo by remember {
        mutableStateOf<File?>(null)
    }
    var isCompressing by remember { mutableStateOf(false) }


    fun getRealPathFromAudioURI(context: Context, contentUri: Uri): String? {
        var cursor: Cursor? = null
        return try {
            val projection = arrayOf(MediaStore.MediaColumns.DATA)
            cursor = context.contentResolver.query(contentUri, projection, null, null, null)
            if (cursor != null && cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                cursor.getString(columnIndex)
            } else {
                null
            }
        } finally {
            cursor?.close()
        }
    }

    val directory = File(context.filesDir, "videos")

// Create the directory if it doesn't exist
    if (!directory.exists()) {
        directory.mkdirs()
    }

// Generate a random file name
    val outputFileName = "processed_video_${System.currentTimeMillis()}.mp4"
    val processedFile = File(directory, outputFileName)

//// Create the directory if it doesn't exist
//    if (!directory.exists()) {
//        directory.mkdirs()  // Make parent directories as well
//    }
//
//// Now you can save processed files to this directory
//    val outputFileName = "processedvideo.mp4"  // Provide a filename for the output file


    // Get the list of existing files in the directory
//    val existingFiles = directory.listFiles { file -> file.isFile }?.toList() ?: emptyList()
//
//// Find the highest number suffix used in existing files
//    val maxSuffix = existingFiles
//        .mapNotNull { file ->
//            val fileName = file.name
//            val fileNameWithoutExtension = fileName.substringBeforeLast(".")
//            val suffix = fileNameWithoutExtension.substringAfterLast("_").toIntOrNull()
//            suffix
//        }
//        .maxOrNull() ?: 0
//
//// Construct the output file name with a suffix
//    val baseFileName_ = "processed_video89"
//    val outputFileName = "$baseFileName_${maxSuffix + 1}.mp4"
//
//    val processedFile = File(directory, outputFileName)



    val videoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {selectedUri ->

            val videoPath = selectedUri?.let { getRealPathFromAudioURI(context, it) }
            selectedVideo = selectedUri
            selectedvideopath = videoPath





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
                Image(painter = painterResource(id = R.drawable.video_library_fill0_wght400_grad0_opsz24),
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


                .offset((-10).dp, 200.dp)
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

                val model = ImageRequest.Builder(LocalContext.current)
                    .data(selectedVideo)
                    .videoFrameMillis(10000)
                    .decoderFactory { result, options, _ ->
                        VideoFrameDecoder(
                            result.source,
                            options
                        )
                    }
                    .build()


                AsyncImage(
                    model = model,
                    contentDescription = "video thumbnail",
                    contentScale = ContentScale.FillBounds
                )






            }
        }

        fun compressVideo(inputPath: String, quality: Int, outputFile: File) {
            val command = "-i $inputPath -c:v mpeg4 -q:v $quality ${outputFile.absolutePath}"
            val session = FFmpegKit.execute(command)

            if (ReturnCode.isSuccess(session.returnCode)) {
                // Compression successful
            } else if (ReturnCode.isCancel(session.returnCode)) {
                // Compression cancelled
            } else {
                // Compression failed
                Log.d(
                    TAG,
                    String.format(
                        "Command failed with state %s and rc %s.%s",
                        session.state,
                        session.returnCode,
                        session.failStackTrace
                    )
                )
            }
        }



        //Done Button
        Column(

            modifier = Modifier
                .offset(25.dp, 740.dp)

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
                    .clickable {
//                        val media = selectedvideopath
//                        println(media)
//                        val command = "-i $media -c:v mpeg4 -q:v $qualityReader ${processedFile.absolutePath}"
//
//                        val session = FFmpegKit.execute(command)
//
//
//                        if (ReturnCode.isSuccess(session.returnCode)) {
//
//                            // SUCCESS
//                        } else if (ReturnCode.isCancel(session.returnCode)) {
//
//                            // CANCEL
//                        } else {
//
//                            // FAILURE
//                            Log.d(
//                                TAG,
//                                String.format(
//                                    "Command failed with state %s and rc %s.%s",
//                                    session.state,
//                                    session.returnCode,
//                                    session.failStackTrace
//                                )
//                            )
//                        }
//                        Compressedvideo = processedFile
//                        sharedViewModel.receiveVideo(videoToEnd = processedFile.absoluteFile)
//                       navController.navigate("done",)
                        val media = selectedvideopath
                        if (media != null && qualityReader.isNotEmpty()) {
                            compressVideo(media, qualityReader.toInt(), processedFile)
                            Compressedvideo = processedFile
                            sharedViewModel.receiveVideo(videoToEnd = processedFile.absoluteFile)
                            navController.navigate("done")
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
                        .offset(x = 20.dp, y = 16.dp)

                )
            }

        }


        //  Button Three is inside this Container

        Column(

            modifier = Modifier
                .offset(25.dp, 650.dp)

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
                            Icon(
                                painter = painterResource(id = R.drawable.clarify_fill0_wght400_grad0_opsz24),
                                contentDescription = "quality icon",

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
                                    qualityReader = numberCheck.toString()
                                    println(qualityReader)

                                    //quality reader is the variable use for compression operation
                                }
                            }
                        },


                        )
                }
            }


// select button

            Column(

                modifier = Modifier
                    .offset(y = -158.dp),
                horizontalAlignment = Alignment.CenterHorizontally

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
                            videoPicker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly)
                            )

                        }


                ) {
                    Text(

                        text = "Select Video",
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


}



