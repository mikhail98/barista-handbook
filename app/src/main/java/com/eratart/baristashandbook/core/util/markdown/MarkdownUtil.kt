package com.eratart.baristashandbook.core.util.markdown

import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.eratart.baristashandbook.core.ext.fromHtml
import com.eratart.baristashandbook.core.util.markdown.MarkdownUtil.getWithoutMd

object MarkdownUtil {
    private val regexMDLink = Regex("\\[(.*?)\\]\\((.*?)\\)")

    fun String.getWithoutMd(): String {
        val spannedString = try {
            buildSpannedString(this, getIndexesOfMDLinks(this, true))
        } catch (error: Exception) {
            buildSpannedString { append(this) }
        }
        return spannedString.toString()
    }

    fun TextView.renderMD(sourceText: String) {
        val spannedString = try {
            buildSpannedString(sourceText, getIndexesOfMDLinks(sourceText, true))
        } catch (error: Exception) {
            buildSpannedString { append(sourceText) }
        }

        text = spannedString
    }

    private fun buildSpannedString(
        sourceText: String, totalList: List<FormattingData>
    ): SpannedString {
        var startIndex = 0
        val resultString = buildSpannedString {
            totalList.forEach {
                val preText = sourceText.substring(startIndex, it.startIndex)
                append(preText)
                when (it.textStyle) {
                    MarkdownStyle.LINK -> appendLink(it.text)
                    else -> append(it.text)
                }
                startIndex += preText.length + it.placeholder.length
            }
            append(sourceText.substring(startIndex, sourceText.length))
        }
        return resultString
    }


    private fun SpannableStringBuilder.appendLink(text: String) {
        bold { append(text.fromHtml()) }
    }

    private fun getIndexesOfMDLinks(
        sourceText: String, replaceWithLink: Boolean
    ): List<FormattingData> {
        return regexMDLink.findAll(sourceText).map {
            val text = it.groupValues[1]
            val link = it.groupValues[2]
            val htmledText = if (replaceWithLink) {
                    "<a href=\"$link\">$text</a>"
                } else {
                    text
                }
            FormattingData(it.value, htmledText, it.range.first, MarkdownStyle.LINK)
        }.toList()
    }
}