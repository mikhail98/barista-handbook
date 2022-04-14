package com.eratart.baristashandbook.baseui.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.eratart.baristashandbook.R

object AlertUtil {

    fun showOkAlert(context: Context, @StringRes titleRes: Int, @StringRes descriptionRes: Int) {
        AlertDialog.Builder(context, R.style.AlertDialogTheme)
            .setTitle(titleRes)
            .setMessage(descriptionRes)
            .setPositiveButton(R.string.app_ok) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}