package com.eratart.baristashandbook.tools.share

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.eratart.baristashandbook.BuildConfig
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.tools.permissions.IPermissionsManager
import com.eratart.baristashandbook.tools.permissions.PermissionsManager
import java.io.File
import java.io.FileOutputStream

class ShareTool(private val activity: Activity) : IShareTool {

    companion object {
        private const val TYPE_IMAGE = "image/*"
        private const val TYPE_PLAIN = "text/plain"
        private const val TYPE_PDF = "application/pdf"

        private const val PROVIDER_SUFFIX = ".provider"

        private const val IMAGE_TMP = "image_tmp"
    }

    private val permissionsManager: IPermissionsManager by lazy { PermissionsManager(activity) }

    override fun shareApp() {
        val textToShare = getAppUrlSuffix(false)
        val sendIntent = Intent().apply {
            putExtra(Intent.EXTRA_TEXT, textToShare)
        }
        startActivity(sendIntent, TYPE_PLAIN, activity.getString(R.string.app_name))
    }

    override fun shareText(text: String, title: String?) {
        val textToShare = text.plus(getAppUrlSuffix(true))
        val sendIntent = Intent().apply {
            putExtra(Intent.EXTRA_TEXT, textToShare)
        }
        startActivity(sendIntent, TYPE_PLAIN, title)
    }

    override fun shareImage(bitmap: Bitmap, text: String, title: String?) {
        if (isMoreThanQ() || permissionsManager.checkWritePermissions()) {
            val textToShare = text.plus(getAppUrlSuffix(true))
            val bitmapUri = getBitmapUri(bitmap)
            val sendIntent = Intent().apply {
                putExtra(Intent.EXTRA_TEXT, textToShare)
                putExtra(Intent.EXTRA_STREAM, bitmapUri)
            }
            startActivity(sendIntent, TYPE_IMAGE, title)
        }
    }

    private fun getBitmapUri(bitmap: Bitmap): Uri {
        return if (isMoreThanQ()) {
            val path = MediaStore.Images.Media.insertImage(
                activity.contentResolver, bitmap, IMAGE_TMP, null
            )
            Uri.parse(path)
        } else {
            val imagesDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, System.currentTimeMillis().toString().plus(".jpeg"))

            val fos = FileOutputStream(image)
            fos.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
            fos.close()
            getUriForFile(image)
        }
    }

    private fun isMoreThanQ(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }

    override fun sharePdf(file: File, title: String?) {
        val imageUri = getUriForFile(file)
        val sendIntent = Intent().apply {
            putExtra(Intent.EXTRA_TEXT, getAppUrlSuffix(false))
            putExtra(Intent.EXTRA_STREAM, imageUri)
        }
        startActivity(sendIntent, TYPE_PDF, title)
    }

    private fun getUriForFile(file: File): Uri {
        return FileProvider.getUriForFile(
            activity, BuildConfig.APPLICATION_ID.plus(PROVIDER_SUFFIX), file
        )
    }

    private fun startActivity(intent: Intent, extraType: String, title: String?) {
        val sendIntent = intent.apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TITLE, title)
            type = extraType
        }

        val shareIntent = Intent.createChooser(sendIntent, title)
        activity.startActivity(shareIntent)
    }

    private fun getAppUrlSuffix(addPrefix: Boolean): String {
        val appName = activity.getString(R.string.app_name)
        var string = StringConstants.EMPTY
        if (addPrefix) {
            string += StringConstants.NEW_LINE
                .plus(StringConstants.NEW_LINE)
                .plus(StringConstants.NEW_LINE)
        }
        string += activity.getString(R.string.app_available_on_google_play, appName)
            .plus(StringConstants.NEW_LINE)
            .plus(BuildConfig.APP_URL_GOOGLE)
        return string
    }
}