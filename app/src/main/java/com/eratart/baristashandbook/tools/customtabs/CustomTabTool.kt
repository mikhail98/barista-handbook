package com.eratart.baristashandbook.tools.customtabs

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.eratart.baristashandbook.core.ext.printError

class CustomTabTool(private val context: Context) : ICustomTabTool {

    override fun openCustomTab(url: String) {
        if (url.isEmpty()) return
        val uri = Uri.parse(url)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        try {
            customTabsIntent.launchUrl(context, uri)
        } catch (e: Exception) {
            context.startActivity(Intent(Intent.ACTION_VIEW, uri))
            e.printError()
        }
    }
}