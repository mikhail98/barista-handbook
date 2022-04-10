package com.eratart.baristashandbook.tools.pdfcreator

import android.app.Activity
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.core.ext.getBitmapFromUrl
import com.eratart.baristashandbook.core.ext.printError
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import java.io.File
import java.io.FileOutputStream

class PdfCreator(private val activity: Activity) : IPdfCreator {

    override fun createPdfForDish(dish: Dish, onFileReady: (File?) -> Unit) {
        activity.getBitmapFromUrl(dish.photos.first()) {

        }
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        val folder = File(dir, activity.getString(R.string.app_name))
        if (!folder.exists()) {
            folder.mkdir()
        }
        try {
            val file = File(folder, dish.id.plus(StringConstants.PDF_EXT))
            file.createNewFile()
            val fileOutputStream = FileOutputStream(file)
            val document = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(
                100, 100, 1
            ).create()

            val page = document.startPage(pageInfo).apply {
                canvas.drawText(dish.title, 0f, 10f, Paint())
            }

            document.apply {
                finishPage(page)
                writeTo(fileOutputStream)
                close()
            }

            fileOutputStream.close()

            onFileReady.invoke(file)
        } catch (e: Exception) {
            e.printError()
            onFileReady.invoke(null)
        }
    }

    override fun createPdfForItem(item: Item, onFileReady: (File?) -> Unit) {
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        val folder = File(dir, activity.getString(R.string.app_name))
        if (!folder.exists()) {
            folder.mkdir()
        }
        try {
            val file = File(folder, item.id.plus(StringConstants.PDF_EXT))
            file.createNewFile()
            val fileOutputStream = FileOutputStream(file)
            val document = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(
                100, 100, 1
            ).create()

            val page = document.startPage(pageInfo).apply {
                canvas.drawText(item.title, 0f, 10f, Paint())
            }

            document.apply {
                finishPage(page)
                writeTo(fileOutputStream)
                close()
            }

            fileOutputStream.close()

            onFileReady.invoke(file)
        } catch (e: Exception) {
            e.printError()
            onFileReady.invoke(null)
        }
    }

}