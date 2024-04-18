package com.example.fileco

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.File

class datasharemodel: ViewModel() {


    var finalVideoOutput by mutableStateOf<File?>(null)
        private set

    fun receiveVideo(videoToEnd: File){
        finalVideoOutput = videoToEnd
    }

}