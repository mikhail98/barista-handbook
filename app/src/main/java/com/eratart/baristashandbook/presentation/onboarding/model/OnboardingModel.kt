package com.eratart.baristashandbook.presentation.onboarding.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
class OnboardingModel(
    @StringRes
    val title: Int,
    @DrawableRes
    val imageRes: Int,
    val isSkippable: Boolean,
    val isSwipable: Boolean,
    @StringRes
    val buttonText: Int?
) : Parcelable