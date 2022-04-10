package com.eratart.baristashandbook.core.util.markdown

class FormattingData(
    val placeholder: String,
    val text: String,
    val startIndex: Int,
    val textStyle: MarkdownStyle
) {
    fun getEndIndex() = startIndex + placeholder.length
}