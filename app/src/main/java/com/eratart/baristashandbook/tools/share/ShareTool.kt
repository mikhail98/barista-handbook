package com.eratart.baristashandbook.tools.share

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.eratart.baristashandbook.BuildConfig
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.core.constants.StringConstants
import java.io.File

class ShareTool(private val activity: Activity) : IShareTool {

    companion object {
        private const val TYPE_IMAGE = "image/*"
        private const val TYPE_PLAIN = "text/plain"
        private const val TYPE_PDF = "application/pdf"

        private const val PROVIDER_SUFFIX = ".provider"

        private const val IMAGE_TMP = "image_tmp"
    }

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
        val textToShare = text.plus(getAppUrlSuffix(true))
        val bitmapPath = MediaStore.Images.Media.insertImage(
            activity.contentResolver, bitmap, IMAGE_TMP, null
        )
        val bitmapUri = Uri.parse(bitmapPath)
        val sendIntent = Intent().apply {
            putExtra(Intent.EXTRA_TEXT, textToShare)
            putExtra(Intent.EXTRA_STREAM, bitmapUri)
        }
        startActivity(sendIntent, TYPE_IMAGE, title)
    }

    override fun sharePdf(file: File, title: String?) {
        val imageUri = FileProvider.getUriForFile(
            activity, BuildConfig.APPLICATION_ID.plus(PROVIDER_SUFFIX), file
        )
        val sendIntent = Intent().apply {
            putExtra(Intent.EXTRA_TEXT, getAppUrlSuffix(false))
            putExtra(Intent.EXTRA_STREAM, imageUri)
        }
        startActivity(sendIntent, TYPE_PDF, title)
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