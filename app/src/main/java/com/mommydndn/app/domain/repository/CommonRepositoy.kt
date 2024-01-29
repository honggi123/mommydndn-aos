package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.Banner
import com.mommydndn.app.domain.model.Image
import java.io.File

interface CommonRepositoy {

    suspend fun getBanners(): List<Banner>

    suspend fun uploadFiles(files: List<File>): List<Image>
}