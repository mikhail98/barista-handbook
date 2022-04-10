package com.eratart.baristashandbook.core.util

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import com.eratart.baristashandbook.core.constants.IntConstants


object TextViewUrlUtil {

    fun TextView.setLinksClickable(listener: (String) -> Unit) {
        val strBuilder = SpannableStringBuilder(text)
        val urls = strBuilder.getSpans(IntConstants.ZERO, text.length, URLSpan::class.java)
        for (span in urls) {
            makeLinkClickable(strBuilder, span, listener)
        }
        text = strBuilder
        movementMethod = LinkMovementMethod.getInstance()
    }

    private fun makeLinkClickable(
        strBuilder: SpannableStringBuilder, span: URLSpan, listener: (String) -> Unit
    ) {
        val start = strBuilder.getSpanStart(span)
        val end = strBuilder.getSpanEnd(span)
        val flags = strBuilder.getSpanFlags(span)
        val clickable: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                listener.invoke(span.url)
            }
        }
        strBuilder.setSpan(clickable, start, end, flags)
        strBuilder.setSpan(StyleSpan(Typeface.BOLD), start, end, flags)
        strBuilder.setSpan(getUnderlineSpan(false), start, end, flags)
        strBuilder.removeSpan(span)
    }

    private fun getUnderlineSpan(isUnderline: Boolean): UnderlineSpan {
        return object : UnderlineSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = isUnderline
            }
        }
    }
}