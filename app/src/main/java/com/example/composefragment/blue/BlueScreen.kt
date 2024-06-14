package com.example.composefragment.blue

import androidx.fragment.compose.AndroidFragment
import androidx.fragment.compose.rememberFragmentState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.composefragment.BLUE_SCREEN

fun NavGraphBuilder.addBlueScreen(navController: NavController) {
    composable(route = BLUE_SCREEN) {
        val fragmentState = rememberFragmentState()
        AndroidFragment<BlueFragment>(
            fragmentState = fragmentState,
        )
    }
}
