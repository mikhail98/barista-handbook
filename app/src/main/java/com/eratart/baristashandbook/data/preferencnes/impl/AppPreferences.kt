package com.eratart.baristashandbook.data.preferencnes.impl

import android.content.SharedPreferences
import com.eratart.baristashandbook.data.preferencnes.BasePreferences
import com.eratart.baristashandbook.domain.preferences.IAppPreferences

class AppPreferences(sharedPreferences: SharedPreferences) :
    BasePreferences(sharedPreferences), IAppPreferences