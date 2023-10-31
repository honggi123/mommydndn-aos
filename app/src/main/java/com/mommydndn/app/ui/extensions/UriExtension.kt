package com.mommydndn.app.ui.extensions

import android.content.Context
import android.net.Uri
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream


fun Uri.asMultipart(name: String, context: Context): MultipartBody.Part? {
    try {
        val inputStream = context.contentResolver.openInputStream(this)
        val file = File(context.cacheDir, "temp_file") // 임시 파일 생성
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()

        val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", file.name, requestBody)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}


