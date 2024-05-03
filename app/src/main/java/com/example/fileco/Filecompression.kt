package com.example.fileco

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.chaquo.python.Python
import java.io.File
import java.io.FileOutputStream


@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WindowFileCompression(navController: NavHostController, sharedViewModel :datasharemodel) {


    val context = LocalContext.current
    val buttonStrokeColor = Color(0xFF9DB2BF)

    var seletedDoc by remember {
        mutableStateOf<Uri?>(null)
    }
    var selecteddocpath by remember {
        mutableStateOf<String?>(null)
    }
    var compressedDoc by remember {
        mutableStateOf<File?>(null)
    }


    var selectedPdfName by remember {
        mutableStateOf<String?>(null)
    }
    var selectedPdfSize by remember {
        mutableStateOf<String?>(null)
    }

    val isPdfSelected =
        remember { derivedStateOf { seletedDoc != null && selecteddocpath != null } }

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
        if (size <= 0) return "0 MB"
        val fileSizeInMB = size.toDouble() / (1024 * 1024)
        return String.format("%.2f MB", fileSizeInMB)
    }

    val sizeOffile = selectedPdfSize?.toLong()?.let { formatFileSize(it) }


    val FilePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { selectedUri ->
            seletedDoc = selectedUri
            val inputStream = selectedUri?.let { context.contentResolver.openInputStream(it) }
            val tempFile = File(context.cacheDir, "doc.pdf")

            inputStream?.use { inputStream ->
                val outputStream = FileOutputStream(tempFile)
                inputStream.copyTo(outputStream)
                outputStream.flush()
                outputStream.close()
            }

            val tempFilePath = tempFile.absolutePath
            selecteddocpath = tempFilePath
            val pdfName = getFileNameFromUri(selectedUri, context)
            selectedPdfName = pdfName
            val pdfSize = getFileSizeFromUri(selectedUri, context)
            selectedPdfSize = pdfSize.toString()

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
                .offset(x = 30.dp, y = (60).dp)
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

                //Icon inside rounded rectangle


                Text(
                    color = Color.White,
                    text = "\tFilename: $selectedPdfName\n\tFilesize: $sizeOffile "
                )


            }

        }


        // Button Three is inside this Container
        Column(

            modifier = Modifier
                .offset(25.dp, 650.dp)

        ) {

        }


        // Done Button
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
                    .clickable {
                        if (isPdfSelected.value){
                            val python = Python.getInstance()
                            val pyModule = python.getModule("pdfCompressor")
                            val pyFunction = pyModule.callAttr("pdfCompress", selecteddocpath)
                            val pythonFilePath: String = pyFunction.toString()
                            val kotlinFile = File(pythonFilePath)
                            compressedDoc = kotlinFile

                            sharedViewModel.receivePdfOut(PdfFile = compressedDoc)
                            navController.navigate("donepdf")
                        } else {
                            Toast
                                .makeText(context, "Select Pdf first", Toast.LENGTH_SHORT)
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
                        .offset(x = 130.dp, y = 16.dp)

                )
            }

        }
        // select file button

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

                            FilePicker.launch(arrayOf("application/pdf"))




                    }


            ) {
                Text(

                    text = "Select File",
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = (-1).sp,
                    fontWeight = FontWeight.Medium,

                    modifier = Modifier
                        .offset(x = 110.dp, y = 16.dp)

                )

                Image(
                    painter = painterResource(id = R.drawable.add_box_fill0_wght400_grad0_opsz24),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(60.dp)
                        .padding(0.dp)
                        .offset(x = 50.dp, y = (-16).dp)

                )


            }

        }
    }
}








