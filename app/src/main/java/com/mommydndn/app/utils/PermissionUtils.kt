package com.mommydndn.app.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat

object PermissionUtils {

    fun checkAndRequestPermissions(
        context: Context,
        permissions: Array<String>,
        launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
        onPermissionGranted: () -> Unit
    ) {
        if (permissions.all {
                ContextCompat.checkSelfPermission(
                    context,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            onPermissionGranted()
        } else {
            launcher.launch(permissions)
        }
    }

}