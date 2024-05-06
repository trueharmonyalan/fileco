package com.example.fileco

import android.content.ContentValues.TAG
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import coil.request.videoFrameMillis
import com.arthenica.ffmpegkit.FFmpegKit
import com.arthenica.ffmpegkit.ReturnCode
import com.arthenica.ffmpegkit.Statistics
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File






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
    val isVideoSelected =
        remember { derivedStateOf { selectedVideo != null && selectedvideopath != null } }

    var selectedVideoName by remember {
        mutableStateOf<String?>(null)
    }
    var selectedVideoSize by remember {
        mutableStateOf<String?>(null)
    }


    fun getFileNameFromUri(uri: Uri?, context: Context): String? {
        uri?.let {
            val cursor = context.contentResolver.query(it, null, null, null, null)
            cursor?.moveToFirst()
            val nameIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            val fileName = nameIndex?.let { index ->
                cursor.getString(index)
            }
            cursor?.close()
            return fileName
        }
        return null
    }

    fun getFileSizeFromUri(uri: Uri?, context: Context): Long {
        uri?.let {
            val cursor = context.contentResolver.query(it, null, null, null, null)
            cursor?.moveToFirst()
            val sizeIndex = cursor?.getColumnIndex(OpenableColumns.SIZE)

            val fileSize = sizeIndex?.let { index ->
                cursor.getLong(index)

            } ?: 0L
            cursor?.close()
            return fileSize
        }

        return 0L
    }

    fun formatFileSize(size: Long): String {
        val kiloBytes = 1000.0
        val megaBytes = kiloBytes * 1000
        val gigaBytes = megaBytes * 1000

        return when {
            size < kiloBytes -> "$size B"
            size < megaBytes -> "%.2f KB".format(size / kiloBytes)
            else -> {
                val sizeInMB = size.toDouble() / megaBytes
                "%.2f MB".format(sizeInMB)
            }
        }
    }

    val sizeOffile = selectedVideoSize?.toLong()?.let { formatFileSize(it) }


    fun getRealPathFromVideoURI(context: Context, contentUri: Uri): String? {
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


    val videoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedUri ->

            val videoPath = selectedUri?.let { getRealPathFromVideoURI(context, it) }
            selectedVideo = selectedUri
            selectedvideopath = videoPath
            val videoName = getFileNameFromUri(selectedUri, context)
            selectedVideoName = videoName
            val videoSize = getFileSizeFromUri(selectedUri, context)
            selectedVideoSize = videoSize.toString()


        }
    )
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val fontName = GoogleFont("Roboto")

    val fontFamily = FontFamily(
        Font(
            googleFont = fontName,
            fontProvider = provider,
            weight = FontWeight.Bold,
            style = FontStyle.Italic
        )
    )



    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor("#27374D")))
            .padding(30.dp)
            .offset(10.dp, 0.dp)

    ) {

        Text(
            fontFamily = fontFamily,
            text = "Video",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Thin
        )
        Text(
            modifier = Modifier
                .offset(0.dp, 27.dp),
            text = "Compressor.",
            fontSize = 40.sp,
            fontFamily = fontFamily,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )


        //rounded rectangle configuration

        Column(
            modifier = Modifier
                .height(296.dp)
                .width(330.dp)
                .offset(0.dp, 100.dp)
                .background(
                    color = Color(android.graphics.Color.parseColor("#17273e")),
                    shape = RoundedCornerShape(15.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            if (sizeOffile != null) {
                Text(
                    text = "Size: $sizeOffile",
                    color = Color.White
                )
            }


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
                contentScale = ContentScale.Crop
            )


        }









        fun compressVideo(
            inputPath: String,
            quality: Int,
            outputFile: File,
        ) = runBlocking {
            GlobalScope.launch {
                println("working on: ${Thread.currentThread()}")
                val command = "-i $inputPath -c:v mpeg4 -q:v $quality ${outputFile.absolutePath}"
                val session = FFmpegKit.execute(command)
                if (ReturnCode.isSuccess(session.returnCode)) {
                    Compressedvideo = processedFile
                    sharedViewModel.receiveVideo(videoToEnd = processedFile.absoluteFile)

                    // Switch to the main thread before navigating
                    withContext(Dispatchers.Main) {
                        navController.navigate("done")
                    }

                    // Compression successful
                } else if (ReturnCode.isCancel(session.returnCode)) {
                    // Compression cancelled
                } else {
                    // Compression failed
                    Log.d(
                        TAG, String.format(
                            "Command failed with state %s and rc %s.%s",
                            session.state, session.returnCode, session.failStackTrace
                        )
                    )
                }
            }
        }


// select button

        Column(

            modifier = Modifier
                .offset(5.dp, 430.dp),
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


//Quality Button
        Column(
            modifier = Modifier
                .height(68.dp)
                .width(321.dp)
                .offset(5.dp, 530.dp)
                .background(
                    color = Color(android.graphics.Color.parseColor("#526D82")),
                    shape = RoundedCornerShape(60.dp)
                )
                .border(3.dp, color = buttonStrokeColor, shape = RoundedCornerShape(60.dp))


        ) {
            Row {
                Text(
                    text = "Compression",
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = (-1).sp,
                    fontWeight = FontWeight.Medium,

                    modifier = Modifier
                        .offset(x = 20.dp, y = (15).dp)

                )


                TextField(
                    enabled = isVideoSelected.value,
                    value = qualityReader,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.clarify_fill0_wght400_grad0_opsz24),
                            contentDescription = "quality icon",

                            )
                    },
                    modifier = Modifier
                        .width(100.dp)
                        .offset(x = 70.dp, y = 5.dp)
                        .border(
                            width = 2.dp,
                            color = buttonStrokeColor,
                            shape = RoundedCornerShape(15.dp)
                        ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color(android.graphics.Color.parseColor("#27374D")),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White

                    ),

                    singleLine = true,


                    onValueChange = { userResponse ->

                        if (userResponse.isEmpty()) {
                            qualityReader = ""
                        } else {
                            val numberCheck = userResponse.toIntOrNull()
                            if (numberCheck != null && numberCheck in 1..100) {
                                qualityReader = numberCheck.toString()
                                println(qualityReader)

                                //quality reader is the variable use for compression operation
                            }
                        }
                    },


                    )
            }
        }


        //Done Button
        Column(

            modifier = Modifier
                .offset(5.dp, 630.dp)

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


                        val media = selectedvideopath
                        if (media != null && qualityReader.isNotEmpty()) {

                            compressVideo(media, qualityReader.toInt(), processedFile)

                        } else {
                            Toast
                                .makeText(context, "Select video first", Toast.LENGTH_SHORT)
                                .show()
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


    }
}






