package com.example.composefragment.blue

import android.util.Log
import androidx.fragment.compose.AndroidFragment
import androidx.fragment.compose.rememberFragmentState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composefragment.BLUE_SCREEN

fun NavGraphBuilder.addBlueScreen(navController: NavController) {
    composable(
        route = BLUE_SCREEN,
        arguments = listOf(
            navArgument("arg1") { type = NavType.IntType },
            navArgument("arg2") { type = NavType.StringType },
        )
    ) {

        val id = it.arguments?.getInt("arg1") ?: -1
        val name = it.arguments?.getString("arg2").orEmpty()

        Log.d("BlueScreen", "args: id = $id name = $name")

        val fragmentState = rememberFragmentState()
        AndroidFragment<BlueFragment>(
            fragmentState = fragmentState,
        )
    }
}
