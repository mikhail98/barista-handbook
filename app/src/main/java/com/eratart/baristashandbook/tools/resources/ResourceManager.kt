package com.eratart.baristashandbook.tools.resources

import android.content.Context

class ResourceManager(private val context: Context) : IResourceManager {

    override fun getString(stringRes: Int): String {
        return context.getString(stringRes)
    }
}