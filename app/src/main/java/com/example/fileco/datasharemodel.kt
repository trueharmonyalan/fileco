package com.example.fileco

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.File

class datasharemodel: ViewModel() {


    var finalVideoOutput by mutableStateOf<File?>(null)
        private set

    fun receiveVideo(videoToEnd: File){
        finalVideoOutput = videoToEnd
    }


    var finalAudioOutput by mutableStateOf<File?>(null)
        private set

    fun receiveAudio(AudioFile: File){
        finalAudioOutput = AudioFile
        println("receivved vide:$AudioFile")
    }


    var finalPdfOutput by mutableStateOf<File?>(null)
        private set

    fun receivePdfOut(PdfFile: File?){
        finalPdfOutput = PdfFile
        println("receivved vide:$PdfFile")
    }



    var finalImageOutput by mutableStateOf<File?>(null)
        private set

    fun receiveImageOut(ImageFile: File?){
        finalImageOutput = ImageFile
        println("receivved vide:$ImageFile")
    }


}