package com.mommydndn.app.utils

import android.net.Uri
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object NetworkUtils {

    fun getImagePart(uri: Uri): MultipartBody.Part {
        val file = File(uri.path)
        val requestFile = file.asRequestBody("image/*".toMediaType())
        return MultipartBody.Part.createFormData("image", file.name, requestFile)
    }
}