package com.example.fileco

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
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
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.chaquo.python.Python
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun WindowImageCompression(navController: NavHostController,sharedViewModel :datasharemodel) {
    val context = LocalContext.current
    var qualityReader by remember {
        mutableStateOf("")
    }
    val buttonStrokeColor = Color(0xFF9DB2BF)

    var selectedImage by remember {
    mutableStateOf<Uri?>(null)
    }

    var selectedImagepath by remember {
        mutableStateOf<String?>(null)
    }

    var compressedImage by remember {
        mutableStateOf<File?>(null)
    }
    val isClicked = remember { mutableStateOf(false) }


    var selectedImageSize by remember {
        mutableStateOf<String?>(null)
    }
    val isImageSelected = remember { derivedStateOf { selectedImage != null && selectedImagepath != null } }

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

    val sizeOffile = selectedImageSize?.toLong()?.let { formatFileSize(it) }


    fun getRealPathFromImageURI(context: Context, contentUri: Uri): String? {
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

    val directory = File(context.filesDir, "Images")
    if (!directory.exists()) {
        directory.mkdirs()
    }
    val outputFileName = "Image${System.currentTimeMillis()}.PNG"
    val processedFile = File(directory, outputFileName)




    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {selectedUri ->
            val imagePath = selectedUri?.let { getRealPathFromImageURI(context, it) }
            selectedImage = selectedUri
            selectedImagepath = imagePath
            val ImageSize = getFileSizeFromUri(selectedUri, context)
            selectedImageSize = ImageSize.toString()

            println("when selected${selectedImagepath}")
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
            .padding(5.dp)
            .padding(30.dp)
            .offset(10.dp,0.dp)


    ) {
        Text(
            fontFamily= fontFamily,
            text = "Image",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Thin
        )
        Text(
            modifier = Modifier
                .offset(0.dp,27.dp),
            text = "Compressor.",
            fontSize = 40.sp,
            fontFamily= fontFamily,
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




                AsyncImage(
                    model = selectedImage,
                    contentDescription = "selected image",
                    contentScale = ContentScale.FillBounds,

                )




            }




            //button design for Button Three
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
                        text = "Quality",
                        fontSize = 25.sp,
                        color = Color.White,
                        letterSpacing = (-1).sp,
                        fontWeight = FontWeight.Medium,

                        modifier = Modifier
                            .offset(x = 20.dp, y = (15).dp)

                    )


                    TextField(
                        enabled = isImageSelected.value,
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













        fun compressImage(image :String, qualityReader:String) = runBlocking{
            GlobalScope.launch {
                if (image != null && qualityReader.isNotEmpty()) {
                    val python = Python.getInstance()
                    val pyModule = python.getModule("imageCompressor")
                    println("before sharing to pycompressor${selectedImagepath}")
                    val pyFunction = pyModule.callAttr(
                        "imagecompressor",
                        selectedImagepath,
                        qualityReader
                    )
                    val pythonFilePath: String = pyFunction.toString()
                    val kotlinFile = File(pythonFilePath)
                    compressedImage = kotlinFile

                    withContext(Dispatchers.Main) {
                        sharedViewModel.receivePdfOut(PdfFile = compressedImage)
                        navController.navigate("doneImage")
                    }

                } else {
                    Toast
                        .makeText(context, "Select image first", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }


        // Done Button

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
                        isClicked.value = !isClicked.value
                        val image = selectedImagepath

//                        if (image != null && qualityReader.isNotEmpty()) {
//                            val python = Python.getInstance()
//                            val pyModule = python.getModule("imageCompressor")
//                            println("before sharing to pycompressor${selectedImagepath}")
//                            val pyFunction = pyModule.callAttr(
//                                "imagecompressor",
//                                selectedImagepath,
//                                qualityReader
//                            )
//                            val pythonFilePath: String = pyFunction.toString()
//                            val kotlinFile = File(pythonFilePath)
//                            compressedImage = kotlinFile
//
//                            sharedViewModel.receivePdfOut(PdfFile = compressedImage)
//                            navController.navigate("doneImage")
//                        } else {
//                            Toast
//                                .makeText(context, "Select image first", Toast.LENGTH_SHORT)
//                                .show()
//                        }
                        if (image != null) {
                            compressImage(image,qualityReader)
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

        // add image button
        Column(

            modifier = Modifier
                .offset(5.dp, 430.dp),

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
                        imagePicker.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )

                    }


            ) {
                Text(

                    text = "Select Image",
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

