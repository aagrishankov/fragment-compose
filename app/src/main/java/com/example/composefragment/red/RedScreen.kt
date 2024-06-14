package com.example.composefragment.red

import androidx.fragment.compose.AndroidFragment
import androidx.fragment.compose.rememberFragmentState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.composefragment.RED_DEEPLINK
import com.example.composefragment.RED_SCREEN

fun NavGraphBuilder.addRedScreen(navController: NavController) {
    composable(
        route = RED_SCREEN,
        deepLinks = listOf(navDeepLink { uriPattern = RED_DEEPLINK })
    ) {
        val fragmentState = rememberFragmentState()
        AndroidFragment<RedFragment>(
            fragmentState = fragmentState,
        )
    }
}
