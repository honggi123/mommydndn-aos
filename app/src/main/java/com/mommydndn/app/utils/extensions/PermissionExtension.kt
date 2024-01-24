package com.mommydndn.app.utils.extensions

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import androidx.core.content.ContextCompat

internal fun isMediaImagesPermissionGranted(context: Context): Boolean {
    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> {
            ContextCompat.checkSelfPermission(
                context,
                READ_MEDIA_VISUAL_USER_SELECTED
            ) == PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(
                        context,
                        READ_MEDIA_IMAGES
                    ) == PERMISSION_GRANTED
        }

        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
            ContextCompat.checkSelfPermission(context, READ_MEDIA_IMAGES) == PERMISSION_GRANTED
        }

        else -> ContextCompat.checkSelfPermission(
            context,
            READ_EXTERNAL_STORAGE
        ) == PERMISSION_GRANTED
    }
}