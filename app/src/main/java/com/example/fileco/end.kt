package com.example.fileco

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import java.io.File
import android.Manifest
import java.io.IOException


const val SAVE_REQUEST_CODE = 1001 // Define a request code for saving the file
private const val REQUEST_SAVE_VIDEO = 123

@Composable
fun FinalWindowPage(navController: NavHostController,sharedViewModel: datasharemodel) {
val context = LocalContext.current
    val VideoComes = sharedViewModel.finalVideoOutput
    val fileName = VideoComes?.name
    val fileSize = VideoComes?.length()

     fun formatFileSize(size: Long): String {
        if (size <= 0) return "0 MB"
        val fileSizeInMB = size.toDouble() / (1024 * 1024)
        return String.format("%.2f MB", fileSizeInMB)
    }
    val sizeOffile = fileSize?.let { formatFileSize(it) }





//    fun saveFile() {
//        // Get the Uri of the compressed video file
//        val uri = Uri.fromFile(VideoComes)
//
//        // Create an intent to save the file
//        val saveIntent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
//            type = "video/mp4"
//            putExtra(Intent.EXTRA_TITLE, VideoComes?.name) // Set the default file name
//            addCategory(Intent.CATEGORY_OPENABLE)
//        }
//
//        // Start the activity with the saveIntent
//
//        startActivityForResult(context as Activity, saveIntent, SAVE_REQUEST_CODE, null)
//    }
//fun saveVideoToExternalStorage(context: Context, videoFile: File) {
//    // Create a new file in the external storage directory
//    val externalDir = Environment.getExternalStorageDirectory()
//    val savedFile = File(externalDir, "${videoFile.nameWithoutExtension}_saved.mp4")
//
//    // Copy the video data to the new file
//    videoFile.inputStream().use { input ->
//        savedFile.outputStream().use { output ->
//            input.copyTo(output)
//        }
//    }
//
//    // Display a success message or perform any other necessary actions
//    Toast.makeText(context, "Video saved to external storage", Toast.LENGTH_SHORT).show()
//}
//
//    fun saveVideo() {
//        if (ContextCompat.checkSelfPermission(context,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            // Request the permission if not granted
//            ActivityCompat.requestPermissions(
//                context as Activity,
//                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                REQUEST_SAVE_VIDEO
//            )
//        } else {
//            // Permission is granted, save the video
//            saveVideoToExternalStorage(context, VideoComes!!)
//        }
//    }






    fun saveVideoToUri(context: Context, videoFile: File, uri: Uri) {
        try {
            context.contentResolver.openOutputStream(uri)?.use { output ->
                videoFile.inputStream().use { input ->
                    input.copyTo(output)
                }
            }
            Toast.makeText(context, "Video saved successfully", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(context, "Error saving video: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


    val saveFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("video/mp4"),
        onResult = { uri ->
            if (uri != null) {
                // Save the video file to the selected location
                saveVideoToUri(context, VideoComes!!, uri)
            }
        }
    )

    fun saveFile() {
        // Get the Uri of the compressed video file
        val uri = VideoComes?.let { Uri.fromFile(it) }

        // Create an intent to save the file
        saveFileLauncher.launch(fileName)
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


                .offset((-10.dp), 200.dp)
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
                    text = "Compressed Video:${fileName}",
                    color = Color.White,

                )
                Text(text ="Compressed Video Size:${sizeOffile}" ,
                    color = Color.White,
                    )
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

                        saveFile()
                    }


            ) {
                Text(
                    text = "Save",
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

