package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.Image
import java.io.File

interface ImageRepository {

    suspend fun uploadFiles(files: List<File>): List<Image>
}