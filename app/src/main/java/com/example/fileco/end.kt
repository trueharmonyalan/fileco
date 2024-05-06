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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException



@Composable
fun FinalWindowPage(navController: NavHostController,sharedViewModel: datasharemodel) {
val context = LocalContext.current
    val VideoComes = sharedViewModel.finalVideoOutput
    val fileName = VideoComes?.name
    val fileSize = VideoComes?.length()




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
    val sizeOffile = fileSize?.let { formatFileSize(it) }




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



    fun saveFile() = runBlocking {
        GlobalScope.launch {
            saveFileLauncher.launch(fileName)
        }

    }




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



        ) {

        Text(
            fontFamily = fontFamily,
            text = "Completed!..",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Thin
        )

        //rounded rectangle configuration

        Column(
            modifier = Modifier
                .height(296.dp)
                .width(360.dp)
                .offset(0.dp, 100.dp)
                .background(
                    color = Color(android.graphics.Color.parseColor("#17273e")),
                    shape = RoundedCornerShape(15.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Compressed Video:\n${fileName}\n",
                color = Color.White,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Light

            )
            Text(
                text = "Compressed Video Size: ${sizeOffile}",
                color = Color.White,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Light
            )


        }





        Column(
            modifier = Modifier
                .offset(x = 20.dp, y = 420.dp)

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
                        .offset(x = 130.dp, y = 16.dp)

                )
            }
        }


    }

}


