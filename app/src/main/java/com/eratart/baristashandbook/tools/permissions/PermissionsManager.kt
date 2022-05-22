package com.eratart.baristashandbook.tools.permissions

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.eratart.baristashandbook.tools.permissions.IPermissionsManager.Companion.PERMISSION_REQUEST_CODE_STORAGE

class PermissionsManager(private var activity: Activity) : IPermissionsManager {

    private fun isWriteExternalStoragePermissionEnabled() =
        ContextCompat.checkSelfPermission(
            activity, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED


    override fun checkWritePermissions(): Boolean {
        val isEnabled = isWriteExternalStoragePermissionEnabled()
        if (!isEnabled) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                PERMISSION_REQUEST_CODE_STORAGE
            )
        }
        return isEnabled
    }

    private fun requestPermissions(permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }
}