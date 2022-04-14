package com.eratart.baristashandbook.tools.resources

import androidx.annotation.StringRes

interface IResourceManager {

    fun getString(@StringRes stringRes: Int): String
}