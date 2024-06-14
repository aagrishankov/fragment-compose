package com.example.composefragment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StartListener(
    val navigateToBlueScreen: () -> Unit,
    val navigateToRedScreen: () -> Unit,
    val navigateToRedScreenByDeeplink: () -> Unit,
) : Parcelable
