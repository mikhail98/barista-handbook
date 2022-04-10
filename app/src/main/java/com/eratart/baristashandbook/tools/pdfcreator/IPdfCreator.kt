package com.eratart.baristashandbook.tools.pdfcreator

import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import java.io.File

interface IPdfCreator {
    fun createPdfForItem(item: Item, onFileReady: (File?) -> Unit)

    fun createPdfForDish(dish: Dish, onFileReady: (File?) -> Unit)
}