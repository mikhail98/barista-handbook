package com.eratart.baristashandbook.tools.share

import android.graphics.Bitmap
import java.io.File

interface IShareTool {

    fun shareText(text: String, title: String?)

    fun shareImage(bitmap: Bitmap, text: String, title: String?)

    fun sharePdf(file: File, title: String?)
}