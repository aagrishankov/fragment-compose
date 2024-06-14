package com.example.composefragment

import android.annotation.SuppressLint
import android.content.Intent
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.compose.AndroidFragment
import androidx.fragment.compose.rememberFragmentState
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.example.composefragment.StartFragment.Companion.LISTENER_KEY

fun NavGraphBuilder.addStartScreen(navController: NavController) {
    composable(route = START_SCREEN) {
        
        val listener = StartListener(
            navigateToBlueScreen = { navController.navigate(BLUE_SCREEN) },
            navigateToRedScreen = { navController.navigate(RED_SCREEN) },
            navigateToRedScreenByDeeplink = { navController.navigateDeeplink(RED_DEEPLINK) },
        )
        val fragmentState = rememberFragmentState()
        val args = bundleOf(LISTENER_KEY to listener)
        
        AndroidFragment<StartFragment>(
            fragmentState = fragmentState,
            arguments = args,
        )
    }
}

@SuppressLint("RestrictedApi")
fun NavController.navigateDeeplink(link: String) {
    val intent = Intent(Intent.ACTION_VIEW, link.toUri())
    val matchDeepLink = graph.matchDeepLink(NavDeepLinkRequest(intent))
    when {
        matchDeepLink != null -> navigate(link.toUri())
        else -> error("Deeplink not found")
    }
}
