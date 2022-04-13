package com.eratart.baristashandbook.presentation.appinfo.viewmodel

import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import com.eratart.baristashandbook.tools.resources.IResourceManager

class AppInfoViewModel(resourceManager: IResourceManager, appPreferences: IAppPreferences) :
    BaseViewModel(resourceManager, appPreferences)